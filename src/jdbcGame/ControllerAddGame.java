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
			//TODO: Insert Shit into DB
		}
	}
}
