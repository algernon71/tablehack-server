package org.tablehack.tablehackserver.dice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


import lombok.val;

public class DiceRoller {

	public static void main(String[] args) {
		int diceCount = Integer.parseInt(args[0]);
		int rollCount = Integer.parseInt(args[1]);
		Dice dices[] = {
				new Dice("Melee", ",,1,1,1*,break".split(",")),
				new Dice("Range", ",,,,1,1*".split(",")),
				new Dice("Electricity", ",stun,stun,,1spread,1spread,1spread,2spread,3spread,1,1,1".split(",")),
				new Dice("Cold", ",stun,stun,,1,1,1,2,1,1,1,1".split(","))
		};
		
		
		for (Dice dice : dices) {
			testRoll(dice, diceCount, rollCount);
		}
		
	}
	
	public static void testRoll(Dice dice, int diceCount, int rollCount) {
		double sum = 0;
		int maxValue = 0;
		int totalEffects = 0;
		Map<Integer, Integer> pdf = new HashMap<>();
		Map<String, Map<Integer, Integer>> effectsPdfs = new HashMap<>();
		for (int i = 0 ; i < rollCount ; ++i) {
			RollResult result = dice.roll(diceCount);
			// System.out.println(result.toString());
			sum += result.value;
			if (result.value > maxValue) {
				maxValue = result.value;
			}
			Integer valueCount = pdf.get(result.value);
			if (valueCount == null) {
				pdf.put(result.value, 1);
			} else {
				pdf.put(result.value, valueCount + 1);
			}
			result.effects.entrySet().forEach(entry -> {
				Map<Integer, Integer> effectsPdf = effectsPdfs.get(entry.getKey());
				if (effectsPdf == null) {
					effectsPdf = new HashMap<>();
					effectsPdfs.put(entry.getKey(), effectsPdf);
					
				}
				Integer effectCount = effectsPdf.get(entry.getValue());
				if (effectCount == null) {
					effectsPdf.put(entry.getValue(), 1);
				} else {
					effectsPdf.put(entry.getValue(), effectCount + 1);
				}
				
			});
			
		}
		// System.out.println(effectsPdfs.toString());
		double average = sum / rollCount;
		System.out.println("=".repeat(20));
		System.out.println("" + dice.getName()  + " " + dice.getSides().size() + " sides");
		/*
		dice.getSides().forEach(side -> {
			System.out.println(side.toString());
		});*/
		System.out.println("=".repeat(20));
		System.out.println("Average: " + average);
		System.out.println("Average per dice: " + average / diceCount);
		System.out.println("Max: " + maxValue);
		
		for (int i = 0 ; i <= maxValue ; ++i) {
			
			Integer valueCount = pdf.get(i);
			if (valueCount == null) {
				valueCount = 0;
			}
			double percentage = (double)valueCount / rollCount;
			int numberOfMarkers = (int)Math.round(100 * percentage);
			System.out.println(i + " " + "+".repeat(numberOfMarkers) + " (" + percentage * 100 + "%)");
		}
		
		effectsPdfs.entrySet().forEach(entry -> {
			int totalSum = entry.getValue().entrySet().stream()
					.map(e -> e.getKey() * e.getValue())
					.reduce(0, (a, b) -> a + b);
			int maxCount = entry.getValue().keySet().stream().mapToInt(v -> v).max().orElseThrow();
			double effectAverage = (double) totalSum / rollCount;
			
			System.out.println(entry.getKey() + " - Average:" + effectAverage);
			for (int i = 0 ; i <= maxCount; ++i) {
				Integer valueCount = entry.getValue().get(i);
				if (valueCount == null) {
					valueCount = 0;
				}
				double percentage = (double)valueCount / rollCount;
				int numberOfMarkers = (int)Math.round(100 * percentage);
				System.out.println(i + " " + "+".repeat(numberOfMarkers) + " (" + percentage * 100 + "%)");
				
			}
		});
		
	}

}
