package jdbcGame;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/*
 * Created by Aaron Fernandes(300773526) on November 2015.
 */
public class ControllerAddPlayer extends ControllerRoot {

	@FXML	GridPane addPlayerGrid;

	@FXML TextField fNameTxt;
	@FXML TextField lNameTxt;
	@FXML TextField addressTxt;
	@FXML TextField pCodeTxt;
	@FXML TextField provienceTxt;
	@FXML TextField pNumTxt;

	public void addplayerBtnHandler(ActionEvent actionEvent){
		Alert alert=new Alert(Alert.AlertType.ERROR);
		boolean insertSuccessful;
		ObservableList<Node> list=addPlayerGrid.getChildren();
		for (Node node : list) {
			if (node instanceof TextField) {
				if (((TextField) node).getText().isEmpty()) {
					alert.setContentText("Missing info!\nYou will now be focused into that element!!");
					alert.showAndWait();
					node.requestFocus();
					return;
				}
			}
		}

		insertSuccessful = this.insertIntoDB("INSERT INTO Player (first_name, last_name, address, postal_code, province, phone_number) VALUES ('"+fNameTxt.getText()+"', '"+lNameTxt.getText()+"', '"+addressTxt.getText()+"','"+pCodeTxt.getText()+"','"+provienceTxt.getText()+"','"+pNumTxt.getText()+"');");
		if (insertSuccessful) {
			alert.setAlertType(Alert.AlertType.INFORMATION);
			alert.setContentText("Player added");
			alert.showAndWait();
		}
		else {
			alert.setAlertType(Alert.AlertType.ERROR);
			alert.setContentText("error with inserting");
			alert.showAndWait();
		}

	}
}
