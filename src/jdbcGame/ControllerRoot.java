package jdbcGame;


import javafx.event.ActionEvent;

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

	protected ResultSet runQuery(String q){
		try
			(
					Connection connection = DBConfig.getConnection();
					Statement statment=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			)
		{
			return statment.executeQuery(q);

		} catch (SQLException e) {
			DBConfig.displayException(e);
		}
		return null;
	}

	protected void insertIntoDB(String q){
		try
		(
			Connection connection =DBConfig.getConnection();
			Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		)
		{
			ResultSet r = statement.executeQuery(q);
		}
		catch (SQLException e){
			DBConfig.displayException(e);
		}
	}



}
