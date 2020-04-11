package by.me.view;

import by.me.GameCycle;
import by.me.MainApp;
import by.me.model.NumberUtil;
import by.me.model.PlayerStatistics;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class GameWindowController {

	private MainApp mainApp;
	
	@FXML
	private TableView<PlayerStatistics> statisticsTable;
	
	@FXML
	private TableColumn<PlayerStatistics, String> stepColumn;
	
	@FXML
	private TableColumn<PlayerStatistics, String> numberColumn;
	
	@FXML
	private TableColumn<PlayerStatistics, String> bullsColumn;
	
	@FXML
	private TableColumn<PlayerStatistics, String> cowsColumn;
	
	@FXML
	private TextField input;
	
	@FXML
	private Button okButton;
	
	@FXML
	private Button menuButton;
	
	@FXML
	private Label genNumber;
	
	@FXML
	private Label victory;
	
	private GameCycle game;
	
	public void setIt(String it) {
		this.genNumber.setText(it);
	}
	
	public void setGameCycle(GameCycle game) {
		this.game = game;
		this.statisticsTable.setItems(game.getTableData());
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	private void handleOKButton() {
		String input = this.input.getText();
		if (NumberUtil.isInvalidInput(input) || !NumberUtil.allDigitsUnique(input)) {
			Alert alert = new Alert(AlertType.WARNING);
    		alert.initOwner(this.mainApp.getPrimaryStage());
    		alert.setTitle("");
    		alert.setHeaderText("Кривой ввод");
    		alert.setContentText("Введите число с требуемым форматом");
    		
    		alert.showAndWait();
		}
		else {
			this.game.setNumber(input);
			this.game.analyse();
			if (game.isVictory()) {
				this.victory.setText("Победа!!!");
				this.okButton.setDisable(true);
				this.input.setDisable(true);
				
			}
		}
		
	}
	
	public GameWindowController() {
		this.input = new TextField();
		this.okButton = new Button();
	}
	
	@FXML
	private void handleMenuButon() {
		this.mainApp.returnSourceScene();
	}
	
	@FXML
    private void initialize() {
        
		this.stepColumn.setCellValueFactory(cellData -> cellData.getValue().steps());
        this.numberColumn.setCellValueFactory(cellData -> cellData.getValue().number());
        this.cowsColumn.setCellValueFactory(cellData -> cellData.getValue().cows());
        this.bullsColumn.setCellValueFactory(cellData -> cellData.getValue().bulls());
        
        this.okButton.setDefaultButton(true);
        
        DropShadow shadow = new DropShadow();
		shadow.setColor(Color.ORANGE);
		shadow.setHeight(76);
		shadow.setWidth(250);
        
        this.menuButton.addEventHandler(MouseEvent.MOUSE_ENTERED, ev -> {
			this.menuButton.setEffect(shadow);
		});
		this.menuButton.addEventHandler(MouseEvent.MOUSE_EXITED, ev -> {
			this.menuButton.setEffect(null);
		});
    }
	
}
