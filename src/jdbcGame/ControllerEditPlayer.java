package jdbcGame;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
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
	@FXML private ScrollPane editScrollPane;

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
			playerID=rs.getInt("player_id");
			System.out.println(rs.getString("first_name"));
			System.out.println(rs.getString("last_name"));
			System.out.println(rs.getString("address"));
			System.out.println(rs.getString("postal_code"));
			System.out.println(rs.getString("province"));
			System.out.println(rs.getString("phone_number"));

		}
		catch (SQLException e){
			DBConfig.displayException(e);
		}
		finally {
			if (rs!=null){rs.close();}
		}

		this.togglePanes();
	}

	public void togglePanes(){
		editGridPane.setVisible(!editGridPane.isVisible());
		editScrollPane.setVisible(!editScrollPane.isVisible());
	}
}
