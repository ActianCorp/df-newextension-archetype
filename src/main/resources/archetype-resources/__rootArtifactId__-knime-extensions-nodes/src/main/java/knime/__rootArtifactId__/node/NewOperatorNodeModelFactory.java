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

import ${package}.dataflow.operators.NewOperator;
import com.pervasive.datarush.knime.core.framework.AbstractDRNodeFactory;
import com.pervasive.datarush.knime.core.framework.DRNodeModel;
import com.pervasive.datarush.knime.coreui.common.CustomDRNodeDialogPane;

public final class NewOperatorNodeModelFactory extends AbstractDRNodeFactory<NewOperator> {

    @Override
    protected CustomDRNodeDialogPane<NewOperator> createNodeDialogPane() {
    	CustomDRNodeDialogPane<NewOperator> dialog = new CustomDRNodeDialogPane<NewOperator>(new NewOperator(), new NewOperatorNodeDialogPane());
    	dialog.setDefaultTabTitle("Properties");
        return dialog;
    }

    @Override
    public DRNodeModel<NewOperator> createDRNodeModel() {
        return new DRNodeModel<NewOperator>( new NewOperator(), new NewOperatorNodeSettings());
    }

    @Override
    protected boolean hasDialog() {
        return true;
    }
}
