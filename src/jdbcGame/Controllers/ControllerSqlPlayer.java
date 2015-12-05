package jdbcGame.Controllers;

import jdbcGame.DBConfig;
import jdbcGame.beans.Player;

import java.sql.*;
import java.util.ArrayList;

/*
 * Created by Aaron Fernandes(300773526) on December 2015.
 */
public class ControllerSqlPlayer extends ControllerRoot {
	/*
			Run Select query
	 */
	protected Player _runQuery(int playerID) throws SQLException {
		String sqlQuery= "SELECT * FROM Player WHERE player_id=?";
		ResultSet resultSet=null;
		Player player=new Player();
		try	(
						 Connection connection = DBConfig.getConnection();
						 PreparedStatement statement=connection.prepareStatement(sqlQuery)
		)
		{
			statement.setInt(1,playerID);
			resultSet=statement.executeQuery();
			resultSet.next();

			player.setPlayer_id(resultSet.getInt("player_id"));
			player.setFirst_name(resultSet.getString("first_name"));
			player.setLast_name(resultSet.getString("last_name"));
			player.setAddress(resultSet.getString("address"));
			player.setPostal_code(resultSet.getString("postal_code"));
			player.setProvince(resultSet.getString("province"));
			player.setPhone_number(resultSet.getString("phone_number"));

			return player;
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
		return this._runQueryHelper("SELECT * FROM Player", "player_id");
	}

	/*
			Run Insert query
	 */

	protected boolean _insertQuery(Player player) throws SQLException{
		String sqlQuery="INSERT INTO Player (first_name, last_name, address, postal_code, province) " +
												"VALUES (?, ?, ?, ?, ?)";
		ResultSet keys=null;
		try
				(
						Connection connection=DBConfig.getConnection();
						PreparedStatement statement =connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)
				)
		{
			statement.setString(1, player.getFirst_name());
			statement.setString(2, player.getLast_name());
			statement.setString(3, player.getAddress());
			statement.setString(4, player.getPostal_code());
			statement.setString(5, player.getProvince());

			//test number of affected rows
			if (statement.executeUpdate()==1){
				keys=statement.getGeneratedKeys();
				keys.next();
				int newKey=keys.getInt(1);
				player.setPlayer_id(newKey);
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
	protected boolean _updateQuery(Player player) throws SQLException{
		String sqlQuery="UPDATE Player SET " +
												"first_name=?, last_name=?, address=?, postal_code=?, province=?, phone_number=?" +
												"WHERE player_id=?";

		try
				(
						Connection connection=DBConfig.getConnection();
						PreparedStatement statement=connection.prepareStatement(sqlQuery)
				)
		{
			statement.setString(1, player.getFirst_name());
			statement.setString(2, player.getLast_name());
			statement.setString(3, player.getAddress());
			statement.setString(4, player.getPostal_code());
			statement.setString(5, player.getProvince());
			statement.setString(6, player.getPhone_number());
			statement.setInt(7, player.getPlayer_id());

			return statement.executeUpdate() == 1;
		}
		catch (SQLException e){
			DBConfig.displayException(e);
			return false;
		}
	}
}
