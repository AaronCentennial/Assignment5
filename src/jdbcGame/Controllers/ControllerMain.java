package jdbcGame.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import jdbcGame.Main;

public class ControllerMain extends ControllerRoot{
	@FXML	private ComboBox mainCombo;

	public void mainOptionHandler(ActionEvent actionEvent) {

		Main.setScene(mainCombo.getSelectionModel().getSelectedIndex());

	}

}
