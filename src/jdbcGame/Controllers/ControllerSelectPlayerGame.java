package jdbcGame.Controllers;

/*
 * Created by Aaron Fernandes(300773526) on December 2015.
 */

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import jdbcGame.DBConfig;
import jdbcGame.beans.PlayerAndGame;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerSelectPlayerGame extends ControllerSqlSelectPlayer implements Initializable, EventHandler<Event>{

	@FXML private ComboBox<Integer> selPlayer;
	@FXML private Label messageLabel;
	@FXML private GridPane gameGrid;
	private int rowCount=1;

	/**
	 * populates a list of games for the user to select
	 * @param url
	 * @param resourceBundle
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		selPlayer.setItems(FXCollections.observableArrayList(this._runSql()));
	}

	/**
	 * adds rows to the scroll Pane when a player is selected
	 * @throws SQLException
	 */
	public void selPlayerHandler() throws SQLException {
		int playerID=selPlayer.getSelectionModel().getSelectedItem();
		ArrayList<PlayerAndGame> playerAndGames = this._getPlayerGamesById(playerID);
		ArrayList<String> gameList=this._getGamesList();

		// Remove event handlers ☺
		for (Node n: gameGrid.getChildren()){
			n.removeEventHandler(ActionEvent.ACTION,this);
			n.removeEventHandler(KeyEvent.KEY_PRESSED,this);
		}
		gameGrid.getChildren().clear();

		//Sets the player name to a label
		try {
			messageLabel.setText("Games played by: "+this._getNameByID(playerID));
		} catch (SQLException e) {
			DBConfig.displayException(e);
		}

		//Add labels
		gameGrid.add(new Label("Game"),0,0);
		gameGrid.add(new Label("Playing Date"),1,0);
		gameGrid.add(new Label("Score"),2,0);

		//add rows
		for (int i=0; i<playerAndGames.size();i++){
			rowCount++;
			PlayerAndGame plyAndGame=playerAndGames.get(i);

			//Combo Box
			ComboBox<String> gameCbx=new ComboBox<>();
			gameCbx.setItems(FXCollections.observableArrayList(gameList));
			gameCbx.addEventHandler(ActionEvent.ACTION,this);
			gameCbx.setValue(plyAndGame.getGame_title());
			gameCbx.setTooltip(new Tooltip(String.format("%d", plyAndGame.getPlayer_game_id())));

			//Date Picker
			DatePicker datepicker=new DatePicker();
			datepicker.addEventHandler(ActionEvent.ACTION,this);
			datepicker.setValue(plyAndGame.getPlaying_date().toLocalDate());
			datepicker.setTooltip(new Tooltip(String.format("%d", plyAndGame.getPlayer_game_id())));

			//Text Field to set score
			TextField textfield=new TextField(String.format("%d", plyAndGame.getScore()));
			textfield.addEventHandler(KeyEvent.KEY_RELEASED,this);
			textfield.setTooltip(new Tooltip(String.format("%d", plyAndGame.getPlayer_game_id())));

			gameGrid.add(gameCbx, 0,i+1);
			gameGrid.add(datepicker, 1,i+1);
			gameGrid.add(textfield, 2,i+1);
		}

	}

	/**
	 * Adds a new
	 */
	public void addRowHandler(){
		//Combo Box
		ComboBox<String> gameCbx=new ComboBox<>();
		gameCbx.setItems(FXCollections.observableArrayList(this._getGamesList()));
		gameCbx.addEventHandler(ActionEvent.ACTION,this);

		//Date Picker
		DatePicker datepicker=new DatePicker();
		datepicker.addEventHandler(ActionEvent.ACTION,this);

		//Text Field to set score
		TextField textfield=new TextField();
		textfield.addEventHandler(KeyEvent.KEY_RELEASED,this);

		gameGrid.add(gameCbx,0,rowCount);
		gameGrid.add(datepicker,1,rowCount);
		gameGrid.add(textfield,2,rowCount);

		rowCount++;
	}

	/**
	 * Crazy custom handler that handles all the events
	 * @param event
	 */
	@Override
	public void handle(Event event) {
		//handle Combo Box events
		if (event.getSource() instanceof ComboBox){
			int playerGameId =Integer.parseInt(((ComboBox) event.getSource()).getTooltip().getText());
			int id=((ComboBox) event.getSource()).getSelectionModel().getSelectedIndex();
			this.setNewGame(id+1,playerGameId);
		}
		//handel DatePicker events
		else if (event.getSource() instanceof DatePicker){
			int id=Integer.parseInt(((DatePicker) event.getSource()).getTooltip().getText());
			LocalDate localDatedate=((DatePicker) event.getSource()).getValue();
			Instant instant=Instant.from(localDatedate.atStartOfDay(ZoneId.systemDefault()));
			java.util.Date utilDate=java.util.Date.from(instant);

			Date date=new Date(utilDate.getTime());

			this.setNewDate(id,date);

		}
		//handle TextField events
		else if (event.getSource() instanceof TextField){
			int id=Integer.parseInt(((TextField) event.getSource()).getTooltip().getText());
			int score=Integer.parseInt(((TextField) event.getSource()).getText());

			if (id!=0 || score!=0) {
				System.out.println(this.setNewScore(id, score));
			}

		}//if TextField
	}

}
