/**
 * 
 */
package com.samknows.model;

/**
 * @author caspervice
 *
 */
public class MetricsResultsModel {
	
	private Double average;
	
	private Double min;
	
	private Double max;
	
	private Double median;
	
	private String from;
	
	private String to;
	
	public MetricsResultsModel(Double average, Double min, Double max, Double median, String from, String to) {
		super();
		this.average = average;
		this.min = min;
		this.max = max;
		this.median = median;
		this.from = from;
		this.to = to;
	}

	public Double getAverage() {
		return average;
	}

	public void setAverage(Double average) {
		this.average = average;
	}

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

	public Double getMedian() {
		return median;
	}

	public void setMedian(Double median) {
		this.median = median;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
	
	

}
