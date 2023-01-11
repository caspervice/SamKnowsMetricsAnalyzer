/**
 * 
 */
package com.samknows.utils;

import java.math.BigDecimal;
import java.util.List;

import com.samknows.model.DataMetricsModel;

/**
 * @author caspervice
 *
 */
public class MetricsAverageUtils {
	
	private static BigDecimal sum = new BigDecimal(0);
	
	private static int datasetCount = 0;
	
	public static Double generateAverageResults(List<DataMetricsModel> metricsList) {
		
		System.out.println("Sum before tally: "+ sum);
		
		sum.setScale(2);
		
		metricsList.forEach((metric) -> {
			
			System.out.println("metric value: " + metric.getMetricValue());
			
			BigDecimal tally = new BigDecimal(sum.doubleValue()).setScale(2, 2);
//			tally.setScale(13, 2);
			sum = tally.add(metric.getMetricValue());
			
			System.out.println("New sum: " + sum);
			datasetCount++;
			
		});
		sum.setScale(13, 2);
		
 		System.out.println("New total sum: " + sum);
 		
 		/** Get the average **/
// 		BigDecimal average = new BigDecimal(sum.doubleValue() / datasetCount).setScale(2, 2);
 		BigDecimal average = new BigDecimal(sum.doubleValue() / datasetCount).setScale(2, BigDecimal.ROUND_HALF_UP);
 		
 		final Double doubleAsMbps = average.doubleValue() / 1000000;
 		final Double averageRounded = Math.round( doubleAsMbps * 100.0 ) / 100.0;
 		
 		System.out.println("Average of the dataset: " + averageRounded);
		System.out.println();
		
//		return "Average: " + averageRounded;
		return averageRounded;
		
	}

}
