/**
 * 
 */
package com.samknows;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samknows.model.DataMetricsModel;

/**
 * @author caspervice
 *
 */
public class App {

	private static Logger logger = Logger.getLogger(App.class.getName());
	
	private static BigDecimal sum = new BigDecimal(0);
	
	private static int datasetCount = 0;
	
	private static Resource loadDatasetOne() {
		
		return new ClassPathResource("inputs/1.json");
		
	}
	
	private Resource loadDatasetTwo() {
		
		return new ClassPathResource("inputs/2.json");

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/** Load inputs. **/
		Resource questionsResource = loadDatasetOne();
		
		//create an input stream from classpath (required by 'Runnable Jars')
		InputStream inputStream = null;
		try {
			
			inputStream = questionsResource.getInputStream();
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
//		BigDecimal decimal
		
		DataMetricsModel [] metrics1 = null;
		try {
			
			metrics1 = mapper.readValue(inputStream, DataMetricsModel[].class);
			System.out.println("Metrics loaded.");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		final List<DataMetricsModel> metrics1List = Arrays.asList(metrics1);
		
		MetricAnalyzer metricAnalyzer = new MetricAnalyzer(metrics1List);
		metricAnalyzer.loadMetrics();
		
		metricAnalyzer.printResults();
		
		/** Generate the average **/
//		generateAverage(metrics1List);
		
 		/** Get Min and Max **/
//		generateMinMax(metrics1List);
		
		/** Timestamp period **/
//		generatePeriod(metrics1List);
		
		

	}

	private static void generatePeriod(List<DataMetricsModel> metrics1List) {
		
		final ArrayList<LocalDate> period = new ArrayList<LocalDate>();
		
		metrics1List.forEach((metric) -> {
			
			final LocalDate date = LocalDate.parse(metric.getDtime());
			
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
		
		
	}

	private static void generateMinMax(List<DataMetricsModel> metrics1List) {
		
		final ArrayList<BigDecimal> tempList = new ArrayList<BigDecimal>();
		final ArrayList<Double> doubleList = new ArrayList<>();
		
		metrics1List.forEach((metric) -> {
			
//			BigDecimal decimal = new BigDecimal(metric.getMetricValue().doubleValue()).setScale(2, 2);
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
		
		final Double minRounded = Math.round( minAsMbps * 100.0 ) / 100.0;
		final Double maxRounded = Math.round( maxAsMbps * 100.0 ) / 100.0;
		
		System.out.println("Min: " + minRounded + " max: " + maxRounded);
		
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
		final Double medianRounded = Math.round( median * 100.0 ) / 100.0;
		System.out.println("Median: " + medianRounded);
		
		
	}

	private static void generateAverage(List<DataMetricsModel> metrics1List) {
		
		System.out.println("Sum before tally: "+ sum);
		
		sum.setScale(2);
		
		metrics1List.forEach((metric) -> {
			
			System.out.println("metric value: " + metric.getMetricValue());
			
			BigDecimal tally = new BigDecimal(sum.doubleValue()).setScale(2, 2);
//			tally.setScale(13, 2);
			sum = tally.add(metric.getMetricValue());
			
//			sum.add(metric.getMetricValue());
			System.out.println("New sum: " + sum);
			datasetCount++;
			
		});
		sum.setScale(13, 2);
		
 		System.out.println("New total sum: " + sum);
 		
 		/** Get the average **/
// 		BigDecimal average = new BigDecimal(sum.doubleValue() / datasetCount).setScale(2, 2);
 		BigDecimal average = new BigDecimal(sum.doubleValue() / datasetCount).setScale(2, BigDecimal.ROUND_HALF_UP);
 		
 		//https://stackoverflow.com/questions/53096684/converting-byte-to-megabyte-in-java-getting-zero
 		final Double doubleAsMbps = average.doubleValue() / 1000000;
 		
 		//https://stackoverflow.com/questions/4796746/double-value-to-round-up-in-java
 		final Double averageRounded = Math.round( doubleAsMbps * 100.0 ) / 100.0;
 		
 		System.out.println("Average of the dataset: " + averageRounded);
		System.out.println();
		
	}

}
