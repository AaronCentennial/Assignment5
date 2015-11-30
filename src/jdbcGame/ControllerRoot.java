package jdbcGame;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/*
 * Created by Aaron Fernandes(300773526) on November 2015.
 */
public class ControllerRoot {

	public void cancelHandler(ActionEvent actionEvent){
		Main.setScene(-1);
	}

	protected ResultSet runQuery(String q) throws SQLException {
		//ResultSet resultSet = null;
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;

		try
		{
			connection = DBConfig.getConnection();
			statement =connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(q);
			return resultSet;
		}
		catch (SQLException e)
		{
			DBConfig.displayException(e);
		}
		finally {
/*			if (resultSet!=null){
				resultSet.close();
			}

			if (statement!=null){
				statement.close();
			}

			if (connection!=null){
				connection.close();
			}*/
		}


		return null;
	}

	protected boolean insertIntoDB(String q){
		try
		(
			Connection connection =DBConfig.getConnection();
			Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		)
		{
			statement.executeUpdate(q);
			return true;
		}
		catch (SQLException e){
			DBConfig.displayException(e);
			return false;
		}
	}

	protected boolean loopNodeListForEmptyInput(ObservableList<Node> list){
		Alert alert=new Alert(Alert.AlertType.ERROR);
		for(Node node : list){
			if (node instanceof TextField){
				if (((TextField) node).getText().isEmpty()){
					alert.setContentText("Missing info!\nYou will now be focused into that element!!");
					alert.showAndWait();
					node.requestFocus();
					return false;
				}
			}
		}
		return true;
	}


}
