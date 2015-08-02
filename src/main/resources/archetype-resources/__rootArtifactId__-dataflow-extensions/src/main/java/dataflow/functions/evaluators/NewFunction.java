#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
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
package ${package}.dataflow.functions.evaluators;

import com.pervasive.datarush.functions.FunctionEvaluator;
import com.pervasive.datarush.tokens.scalar.StringSettable;
import com.pervasive.datarush.tokens.scalar.StringValued;

public class NewFunction implements FunctionEvaluator {

    private final StringSettable result;
    private final StringValued arg1;
    private final StringValued arg2;

    public NewFunction(StringSettable result, StringValued arg1, StringValued arg2) {
        this.result = result;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    @Override
    public void evaluate() {

        String output = "";
        try {
            output = arg1.asString() + arg2.asString();
        } catch (Exception ex) {
            output = ex.getMessage();
        }

        result.set(output);
    }
}
