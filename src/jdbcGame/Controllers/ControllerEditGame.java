package jdbcGame.Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import jdbcGame.beans.Game;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/*
 * Created by Aaron Fernandes(300773526) on November 2015.
 */
public class ControllerEditGame extends ControllerSqlGame implements Initializable {

	@FXML ComboBox<Integer> editGameSelect;

	@FXML	ScrollPane editGameScroll;
	@FXML	GridPane selGameGrid;

	@FXML TextField gameText;

	private int _gameID;

	/**
	 * Populates a dropdown list of game id's for
	 * the user to select a game
	 * @param url
	 * @param resourceBundle
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		if (editGameSelect!=null){
			try {
				editGameSelect.setItems(FXCollections.observableArrayList(this._runQuery()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Changes to edit mode when user selects a game
	 * @throws SQLException
	 */
	public void editGameSelectHandler() throws SQLException {
		this._gameID = editGameSelect.getSelectionModel().getSelectedItem();
		Game game=this.runQuery(this._gameID);

		gameText.setText(game.getGame_title());
		this._togglePanes();
	}

	/**
	 * adds a game to the db
	 * @throws SQLException
	 */
	public void gameSaveBtnHandler() throws SQLException {
		Alert alert=new Alert(Alert.AlertType.ERROR);
		Game game=new Game();
		game.setGame_id(this._gameID);
		game.setGame_title(gameText.getText());
		if (this._updateQuery(game)){
			alert.setAlertType(Alert.AlertType.INFORMATION);
			alert.setContentText("Game saved");
			alert.showAndWait();
		}
		else {
			alert.setContentText("Error in saving");
			alert.showAndWait();
		}
	}

	/**
	 * Changes the Panes when input valid
	 */
	private void _togglePanes(){
		selGameGrid.setVisible(!selGameGrid.isVisible());
		editGameScroll.setVisible(!editGameScroll.isVisible());
	}

}
