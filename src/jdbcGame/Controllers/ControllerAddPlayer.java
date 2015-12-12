package jdbcGame.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import jdbcGame.DBConfig;
import jdbcGame.beans.Player;

import java.sql.SQLException;

/*
 * Created by Aaron Fernandes(300773526) on November 2015.
 */
public class ControllerAddPlayer extends ControllerSqlPlayer {

	@FXML	GridPane addPlayerGrid;

	@FXML private TextField fNameTxt;
	@FXML private TextField lNameTxt;
	@FXML private TextField addressTxt;
	@FXML private TextField pCodeTxt;
	@FXML private TextField provienceTxt;
	@FXML private TextField pNumTxt;

	/**
	 * Adds a new player
	 */
	public void addplayerBtnHandler(){
		Alert alert=new Alert(Alert.AlertType.ERROR);
		boolean insertSuccessful=false;
		Player player=new Player();
		boolean inputValid=this.loopNodeListForEmptyInput(addPlayerGrid.getChildren());

		//If all inputs are not null then
		//add to db
		if (inputValid) {

			player.setFirst_name(fNameTxt.getText());
			player.setLast_name(lNameTxt.getText());
			player.setAddress(addressTxt.getText());
			player.setPostal_code(pCodeTxt.getText());
			player.setProvince(provienceTxt.getText());
			player.setPhone_number(pNumTxt.getText());

			try {
				insertSuccessful = this._insertQuery(player);
			} catch (SQLException e) {
				DBConfig.displayException(e);
			}
			if (insertSuccessful) {
				alert.setAlertType(Alert.AlertType.INFORMATION);
				alert.setContentText("Player added");
				alert.showAndWait();
			} else {
				alert.setAlertType(Alert.AlertType.ERROR);
				alert.setContentText("error with inserting");
				alert.showAndWait();
			}
		}//if

	}//addplayerBtnHandler
}
