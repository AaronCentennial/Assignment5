package jdbcGame.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import jdbcGame.Main;

public class ControllerMain extends ControllerRoot{
	@FXML	private ComboBox mainCombo;

	/**
	 * Sets the scene based on the selected index
	 */
	public void mainOptionHandler() {

		Main.setScene(mainCombo.getSelectionModel().getSelectedIndex());

	}

}
