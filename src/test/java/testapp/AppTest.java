package testapp;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samknows.MetricAnalyzer;
import com.samknows.model.DataMetricsModel;
import com.samknows.model.MetricsResultsModel;

import junit.framework.Assert;

public class AppTest {

	private List<DataMetricsModel> metrics1List;
	
	private MetricAnalyzer analyzer;

	@Before
    public void setUp() {
		
//        headers.setContentType(MediaType.APPLICATION_JSON);
        
        /** Load json **/
		Resource inputs1 = new ClassPathResource("inputs/1.json");
		Resource inputs2 = new ClassPathResource("inputs/2.json");
		
		
		InputStream inputStream = null;
		try {
			
			inputStream = inputs1.getInputStream();
			
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
		
		this.metrics1List = Arrays.asList(metrics1);
		
    }
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testAverage() {
		
		if (this.analyzer == null) {
			
			this.analyzer = new MetricAnalyzer(metrics1List);
		}
		
		this.analyzer.loadMetrics();
		
		final MetricsResultsModel results = this.analyzer.getMetricsResults();
		
		Assert.assertEquals(12.84, results.getAverage());
		
	}

}
