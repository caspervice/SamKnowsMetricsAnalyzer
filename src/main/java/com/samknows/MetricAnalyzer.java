/**
 * 
 */
package com.samknows;

import java.util.List;

import com.samknows.model.DataMetricsModel;
import com.samknows.model.MetricsResultsModel;
import com.samknows.utils.MetricMinMaxUtils;
import com.samknows.utils.MetricsAverageUtils;
import com.samknows.utils.PeriodUtils;

/**
 * @author caspervice
 *
 */
public class MetricAnalyzer {
	
	private List<DataMetricsModel> metricsList;
	
	private Double average;
	
	private Double min;
	
	private Double max;
	
	private Double median;
	
	private String from;
	
	private String to;
	
	private MetricsResultsModel model;
	
	public MetricAnalyzer(List<DataMetricsModel> metrics1List) {
		
		this.metricsList = metrics1List;
	}
	
	public void loadMetrics() {
		
		/** average **/
		this.average = MetricsAverageUtils.generateAverageResults(metricsList);
		
		MetricMinMaxUtils.setupDataset(metricsList);
		
		this.min = MetricMinMaxUtils.generateMin();
		
		this.max = MetricMinMaxUtils.generateMax();
		
		this.median = MetricMinMaxUtils.generateMedian(metricsList);
		
		this.from = PeriodUtils.generateFromPeriod(metricsList);
		
		this.to = PeriodUtils.generateToPeriod(metricsList);
		
		this.model = new MetricsResultsModel(average, min, max, median, from, to);
	}
	
	public void printResults() {
		
		final String result = "SamKnows Metric Analyser v1.0.0"
				+ "\n===============================\n"
				+ "\n\nPeriod Checked: "
				+ "\n\nFrom: " + this.from
				+ "\nTo: " + this.to
				+ "\n\nStatistics:\r\n" + 
				"\r\n" + 
				"    Unit: Megabits per second"
				+ "\n\n\tAverage: " + this.average 
				+ "\n\tMin: " + this.min
				+ "\n\tMax: " + this.max
				+ "\n\tMedian: " + this.median
				+ "\n\nUnder-performing periods:";
		
		System.out.println(result);

		
//				+ this.averageResult
//				+ this.minMaxResult;
		
		
		
//		final String result = "SamKnows Metric Analyser v1.0.0"
//				+ "\n==============================="
//				+ this.periodResult
//				+ "\nStatistics:\r\n" + 
//				"\r\n" + 
//				"    Unit: Megabits per second"
//				+ this.averageResult
//				+ this.minMaxResult;
		
		
	}
	
	public MetricsResultsModel getMetricsResults() {
		
		return this.model;
	}

	
	

}
