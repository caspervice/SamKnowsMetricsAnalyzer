package com.samknows.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.samknows.model.DataMetricsModel;

public class MetricMinMaxUtils {
	
	private static Double minMetric;
	private static Double maxMetric;
	private static Double medianMetric;
	
	private static List<BigDecimal> metricsList;
	
	public static void setupDataset(List<DataMetricsModel> metrics1List) {
		
		if (minMetric != null) {
			
			minMetric = new Double(0);
			maxMetric = new Double(0);
			medianMetric = new Double(0);
			
		}
		
		final ArrayList<BigDecimal> tempList = new ArrayList<BigDecimal>();
		final ArrayList<Double> doubleList = new ArrayList<>();
		
		metrics1List.forEach((metric) -> {
			
			BigDecimal entry = new BigDecimal(metric.getMetricValue().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
			
			final Double doubleToAdd = entry.doubleValue() / 1000000;
			doubleList.add(doubleToAdd);
			
			tempList.add(entry);
			
		});
		
		/** Sort list. **/
		Collections.sort(tempList);
		
		metricsList = tempList;
		
		
	}
	
	public static Double generateMin() {
		
		/** Get value at index '0' **/
		final BigDecimal min = metricsList.get(0);
		
		final Double minAsMbps = min.doubleValue() / 1000000;
		minMetric = Math.round( minAsMbps * 100.0 ) / 100.0;
		
		return minMetric;
		
	}
	
	public static Double generateMax() {
		
		/** Get value at max index **/
		final BigDecimal max = metricsList.get(metricsList.size() -1);
		
		final Double maxAsMbps = max.doubleValue() / 1000000;
		
		maxMetric = Math.round( maxAsMbps * 100.0 ) / 100.0;
		
		return maxMetric;
		
	}
	
	public static String generateMinMax(List<DataMetricsModel> metrics1List) {
		
		if (minMetric != null) {
			
			minMetric = new Double(0);
			maxMetric = new Double(0);
			medianMetric = new Double(0);
		}
		
		final ArrayList<BigDecimal> tempList = new ArrayList<BigDecimal>();
		final ArrayList<Double> doubleList = new ArrayList<>();
		
		metrics1List.forEach((metric) -> {
			
			BigDecimal entry = new BigDecimal(metric.getMetricValue().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
			
			final Double doubleToAdd = entry.doubleValue() / 1000000;
			doubleList.add(doubleToAdd);
			
			tempList.add(entry);
			
		});
		
		/** Sort list. **/
		Collections.sort(tempList);
		
		/** Get value at index '0' **/
		final BigDecimal min = tempList.get(0);
		
		/** Get value at max index **/
		final BigDecimal max = tempList.get(tempList.size() -1);
		
		final Double minAsMbps = min.doubleValue() / 1000000;
		final Double maxAsMbps = max.doubleValue() / 1000000;
		
		minMetric = Math.round( minAsMbps * 100.0 ) / 100.0;
		maxMetric = Math.round( maxAsMbps * 100.0 ) / 100.0;
		
		return "Min: " + minMetric
				+ "\nMax: " + maxMetric;
		
	}
	
	public static Double generateMedian(List<DataMetricsModel> metricsList) {
		
		final ArrayList<Double> doubleList = new ArrayList<>();
		
		metricsList.forEach((metric) -> {
			
			BigDecimal entry = new BigDecimal(metric.getMetricValue().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
			
			final Double doubleToAdd = entry.doubleValue() / 1000000;
			doubleList.add(doubleToAdd);
			
			
		});
		
		/** Median **/
		int listSize = doubleList.size();
		int middleValue = listSize / 2;
		double median = 0;
		if((listSize % 2) == 0) {
			
			median = (doubleList.get(middleValue -1) + doubleList.get(middleValue)) / 2;
			
		} else {
			
			median = doubleList.get(middleValue);
			
		}
		
		/** Median value **/
		medianMetric = Math.round( median * 100.0 ) / 100.0;
		return medianMetric;
		
	}

}
