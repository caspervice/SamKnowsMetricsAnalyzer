package com.samknows.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.joda.time.LocalDate;

import com.samknows.model.DataMetricsModel;

public class PeriodUtils {
	
	public static String generatePeriod(List<DataMetricsModel> metrics1List) {
		
		final ArrayList<LocalDate> period = new ArrayList<LocalDate>();
		
		metrics1List.forEach((metric) -> {
			
			period.add(LocalDate.parse(metric.getDtime()));
			
		});
		
		Collections.sort(period);
		
		/** From & to **/
		final String from = period.get(0).toString();
		final String to = period.get(period.size() - 1).toString();
		
		System.out.println("Period checked:" 
				+ "\nFrom: " + from
				+ "\nTo: " + to);
		System.out.println();
		
		return "Period checked:" 
				+ "\nFrom: " + from
				+ "\nTo: " + to;
		
	}
	
	public static String generateFromPeriod(List<DataMetricsModel> metricsList) {
		
		final ArrayList<LocalDate> period = new ArrayList<LocalDate>();
		
		metricsList.forEach((metric) -> {
			
			period.add(LocalDate.parse(metric.getDtime()));
			
		});
		
		Collections.sort(period);
		
		/** From & to **/
		final String from = period.get(0).toString();
		
		return from;
		
	}
	
	public static String generateToPeriod(List<DataMetricsModel> metricsList) {
		
		final ArrayList<LocalDate> period = new ArrayList<LocalDate>();
		
		metricsList.forEach((metric) -> {
			
			period.add(LocalDate.parse(metric.getDtime()));
			
		});
		
		Collections.sort(period);
		
		final String to = period.get(period.size() - 1).toString();
		
		return to;
		
	}
	
	

}
