package by.me;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import by.me.dao.RecordsDAO;
import by.me.daoImpl.Record;
import by.me.model.PlayerStatistics;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameCycle {

	private ObservableList<PlayerStatistics> tableData = FXCollections.observableArrayList();
	private String input;
	private String generatedNumber;
	private String playerName;
	private boolean victory = false;
	
	private RecordsDAO dao;
	
	public void setNumber(String input) {
		this.input = input;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public void setDao(RecordsDAO dao) {
		this.dao = dao;
	}
	
	public GameCycle(String generatedNumber) {
		this.generatedNumber = generatedNumber;
	}
	
	public ObservableList<PlayerStatistics> getTableData(){
		return this.tableData;
	}
	
	public void setToOneStepsCounter() {
		PlayerStatistics.STEPS_COUNTER = 1;
	}
	
	//check for addition the same person with the same record

	public void analyse() {
		PlayerStatistics stat = new PlayerStatistics(this.input, this.generatedNumber);
		stat.countCowsAndBulls();
		this.tableData.add(stat);
		if(stat.getBulls() == 4) {
			this.victory = true;
			int steps = stat.getSteps();
			List<Record> records = this.dao.getRecords();
			if (records == null) {
				records = new ArrayList<Record>();
				
			}
			records.add(new Record(this.playerName, steps));
			Collections.sort(records);
			if (records.size() > 3) {
				records.remove(records.size() - 1);
			}
				
			this.dao.deleteAll();
			Iterator<Record> it = records.iterator();
			int counter = 0;
			while(it.hasNext() && counter < 3) {
				it.next();
				this.dao.add(records.get(counter));
				counter++;
			}
					
		}
	}
	
	public boolean isVictory() {
		return this.victory;
	}
}
