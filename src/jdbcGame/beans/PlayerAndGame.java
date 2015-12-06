package jdbcGame.beans;

/*
 * Created by Aaron Fernandes(300773526) on December 2015.
 */

import java.sql.Date;

public class PlayerAndGame {

	private int player_game_id;
	private int game_id;
	private int player_id;
	private Date playing_date;
	private int score;
	private String game_title;

	public int getGame_id() {
		return game_id;
	}

	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}

	public int getPlayer_game_id() {
		return player_game_id;
	}

	public void setPlayer_game_id(int player_game_id) {
		this.player_game_id = player_game_id;
	}

	public int getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}

	public Date getPlaying_date() {
		return playing_date;
	}

	public void setPlaying_date(Date playing_date) {
		this.playing_date = playing_date;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}


	public String getGame_title() {
		return game_title;
	}

	public void setGame_title(String game_title) {
		this.game_title = game_title;
	}
}
