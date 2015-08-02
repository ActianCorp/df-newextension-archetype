#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.knime.${parentArtifactId}.node;

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

import java.util.Arrays;
import java.util.List;

import ${package}.dataflow.operators.NewOperator;
import com.pervasive.datarush.knime.core.framework.AbstractDRSettingsModel;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import com.pervasive.datarush.ports.PortMetadata;

/*package*/ 
final class NewOperatorNodeSettings extends AbstractDRSettingsModel<NewOperator> {

	public final SettingsModelString outputString = new SettingsModelString("outputstring", null);

	@Override
	protected List<SettingsModel> getComponentSettings() {
		return Arrays.<SettingsModel>
			asList(outputString);
	}

	@Override
	public void configure(PortMetadata[] inputTypes,
						  NewOperator operator) throws InvalidSettingsException {

		if (this.outputString.getStringValue() == null || this.outputString.getStringValue().trim().isEmpty()) {
			throw new InvalidSettingsException("Output String must not be empty!");
		}

		operator.setOutputString(this.outputString.getStringValue());
	}

}
