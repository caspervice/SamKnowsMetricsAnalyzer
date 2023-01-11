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
	
	private static Resource loadDatasetOne() {
		
		return new ClassPathResource("inputs/1.json");
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/** Load inputs. **/
		Resource inputs1 = loadDatasetOne();
		
		//create an input stream from classpath (required by 'Runnable Jars')
		InputStream inputStream = null;
		try {
			
			inputStream = inputs1.getInputStream();
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		DataMetricsModel [] metrics1 = null;
		try {
			
			metrics1 = mapper.readValue(inputStream, DataMetricsModel[].class);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		final List<DataMetricsModel> metrics1List = Arrays.asList(metrics1);
		
		MetricAnalyzer metricAnalyzer = new MetricAnalyzer(metrics1List);
		metricAnalyzer.loadMetrics();
		
		metricAnalyzer.printResults();
		
	}

}
