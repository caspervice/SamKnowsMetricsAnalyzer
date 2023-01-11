package testapp;

import static org.junit.Assert.fail;

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
//		Resource inputs2 = new ClassPathResource("inputs/2.json");
		
		
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
	public void testAverage() {
		
		if (this.analyzer == null) {
			
			this.analyzer = new MetricAnalyzer(metrics1List);
		}
		
		this.analyzer.loadMetrics();
		
		final MetricsResultsModel results = this.analyzer.getMetricsResults();
		
		Assert.assertEquals(12.84, results.getAverage());
		
	}
	
	@Test
	public void testMin() {
		
		if (this.analyzer == null) {
			
			this.analyzer = new MetricAnalyzer(metrics1List);
		}
		
		this.analyzer.loadMetrics();
		
		final MetricsResultsModel results = this.analyzer.getMetricsResults();
		
		Assert.assertEquals(12.66, results.getMin());
		
	}
	
	@Test
	public void testMax() {
		
		if (this.analyzer == null) {
			
			this.analyzer = new MetricAnalyzer(metrics1List);
		}
		
		this.analyzer.loadMetrics();
		
		final MetricsResultsModel results = this.analyzer.getMetricsResults();
		
		Assert.assertEquals(13.01, results.getMax());
		
	}
	
	@Test
	public void testMedian() {
		
		if (this.analyzer == null) {
			
			this.analyzer = new MetricAnalyzer(metrics1List);
		}
		
		this.analyzer.loadMetrics();
		
		final MetricsResultsModel results = this.analyzer.getMetricsResults();
		
		Assert.assertEquals(12.86, results.getMedian());
		
	}
	
	@Test
	public void testPeriod() {
		
		if (this.analyzer == null) {
			
			this.analyzer = new MetricAnalyzer(metrics1List);
		}
		
		this.analyzer.loadMetrics();
		
		final MetricsResultsModel results = this.analyzer.getMetricsResults();
		
		Assert.assertEquals("2018-01-29", results.getFrom());
		Assert.assertEquals("2018-02-27", results.getTo());
		
	}

}
