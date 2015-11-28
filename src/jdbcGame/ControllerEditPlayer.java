package jdbcGame;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

/*
 * Created by Aaron Fernandes(300773526) on November 2015.
 */
public class ControllerEditPlayer extends ControllerRoot implements Initializable {

	@FXML	private ComboBox<Integer> selPlayerID;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		if (selPlayerID!=null){
			selPlayerID.getItems().clear();
			selPlayerID.getItems().addAll(1,2,4);
		}
	}
}
