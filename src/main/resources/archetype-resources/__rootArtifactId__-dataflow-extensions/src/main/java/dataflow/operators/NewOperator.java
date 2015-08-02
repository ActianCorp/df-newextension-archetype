#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dataflow.operators;

/*
		Copyright 2015 Actian Corporation

		Licensed under the Apache License, Version 2.0 (the "License");
		you may not use this file except in compliance with the License.
		You may obtain a copy of the License at

		http://www.apache.org/licenses/LICENSE-2.0

		Unless required by applicable law or agreed to in writing, software
		distributed under the License is distributed on an "AS IS" BASIS,
		WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
		See the License for the specific language governing permissions and
		limitations under the License.
*/


import static com.pervasive.datarush.io.WriteMode.OVERWRITE;
import static com.pervasive.datarush.types.TokenTypeConstant.*;
import static com.pervasive.datarush.types.TypeUtil.mergeTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.pervasive.datarush.annotations.PortDescription;
import com.pervasive.datarush.annotations.PropertyDescription;

import com.pervasive.datarush.graphs.LogicalGraph;
import com.pervasive.datarush.graphs.LogicalGraphFactory;

import com.pervasive.datarush.operators.*;
import com.pervasive.datarush.operators.io.textfile.ReadDelimitedText;
import com.pervasive.datarush.operators.io.textfile.WriteDelimitedText;
import com.pervasive.datarush.ports.physical.*;
import com.pervasive.datarush.ports.record.*;
import com.pervasive.datarush.tokens.TokenUtils;
import com.pervasive.datarush.tokens.scalar.*;
import com.pervasive.datarush.types.RecordTokenType;
import com.pervasive.datarush.types.RecordTokenTypeBuilder;
import org.apache.commons.lang.BooleanUtils;

@JsonSerialize(include=Inclusion.NON_DEFAULT)
public class NewOperator extends ExecutableOperator implements RecordPipelineOperator {

	private final RecordPort input = newRecordInput("input");
	private final RecordPort output = newRecordOutput("output");
	private final RecordPort reject = newRecordOutput("reject");
	private String outputString;

	@PortDescription("Source records")
	public RecordPort getInput() {
		return input;
	}

	@PortDescription("Output records")
	public RecordPort getOutput() {
		return output;
	}

	@PortDescription("Rejected records")
	public RecordPort getReject() {
		return reject;
	}

	public String getOutputString() {
		return outputString;
	}

	public void setOutputString(String outputString) {
		this.outputString = outputString;
	}

	public NewOperator() {
	}

	@Override
	protected void computeMetadata(StreamingMetadataContext context) {
		//best practice: perform any input validation: should be done first
		// validateInput(context);

		//required: declare our parallelizability.
		//  in this case we use source parallelism as a hint for our parallelism.
		context.parallelize(ParallelismStrategy.NEGOTIATE_BASED_ON_SOURCE);


		// Setup the output schema for NewOperator
		RecordTokenTypeBuilder typeBuilder = new RecordTokenTypeBuilder();
		typeBuilder.addField(field(STRING, "OutputString"));

		//required: declare output type
		//  in this case our output type is the input type plus an additional field
		//  containing the result
//        if (excludeSourceFields) {
//            getOutput().setType(context, typeBuilder.toType());
//        } else {
            RecordTokenType outputType = mergeTypes(getInput().getType(context), typeBuilder.toType());
            getOutput().setType(context, outputType);
//        }

		RecordTokenType rejectType = mergeTypes(getInput().getType(context), record(STRING("ErrorText")));
		getReject().setType(context, rejectType);

		//best practice: define output ordering/distribution
		//  in this case we are generating data in a single field so
		//  the ordering is unspecified and the distribution is partial
		RecordMetadata outputMetadata = input.getCombinedMetadata(context);
		output.setOutputDataOrdering(context, DataOrdering.UNSPECIFIED);
		reject.setOutputDataOrdering(context, DataOrdering.UNSPECIFIED);
	}

	// Check the operator configuration
	private boolean checkConfig() {
		// Check the settings for NewOperator here.  Return false if there are configuration errors.
        return true;
	}

	@Override
	protected void execute(ExecutionContext context) {

		RecordInput recordInput = getInput().getInput(context);
		RecordOutput recordOutput = getOutput().getOutput(context);
		RecordOutput recordReject = getReject().getOutput(context);

		ScalarValued[] allInputs = recordInput.getFields();
		ScalarSettable[] outputs = TokenUtils.selectFields(recordOutput, recordInput.getType().getNames());
		ScalarSettable[] rejects = TokenUtils.selectFields(recordReject, recordInput.getType().getNames());

		// Quit early if the operator configuration isn't valid
		if (checkConfig() == false) {
			recordOutput.pushEndOfData();
			recordReject.pushEndOfData();
			return;
		}

		while (recordInput.stepNext()) {
			try {

				// Copy the original input record fields to the corresponding output record fields
				TokenUtils.transfer(allInputs, outputs);
				StringSettable outputField = (StringSettable) recordOutput.getField("OutputString");
				outputField.set(outputString);
				recordOutput.push();
			}
			catch (Exception e)
			{
				// Copy the original input record fields to the corresponding reject record fields
				TokenUtils.transfer(allInputs, rejects);
				StringSettable errorField = (StringSettable) recordReject.getField("ErrorText");
				errorField.set(e.getMessage() + "${symbol_escape}n" + Arrays.toString(e.getStackTrace()));
				recordReject.push();

			}
		}

		recordOutput.pushEndOfData();
		recordReject.pushEndOfData();
	}
}
