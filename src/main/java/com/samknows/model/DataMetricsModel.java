/**
 * 
 */
package com.samknows.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author caspervice
 *
 */
@NoArgsConstructor
@AllArgsConstructor
public class DataMetricsModel {
	
	@Getter
	@Setter
//	private Double metricValue;
	private BigDecimal metricValue;
	
	@Getter
	@Setter
	private String dtime;

}
