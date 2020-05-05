package by.me.view;

import java.io.IOException;
import java.util.List;

import by.me.GameCycle;
import by.me.MainApp;
import by.me.ServerThread;
import by.me.Waiter;
import by.me.dao.RecordsDAO;
import by.me.daoImpl.Record;
import by.me.daoImpl.RecordsDAOImpl;
import by.me.model.NumberUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


public class MainMenuController {
	
	@FXML
	private Button playButton;
	
	@FXML
	private Button recordsButton;
	
	@FXML
	private Button gameInfoButton;
	
	@FXML
	private Button exitButton;
	
	@FXML
	private TextField playerNameField;
	
	private MainApp mainApp;
	private AnchorPane gameLayout;
	private AnchorPane recordsLayout;
	private GameCycle game;
	private String playerName;
	private ServerThread server;
	private String number;
	
	private RecordsDAO dao;
	
	public MainMenuController() {
		this.dao = new RecordsDAOImpl();
	}
	
	private void initRecordsWindow() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Records.fxml"));
			this.recordsLayout = loader.load();
			
			RecordsController controller = loader.getController();
			List<Record> records = this.dao.getRecords();
			controller.loadRecords(records);
			controller.setMainApp(this.mainApp);
			
			Scene scene = new Scene(this.recordsLayout);
			this.mainApp.getPrimaryStage().setScene(scene);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initGameWindow() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("GameWindow.fxml"));
			this.gameLayout = loader.load();
			GameWindowController controller = loader.getController();
			controller.setMainApp(this.mainApp);
			//String genNumber = NumberUtil.generateStringNumber();
			controller.setIt(this.number);
			
			this.game = new GameCycle(this.number);
			this.game.setDao(this.dao);
			this.game.setPlayerName(this.playerName);
			this.game.setToOneStepsCounter();
			controller.setGameCycle(this.game);
			Scene scene = new Scene(this.gameLayout);
			this.mainApp.getPrimaryStage().setScene(scene);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setServer(ServerThread server) {
		this.server = server;
	}
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	public void initWaitingWindow() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Waiting.fxml"));
			AnchorPane layout = loader.load();
			
			WaitingController controller = loader.getController();
			Scene scene = new Scene(layout);
			this.mainApp.getPrimaryStage().setScene(scene);
			this.server.generateNumber();
			
			while (!Waiter.isReady) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("waiting");
			}
			
			this.number = server.getNumber();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handlePlayButton() {
		this.playerName = this.playerNameField.getText();
		if (this.playerName.isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
    		alert.initOwner(this.mainApp.getPrimaryStage());
    		alert.setTitle("");
    		alert.setHeaderText("");
    		alert.setContentText("¬ведите им€ игрока");
    		
    		alert.showAndWait();
		}
		else {
			initWaitingWindow();
			initGameWindow();
		}
	}
	
	@FXML
	private void handleRecordsButton() {
		initRecordsWindow();
	}
	
	@FXML
	private void handleExitButton() {
		this.server.getThread().interrupt();
		this.mainApp.getPrimaryStage().close();
	}
	
	@FXML
	private void initialize() {
		DropShadow shadow = new DropShadow();
		shadow.setColor(Color.ORANGE);
		shadow.setHeight(76);
		shadow.setWidth(250);
		
		this.playButton.addEventHandler(MouseEvent.MOUSE_ENTERED, ev -> {
			this.playButton.setEffect(shadow);
		});
		this.playButton.addEventHandler(MouseEvent.MOUSE_EXITED, ev -> {
			this.playButton.setEffect(null);
		});
		
		this.recordsButton.addEventHandler(MouseEvent.MOUSE_ENTERED, ev -> {
			this.recordsButton.setEffect(shadow);
		});
		this.recordsButton.addEventHandler(MouseEvent.MOUSE_EXITED, ev -> {
			this.recordsButton.setEffect(null);
		});
		
		this.gameInfoButton.addEventHandler(MouseEvent.MOUSE_ENTERED, ev -> {
			this.gameInfoButton.setEffect(shadow);
		});
		this.gameInfoButton.addEventHandler(MouseEvent.MOUSE_EXITED, ev -> {
			this.gameInfoButton.setEffect(null);
		});
		
		this.exitButton.addEventHandler(MouseEvent.MOUSE_ENTERED, ev -> {
			this.exitButton.setEffect(shadow);
		});
		this.exitButton.addEventHandler(MouseEvent.MOUSE_EXITED, ev -> {
			this.exitButton.setEffect(null);
		});
	}
}
