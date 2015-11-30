package jdbcGame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
	private static Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("fxmls/main.fxml"));
		primaryStage.setTitle("JDBC Assignment 5 - Aaron Fernandes");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		stage = primaryStage;
	}


	public static void main(String[] args) {
		launch(args);
	}

	/*

	 */
	public static void setScene(int fxml) {
		try {

			Parent addplayer = null;
			String title="";

			switch (fxml) {
				case 0:
					addplayer = FXMLLoader.load(Main.class.getResource("fxmls/AddPlayer.fxml"));
					title="Add Player";
					break;
				case 1:
					addplayer = FXMLLoader.load(Main.class.getResource("fxmls/AddGame.fxml"));
					title="Add Game";
					break;
				case 2:
					addplayer = FXMLLoader.load(Main.class.getResource("fxmls/EditPlayer.fxml"));
					title="Edit Player";
					break;
				case 3:
					addplayer=FXMLLoader.load(Main.class.getResource("fxmls/EditGame.fxml"));
					title="EditGame";
					break;
				default:
					addplayer=FXMLLoader.load(Main.class.getResource("fxmls/main.fxml"));
					title="Aaron Fernandes";
					break;
			}

			stage.setTitle("JDBC Assignment 5 - " + title);
			stage.setScene(new Scene(addplayer));
		}
		catch (IOException e) {
			e.printStackTrace();
		}


	}
}