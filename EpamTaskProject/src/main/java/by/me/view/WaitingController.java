package by.me.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WaitingController {

	@FXML
	Label label;
	
	@FXML
	private void initialize() {
		this.label.setText("��������� �����...");
	}
	
	public void setLabel(String text) {
		this.label.setText(text);
	}
	
	public void clearLabel() {
		this.label.setText("");
	}
}
