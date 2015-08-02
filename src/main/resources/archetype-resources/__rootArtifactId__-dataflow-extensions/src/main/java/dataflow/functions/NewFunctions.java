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
package ${package}.dataflow.functions;

import static com.pervasive.datarush.functions.ScalarFunctionDescriptor.define;

import ${package}.dataflow.functions.evaluators.NewFunction;
import com.pervasive.datarush.annotations.Function;
import com.pervasive.datarush.functions.ScalarValuedFunction;
import com.pervasive.datarush.types.TokenTypeConstant;

public class NewFunctions {
    @Function(description="Applies myfunction to arg1 and arg2",
    		  category="NewFunctions",
			  argumentNames={"arg1","arg2"})
    public static ScalarValuedFunction newfunction(ScalarValuedFunction arg1,ScalarValuedFunction arg2) {
		return define("NewFunctions.newfunction",TokenTypeConstant.STRING,NewFunction.class, arg1, arg2);
    }
}
