package jdbcGame.Controllers;


import javafx.collections.ObservableList;
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

	/**
	 * Sets the Scene to default
	 */
	public void cancelHandler(){
		Main.setScene(-1);
	}

	/**
	 * Loops through a list of nodes and checks if any are null
	 * if null the user will be alerted and that element will be focused
	 * @param list		list of nodes
	 * @return				true if no nodes blank
	 */
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

	/**
	 * ???
	 * @param sqlQuery		A query statement
	 * @param colName			The name of the column and stuff
	 * @return						returns an Array List
	 */
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
