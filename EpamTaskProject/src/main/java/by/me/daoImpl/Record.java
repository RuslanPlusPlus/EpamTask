package by.me.daoImpl;

public class Record implements Comparable<Record>{

	private int id = 0;
	private String playerName;
	private int steps;

	
	public Record(String playerName, int steps) {
		this.playerName = playerName;
		this.steps = steps;
	}

	public String getPlayerName() {
		return playerName;
	}

	public int getSteps() {
		return steps;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}

	@Override
	public int compareTo(Record o) {
		return this.getSteps() - o.getSteps();
	}
}
