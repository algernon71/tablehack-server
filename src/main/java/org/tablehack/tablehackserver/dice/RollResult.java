package org.tablehack.tablehackserver.dice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.tablehack.tablehackserver.dice.Dice.Side;

import lombok.Getter;


@Getter
public class RollResult {
	int value = 0;
	int totalRolls = 0;
	int explodeCount = 0;
	Map<String, Integer> effects = new HashMap<>(); 
	
	List<Side> rolls = new ArrayList<>();
	
	public  void print() {
		System.out.println("Roll result:" + value);
		System.out.println("Total dice rolled:" + totalRolls);
		System.out.println("Exploded:" + explodeCount);
		System.out.println("Effects:");
		effects.entrySet().forEach(entry -> {
			System.out.println(" " + entry.getKey() + ":" + entry.getValue());
		});
		
	}
	public String toString() {
		return "Result: " + value + 
				" " + effects.entrySet().stream()
				.map(entry -> entry.getKey() + ":" + entry.getValue())
				.collect(Collectors.joining(" "));
	}
	protected void addRoll(Side side) {
		rolls.add(side);
		this.value += side.value;
		this.totalRolls++;
		if (side.exploding) {
			this.explodeCount++;
		}
		
		if (side.effect != null) {
			Integer effectCount = effects.get(side.effect);
			if (effectCount == null) {
				effects.put(side.effect, 1);
			} else {
				effects.put(side.effect, effectCount + 1);
			}
		}
	}
}