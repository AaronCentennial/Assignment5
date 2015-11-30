package jdbcGame;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/*
 * Created by Aaron Fernandes(300773526) on November 2015.
 */
public class ControllerEditPlayer extends ControllerRoot implements Initializable {

	@FXML	private ComboBox<Integer> selPlayerID;
	@FXML private GridPane editGridPane;
	@FXML private GridPane selectGridPane;
	@FXML private ScrollPane editScrollPane;

	@FXML private TextField fNameTxt,lNameTxt,addressText,pCodeText,provienceText,pNumText;

	private int playerID;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle){
		if (selPlayerID!=null){
			ResultSet resultSet=null;
			try {
				String selPlayerIdQuery = "SELECT player_id FROM Player;";
				resultSet=this.runQuery(selPlayerIdQuery);

				this.runQuery(selPlayerIdQuery);
				selPlayerID.getItems().clear();
				while (resultSet.next()) {
					selPlayerID.getItems().add(resultSet.getInt("player_id"));
				}

			} catch (SQLException e) {
				DBConfig.displayException(e);
			}finally {
				if (resultSet!=null){
					try {
						resultSet.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

	public void selPlayerIDHandler() throws SQLException {
		String query="SELECT * FROM Player WHERE player_id="+(selPlayerID.getSelectionModel().getSelectedItem())+";";
		ResultSet rs=null;
		try {
			rs=this.runQuery(query);

			rs.next();
			playerID =rs.getInt("player_id");
			fNameTxt.setText(rs.getString("first_name"));
			lNameTxt.setText(rs.getString("last_name"));
			addressText.setText(rs.getString("address"));
			pCodeText.setText(rs.getString("postal_code"));
			provienceText.setText(rs.getString("province"));
			pNumText.setText(rs.getString("phone_number"));

		}
		catch (SQLException e){
			DBConfig.displayException(e);
		}
		finally {
			if (rs!=null){rs.close();}
		}

		this.togglePanes();
	}

	public void savePlayerBtnHandler() throws SQLException{
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		ObservableList<Node> list=editGridPane.getChildren();
		if (this.loopNodeListForEmptyInput(list)){
			this.insertIntoDB(String.format("UPDATE Player " +
																					"SET " +
																					"first_name='"+fNameTxt.getText()+"'," +
																					"last_name='"+lNameTxt.getText()+"'," +
																					"address='"+addressText.getText()+"'," +
																					"postal_code='"+pCodeText.getText()+"'," +
																					"province='"+provienceText.getText()+"'," +
																					"phone_number='"+pNumText.getText()+"'" +
																					"WHERE player_id="+playerID+";"));

			alert.setContentText("Record updated");
			alert.showAndWait();
		}

	}

	public void togglePanes(){
		selectGridPane.setVisible(!selectGridPane.isVisible());
		editScrollPane.setVisible(!editScrollPane.isVisible());
	}
}
