package com.exam.modal;

import java.util.*;

public class AverageResponse {

	 private List<Integer> numbers;
	    private List<Integer> windowPrevState;
	    private List<Integer> windowCurrState;
	    private double avg;

	    // Getters and setters
	    public List<Integer> getNumbers() {
	        return numbers;
	    }

	    public void setNumbers(List<Integer> numbers) {
	        this.numbers = numbers;
	    }

	    public List<Integer> getWindowPrevState() {
	        return windowPrevState;
	    }

	    public void setWindowPrevState(List<Integer> windowPrevState) {
	        this.windowPrevState = windowPrevState;
	    }

	    public List<Integer> getWindowCurrState() {
	        return windowCurrState;
	    }

	    public void setWindowCurrState(List<Integer> windowCurrState) {
	        this.windowCurrState = windowCurrState;
	    }

	    public double getAvg() {
	        return avg;
	    }

	    public void setAvg(double avg) {
	        this.avg = avg;
	    }
	    
	    
	    
}
