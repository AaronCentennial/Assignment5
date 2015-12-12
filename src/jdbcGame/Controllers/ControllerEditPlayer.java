package jdbcGame.Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import jdbcGame.DBConfig;
import jdbcGame.beans.Player;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/*
 * Created by Aaron Fernandes(300773526) on November 2015.
 */
public class ControllerEditPlayer extends ControllerSqlPlayer implements Initializable {

	@FXML	private ComboBox<Integer> selPlayerID;
	@FXML private GridPane editGridPane;
	@FXML private GridPane selectGridPane;
	@FXML private ScrollPane editScrollPane;

	@FXML private TextField fNameTxt;
	@FXML private TextField lNameTxt;
	@FXML private TextField addressText;
	@FXML private TextField pCodeText;
	@FXML private TextField provienceText;
	@FXML private TextField pNumText;

	private int _playerID;

	/**
	 * Populates a list of player id's
	 * @param url
	 * @param resourceBundle
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle){
		if (selPlayerID!=null){
			try {
				selPlayerID.setItems(FXCollections.observableArrayList(this._runQuery()));
			} catch (SQLException e) {
				DBConfig.displayException(e);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * Change Panes and lets user edit a player
	 * @throws SQLException
	 */
	public void selPlayerIDHandler() throws SQLException {

		try {
			Player player = this._runQuery(selPlayerID.getSelectionModel().getSelectedItem());

			this._playerID =player.getPlayer_id();
			fNameTxt.setText(player.getFirst_name());
			lNameTxt.setText(player.getLast_name());
			addressText.setText(player.getAddress());
			pCodeText.setText(player.getPostal_code());
			provienceText.setText(player.getProvince());
			pNumText.setText(player.getPhone_number());

		}
		catch (SQLException e){
			DBConfig.displayException(e);
		}

		this.togglePanes();
	}

	/**
	 * Save the edited player to the db
	 * @throws SQLException
	 */
	public void savePlayerBtnHandler() throws SQLException{
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		Player player=new Player();

		player.setPlayer_id(this._playerID);
		player.setFirst_name(fNameTxt.getText());
		player.setLast_name(lNameTxt.getText());
		player.setAddress(addressText.getText());
		player.setPostal_code(pCodeText.getText());
		player.setProvince(provienceText.getText());
		player.setPhone_number(pNumText.getText());

		if (this._updateQuery(player)){
			alert.setContentText("Player saved");
			alert.showAndWait();
		}
		else{
			alert.setAlertType(Alert.AlertType.ERROR);
			alert.setContentText("Player Not saved");
			alert.showAndWait();
		}
	}

	/**
	 * Changes the Panes
	 */
	public void togglePanes(){
		selectGridPane.setVisible(!selectGridPane.isVisible());
		editScrollPane.setVisible(!editScrollPane.isVisible());
	}
}
