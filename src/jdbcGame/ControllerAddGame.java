package jdbcGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/*
 * Created by Aaron Fernandes(300773526) on November 2015.
 */
public class ControllerAddGame extends ControllerRoot {

	@FXML private TextField gameText;

	public void gameAddBtnHandler(ActionEvent actionEvent){
		Alert alert=new Alert(Alert.AlertType.ERROR);

		if (gameText.getText().isEmpty()){
			alert.setContentText("No Game entered!");
			alert.showAndWait();
		}
		else{
			String gText= gameText.getText().replace("'","''");
			this.insertIntoDB("INSERT INTO Game (game_title) VALUES ('"+ gText +"');");
			alert.setAlertType(Alert.AlertType.INFORMATION);
			alert.setContentText(gameText.getText()+"\nAdded as new game");
			alert.showAndWait();
		}
	}
}
