package org.tablehack.tablehackserver.dice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class Dice {
	
	public static class Side {
		int value = 0;
		boolean exploding = false;
		String effect = null;
		
		Side(String str) {
			if (str.length() > 0) {
				if (Character.isDigit(str.charAt(0))) {
					value = str.charAt(0) - '0';
					str = str.substring(1);
				}
				if (str.length() > 0) {
					if (str.charAt(0) == '*') {
						exploding = true;
						str = str.substring(1);
							
					}
					if (str.length() > 0) {
						effect = str;
					}
				}
			}
		}
		
	}
	
	
	String name;
	List<Side> sides;
	
	public Dice(String name, String ... sideStrs ) {
		this.name = name;
		sides = List.of(sideStrs).stream().map(str -> new Side(str)).toList();
	}
	
	public RollResult roll(int count) {
		RollResult result = new RollResult();
		for (int i = 0 ; i < count ; ++i) {
			rollAndAddToResult(result);
		}
		return result;
	}
	
	static Random random = new Random();
	
	void rollAndAddToResult(RollResult result) {
		int roll = random.nextInt(0, sides.size());
		Side side = sides.get(roll);
		result.addRoll(side);
		if (side.exploding) {
			rollAndAddToResult(result);
		}
	}
	
	
	
	
	
}
