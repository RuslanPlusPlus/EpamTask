package by.me.view;

import java.util.Iterator;
import java.util.List;

import by.me.MainApp;
import by.me.daoImpl.Record;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class RecordsController {

	@FXML
	private Label firstPlayerName;
	
	@FXML
	private Label secondPlayerName;
	
	@FXML
	private Label thirdPlayerName;
	
	@FXML
	private Label firstPlayerSteps;
	
	@FXML
	private Label secondPlayerSteps;
	
	@FXML
	private Label thirdPlayerSteps;
	
	@FXML
	private Button menuButton;
	
	private MainApp mainApp;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	private void handleMenuButton() {
		this.mainApp.returnSourceScene();
	}
	
	public void updateRecords() {
		
	}
	
	@FXML
	private void initialize() {
		DropShadow shadow = new DropShadow();
		shadow.setColor(Color.ORANGE);
		shadow.setHeight(50);
		shadow.setWidth(180);
		
		this.menuButton.addEventHandler(MouseEvent.MOUSE_ENTERED, ev -> {
			this.menuButton.setEffect(shadow);
		});
		this.menuButton.addEventHandler(MouseEvent.MOUSE_EXITED, ev -> {
			this.menuButton.setEffect(null);
		});
	}
	
	public void loadRecords(List<Record> records) {
	
		Iterator<Record> it = records.iterator();
		if (records != null) {
			if (it.hasNext()) {
				it.next();
				this.firstPlayerName.setText(records.get(0).getPlayerName());
				this.firstPlayerSteps.setText(Integer.toString(records.get(0).getSteps()));
			}
			if (it.hasNext()) {
				it.next();
				this.secondPlayerName.setText(records.get(1).getPlayerName());
				this.secondPlayerSteps.setText(Integer.toString(records.get(1).getSteps()));
			}
			if (it.hasNext()) {
				it.next();
				this.thirdPlayerName.setText(records.get(2).getPlayerName());
				this.thirdPlayerSteps.setText(Integer.toString(records.get(2).getSteps()));
			}
		}
	}
	
}
