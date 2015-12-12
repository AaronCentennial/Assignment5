package jdbcGame.Controllers;

/*
 * Created by Aaron Fernandes(300773526) on December 2015.
 */

import jdbcGame.DBConfig;
import jdbcGame.beans.PlayerAndGame;

import java.sql.*;
import java.util.ArrayList;

public class ControllerSqlSelectPlayer extends ControllerRoot {

	protected int _playerID;

	// Gets a list of player ID's
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

	// Gets a java bean
	protected ArrayList<PlayerAndGame> _getPlayerGamesById(int player_id) throws SQLException {
		String sqlQuery="SELECT player_game_id, Game.game_id, player_id, playing_date, score, game_title FROM PlayerAndGame JOIN Game ON Game.game_id=PlayerAndGame.game_id WHERE player_id=?";
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

	// Gets a list of all games
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

	// Gets a name from a player ID
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

	// sets a new score
	protected boolean setNewScore(int id, int score){
		String sqlQuery="UPDATE PlayerAndGame SET score=? WHERE player_game_id=?";
		try
			(
				Connection connection=DBConfig.getConnection();
				PreparedStatement statement=connection.prepareStatement(sqlQuery);
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

	protected boolean setNewGame(int id, int playerGameId){
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

	protected boolean setNewDate(int id, Date date){
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
}
