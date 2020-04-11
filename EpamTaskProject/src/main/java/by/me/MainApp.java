package by.me;

import java.io.IOException;
import java.io.InputStream;

import by.me.view.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

public class MainApp extends Application{
	
	Stage primaryStage;
	AnchorPane rootLayout;
	Scene scene;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setResizable(false);
	
		initLayout();
	}
	
	public Stage getPrimaryStage() {
		return this.primaryStage;
	}
	
	public void returnSourceScene() {
		this.primaryStage.setScene(this.scene);
	}
	public void initLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MainMenu.fxml"));
			this.rootLayout = loader.load();
			MainMenuController controller = loader.getController();
			controller.setMainApp(this);
			
			InputStream input = MainApp.class.getResourceAsStream("view/icons/menu.png");
			this.rootLayout.setBackground(new Background(new BackgroundImage(
					new Image(input, 1024, 600, false, false), 
					BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
					BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
			this.scene = new Scene(rootLayout);
			this.primaryStage.setScene(scene);
			this.primaryStage.show();
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	

}
