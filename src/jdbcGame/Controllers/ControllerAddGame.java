package jdbcGame.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import jdbcGame.DBConfig;
import jdbcGame.beans.Game;

import java.sql.SQLException;

/*
 * Created by Aaron Fernandes(300773526) on November 2015.
 */
public class ControllerAddGame extends ControllerSqlGame {

	@FXML private TextField gameText;

	public void gameAddBtnHandler(ActionEvent actionEvent){
		Alert alert=new Alert(Alert.AlertType.ERROR);
		Game game=new Game();
		if (gameText.getText().isEmpty()){
			alert.setContentText("No Game entered!");
			alert.showAndWait();
		}
		else{
			String gText= gameText.getText().replace("'","''");
			game.setGame_title(gText);
			try {
				this._insertQuery(game);
			} catch (SQLException e) {
				DBConfig.displayException(e);
			}
			alert.setAlertType(Alert.AlertType.INFORMATION);
			alert.setContentText(gameText.getText()+"\nAdded as new game");
			alert.showAndWait();
		}
	}
}
