package by.me.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlayerStatistics {
	
	public static int STEPS_COUNTER = 1;
	
	private String generatedNumber;
	
	private String input;
	private StringProperty numberProperty;
	
	private int cows;
	private StringProperty cowsProperty;
	
	private int bulls;
	private StringProperty bullsProperty;
	
	private int steps;
	private StringProperty stepsProperty;
	
	public PlayerStatistics(String input, String generatedNumber) {
		this.steps = STEPS_COUNTER++;
		this.numberProperty = new SimpleStringProperty();
		this.cowsProperty = new SimpleStringProperty();
		this.bullsProperty = new SimpleStringProperty();
		this.stepsProperty = new SimpleStringProperty();
		this.stepsProperty.set(Integer.toString(this.steps));
		this.input = input;
		this.numberProperty.set(input);
		this.generatedNumber = generatedNumber;
	}

	
	public int getBulls() {
		return this.bulls;
	}
	
	public StringProperty number() {
		return this.numberProperty;
	}
	
	public StringProperty cows() {
		return this.cowsProperty;
	}
	
	public StringProperty bulls() {
		return this.bullsProperty;
	}
	
	public StringProperty steps() {
		return this.stepsProperty;
	}
	
	public void countCowsAndBulls() {
		this.cows = 0;
		this.bulls = 0;
		for (int i = 0; i < this.input.length(); i++) {
			for (int j = 0; j < this.generatedNumber.length(); j++) {
				if(this.input.charAt(i) == this.generatedNumber.charAt(j) && i == j) {
					this.bulls++;
				}
				else if(this.input.charAt(i) == this.generatedNumber.charAt(j)) {
					this.cows++;
				}
			}
		}
		
		this.cowsProperty.set(Integer.toString(cows));
		this.bullsProperty.set(Integer.toString(bulls));
	}
	
	public int getSteps() {
		return this.steps;
	}
	
}
