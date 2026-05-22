package org.tablehack.tablehackserver.data;

import java.util.List;

import lombok.Data;


@Data
public class ProgressionStat  {
	Integer startValue;
	Integer maxValue;
	String progression;
	List<Integer> upgradeCosts = List.of();
}
