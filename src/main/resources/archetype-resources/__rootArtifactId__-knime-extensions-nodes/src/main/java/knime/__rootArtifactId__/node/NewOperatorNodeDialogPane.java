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
import com.pervasive.datarush.knime.coreui.common.*;
import com.pervasive.datarush.ports.PortMetadata;
import org.knime.core.node.InvalidSettingsException;

import javax.swing.*;
import java.awt.*;

/*package*/ final class NewOperatorNodeDialogPane implements CustomDialogComponent<NewOperator> {

	private final NewOperatorNodeSettings settings = new NewOperatorNodeSettings();

	private JTextArea outputString;

	@Override
	public NewOperatorNodeSettings getSettings() {
		return settings;
	}

	@Override
	public boolean isMetadataRequiredForConfiguration(int portIndex) {
		return true;
	}

	@Override
	public Component getComponent() {
		JPanel dialog = new JPanel();
		dialog.setLayout(new BorderLayout());

		outputString = createTextArea();
		JScrollPane scrollPane = new JScrollPane(outputString);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Output String"));
		dialog.add(scrollPane, BorderLayout.CENTER);

		return dialog;
	}

	@Override
	public void refresh(PortMetadata[] arg0) {
		outputString.setText(settings.outputString.getStringValue());
	}

    @Override
    public void validateAndApplySettings() throws InvalidSettingsException {
        settings.outputString.setStringValue(outputString.getText());
    }

    private JTextArea createTextArea() {
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        return textArea;
    }
}


