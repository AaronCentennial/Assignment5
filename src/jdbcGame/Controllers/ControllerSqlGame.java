package jdbcGame.Controllers;

import jdbcGame.DBConfig;
import jdbcGame.beans.Game;

import java.sql.*;
import java.util.ArrayList;

/*
 * Created by Aaron Fernandes(300773526) on December 2015.
 */
public class ControllerSqlGame extends ControllerRoot {

	protected Game runQuery(int gameID) throws SQLException {
		String sqlQuery= "SELECT * FROM Game WHERE game_id=?";
		Game game=new Game();
		ResultSet resultSet=null;
		try
				(
						Connection connection = DBConfig.getConnection();
						PreparedStatement statement=connection.prepareStatement(sqlQuery)
				)
		{
			statement.setInt(1,gameID);
			resultSet=statement.executeQuery();
			resultSet.next();

			gameID=resultSet.getInt("game_id");
			game.setGame_id(gameID);
			game.setGame_title(resultSet.getString("game_title"));

			return game;
		}
		catch (SQLException e){
			DBConfig.displayException(e);
			return null;
		}
		finally {
			if(resultSet!=null) {
				resultSet.close();
			}
		}
	}

	protected ArrayList<Integer> _runQuery() throws SQLException{
		return this._runQueryHelper("SELECT * FROM Game", "game_id");
	}

		/*
			Run Insert query
	 */

	protected boolean _insertQuery(Game game) throws SQLException{
		String sqlQuery="INSERT INTO Game (game_title) " +
												"VALUES (?)";
		ResultSet keys=null;
		try
				(
						Connection connection=DBConfig.getConnection();
						PreparedStatement statement =connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)
				)
		{
			statement.setString(1, game.getGame_title());


			//test number of affected rows
			if (statement.executeUpdate()==1){
				keys=statement.getGeneratedKeys();
				keys.next();
				int newKey=keys.getInt(1);
				game.setGame_id(newKey);
			}
			else {
				System.err.println("No Rows Affected");
			}
		}
		catch (SQLException e){
			DBConfig.displayException(e);
			return false;
		}
		finally {
			if (keys!=null){
				keys.close();
			}
		}

		return true;
	}

	/*
			Run Update query
	 */
	protected boolean _updateQuery(Game game) throws SQLException{
		String sqlQuery="UPDATE Game SET " +
												"game_title=? " +
												"WHERE game_id=?";

		try
				(
						Connection connection=DBConfig.getConnection();
						PreparedStatement statement=connection.prepareStatement(sqlQuery)
				)
		{
			statement.setString(1, game.getGame_title());
			statement.setInt(2,game.getGame_id());


			return statement.executeUpdate() == 1;
		}
		catch (SQLException e){
			DBConfig.displayException(e);
			return false;
		}
	}
}
