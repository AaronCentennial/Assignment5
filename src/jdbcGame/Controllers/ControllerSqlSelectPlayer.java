package jdbcGame.Controllers;

/*
 * Created by Aaron Fernandes(300773526) on December 2015.
 */

import jdbcGame.DBConfig;
import jdbcGame.beans.PlayerAndGame;

import java.sql.*;
import java.util.ArrayList;

public class ControllerSqlSelectPlayer extends ControllerRoot {


	/**
	 * Gets a list of player ID's. Bad name for a function I know
	 * @return returns an arrayList of player ID's
	 */
	protected ArrayList<Integer> _runSql(){
		ArrayList<Integer> PlayerIDs=new ArrayList<>();
		String sqlQuery="SELECT player_id FROM Player";

		try
			(
					Connection connection= DBConfig.getConnection();
					Statement statement=connection.createStatement();
					ResultSet resultSet=statement.executeQuery(sqlQuery)
			)
		{

			while (resultSet.next()){
					PlayerIDs.add(resultSet.getInt("player_id"));
			}

			return PlayerIDs;

		}
		catch (SQLException e){
			DBConfig.displayException(e);
			return null;
		}

	}

	/**
	 * Get java bean of Player and Game
	 * @param player_id			Player to return information for
	 * @return							returns an ArrayList of jaba bean PlayerAndGame
	 * @throws SQLException
	 */
	protected ArrayList<PlayerAndGame> _getPlayerGamesById(int player_id) throws SQLException {
		String sqlQuery="SELECT " +
												"player_game_id, " +
												"Game.game_id, " +
												"player_id, " +
												"playing_date, " +
												"score, " +
												"game_title " +
												"FROM PlayerAndGame " +
												"LEFT JOIN Game ON Game.game_id=PlayerAndGame.game_id " +
												"WHERE player_id=?";
		ResultSet resultSet = null;
		ArrayList<PlayerAndGame> playerAndGames=new ArrayList<>();
		try
			(
				Connection connection=DBConfig.getConnection();
				PreparedStatement statement=connection.prepareStatement(sqlQuery)
			)
		{
			statement.setInt(1, player_id);
			resultSet=statement.executeQuery();

			while (resultSet.next()){
				PlayerAndGame playerAndGame=new PlayerAndGame();

				playerAndGame.setPlayer_game_id(resultSet.getInt("player_game_id"));
				playerAndGame.setGame_id(resultSet.getInt("game_id"));
				playerAndGame.setPlayer_id(player_id);
				Date playDate=resultSet.getDate("playing_date");
				playerAndGame.setPlaying_date(playDate);
				playerAndGame.setScore(resultSet.getInt("score"));
				playerAndGame.setGame_title(resultSet.getString("game_title"));

				playerAndGames.add(playerAndGame);
			}

			return playerAndGames;
		}
		catch (SQLException e){
			DBConfig.displayException(e);
			return null;
		}
		finally {
			if (resultSet!=null){resultSet.close();}
		}
	}

	/**
	 * gets a list of games
	 * @return return a list of games
	 */
	protected ArrayList<String> _getGamesList(){
		ArrayList<String> games=new ArrayList<>();
		String sqlQuery ="select game_title from Game";
		try
			(
				Connection connection=DBConfig.getConnection();
				Statement statement=connection.createStatement();
				ResultSet resultSet=statement.executeQuery(sqlQuery)
			)
		{

			while (resultSet.next()){games.add(resultSet.getString("game_title"));}
			return games;
		}
		catch (SQLException e){
			DBConfig.displayException(e);
			return null;
		}

	}

	/**
	 * Gets a name from a player ID
	 * @param player_id				playerid to get games
	 * @return								returns the name of the player based on player id
	 * @throws SQLException
	 */
	protected String _getNameByID(int player_id) throws SQLException {
		String name="";
		String sqlQuery="SELECT first_name, last_name FROM Player WHERE player_id=?";
		ResultSet resultSet=null;
		try
			(
				Connection connection=DBConfig.getConnection();
				PreparedStatement statement=connection.prepareStatement(sqlQuery)

			)
		{
			statement.setInt(1, player_id);
			resultSet=statement.executeQuery();

			if (resultSet.next()){
				name=resultSet.getString("first_name")+" "+resultSet.getString("last_name");
			}
		}
		catch (SQLException e){
			DBConfig.displayException(e);
			return null;
		}
		finally {
			if (resultSet!=null){
				resultSet.close();
			}
		}

		return name;
	}

	/**
	 * Sets a new score
	 * @param id			PlayerAndGame id
	 * @param score		new score to set
	 * @return				returns true if update successful
	 */
	protected boolean _setNewScore(int id, int score){
		String sqlQuery="UPDATE PlayerAndGame SET score=? WHERE player_game_id=?";
		try
			(
				Connection connection=DBConfig.getConnection();
				PreparedStatement statement=connection.prepareStatement(sqlQuery)
			)
		{
			statement.setInt(1,score);
			statement.setInt(2, id);


			return (statement.executeUpdate()) == 1;

		}
		catch (SQLException e){
			DBConfig.displayException(e);
			return false;
		}
	}

	/**
	 * Sets a new game
	 * @param id						Game id
	 * @param playerGameId	PlayerAndGame id
	 * @return							Returns true if update successful
	 */
	protected boolean _setNewGame(int id, int playerGameId){
		String sqlQuery="UPDATE PlayerAndGame SET game_id=? WHERE player_game_id=?";
		try
		(
			Connection connection=DBConfig.getConnection();
			PreparedStatement statement=connection.prepareStatement(sqlQuery)
		)
		{
			statement.setInt(1,id);
			statement.setInt(2,playerGameId);

			int affectedrows=statement.executeUpdate();
			return affectedrows==1;

		}
		catch (SQLException e){
			DBConfig.displayException(e);
			return false;
		}
	}

	/**
	 * Sets a new Date
	 * @param id						PlayerAndGame id
	 * @param date					new score to set
	 * @return							Returns true if update successful
	 */
	protected boolean _setNewDate(int id, Date date){
		String sqlQuery="UPDATE PlayerAndGame SET playing_date=? WHERE player_game_id=?";
		try
			(
				Connection connection=DBConfig.getConnection();
				PreparedStatement statement=connection.prepareStatement(sqlQuery)
			)
		{
			statement.setDate(1,date);
			statement.setInt(2, id);

			return (statement.executeUpdate()) == 1;

		}
		catch (SQLException e){
			DBConfig.displayException(e);
			return false;
		}
	}

/*	String sqlQuery="INSERT INTO Player (first_name, last_name, address, postal_code, province) " +
											"VALUES (?, ?, ?, ?, ?)";*/

	protected int _insertEmptyRow(int playerID){
		String sqlQuery="INSERT INTO PlayerAndGame (player_id) VALUES (?)";
		ResultSet keys=null;
		try
			(
				Connection connection=DBConfig.getConnection();
				PreparedStatement statement=connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			)
		{
			statement.setInt(1, playerID);

			int affected =statement.executeUpdate();
			if (affected==1){
				keys=statement.getGeneratedKeys();
				keys.next();
				return keys.getInt(1);
			}
		}
		catch (SQLException e){
			DBConfig.displayException(e);
		}
		return -1;
	}
}
