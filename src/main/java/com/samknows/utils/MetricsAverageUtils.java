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
		
		sum.setScale(2);
		
		metricsList.forEach((metric) -> {
			
			BigDecimal tally = new BigDecimal(sum.doubleValue()).setScale(2, 2);
			sum = tally.add(metric.getMetricValue());
			
			datasetCount++;
			
		});
		sum.setScale(13, 2);
		
 		/** Get the average **/
// 		BigDecimal average = new BigDecimal(sum.doubleValue() / datasetCount).setScale(2, 2);
 		BigDecimal average = new BigDecimal(sum.doubleValue() / datasetCount).setScale(2, BigDecimal.ROUND_HALF_UP);
 		
 		final Double doubleAsMbps = average.doubleValue() / 1000000;
 		final Double averageRounded = Math.round( doubleAsMbps * 100.0 ) / 100.0;
 		return averageRounded;
		
	}

}
