package jdbcGame.Controllers;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import jdbcGame.DBConfig;
import jdbcGame.Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/*
 * Created by Aaron Fernandes(300773526) on November 2015.
 */
public class ControllerRoot {

	public void cancelHandler(ActionEvent actionEvent){
		Main.setScene(-1);
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

	protected ArrayList<Integer> _runQueryHelper(String sqlQuery, String colName){
		ArrayList<Integer> list=new ArrayList<>();
		try
				(
						Connection connection= DBConfig.getConnection();
						Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
						ResultSet resultSet=statement.executeQuery(sqlQuery)
				)
		{

			while (resultSet.next()){
				list.add(resultSet.getInt(colName));
			}
			return list;
		}
		catch (SQLException e){
			DBConfig.displayException(e);
			return null;
		}
	}
}
