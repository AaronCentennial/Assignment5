package jdbcGame.beans;

/*
 * Created by Aaron Fernandes(300773526) on December 2015.
 */
public class Game {

	//private varables
	private int game_id;
	private String game_title;

	//public getters
	public int getGame_id() {
		return game_id;
	}

	public String getGame_title() {
		return game_title;
	}

	//public setters
	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}

	public void setGame_title(String game_title) {
		this.game_title = game_title;
	}
}
