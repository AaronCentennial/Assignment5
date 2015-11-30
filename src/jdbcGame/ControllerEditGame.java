package jdbcGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/*
 * Created by Aaron Fernandes(300773526) on November 2015.
 */
public class ControllerEditGame extends ControllerRoot implements Initializable {

	@FXML ComboBox<Integer> editGameSelect;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		if (editGameSelect!=null){
			ResultSet resultSet=null;
			try {
				String q="SELECT game_id FROM Game";
				resultSet=this.runQuery(q);

				editGameSelect.getItems().clear();
				while (resultSet.next()){
					editGameSelect.getItems().add(resultSet.getInt("game_id"));
				}

			}
			catch (SQLException e){

			}
		}
	}

	@Override
	public void cancelHandler(ActionEvent actionEvent){

	}

	public void editGameSelectHandler(){
		int _gameID = editGameSelect.getSelectionModel().getSelectedItem();
		String q="SELECT * FROM Game WHERE game_id="+_gameID;
		//TODO finish this function
	}

	public void gameSaveBtnHandler(){
	}

	public void togglePanes(){
/*		selectGridPane.setVisible(!selectGridPane.isVisible());
		editScrollPane.setVisible(!editScrollPane.isVisible());*/
	}

}
