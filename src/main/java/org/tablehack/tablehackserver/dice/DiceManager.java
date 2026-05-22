package org.tablehack.tablehackserver.dice;

import org.springframework.stereotype.Component;

@Component
public class DiceManager {
	Dice melee = new Dice("Melee", ",,1,1,1*,break".split(","));
	Dice range = new Dice("Range", ",,,,1,1*".split(","));
	Dice electricity = new Dice("Electricity", ",stun,stun,,1spread,1spread,1spread,2spread,3spread,1,1,1".split(","));
	Dice cold = new Dice("Cold", ",stun,stun,,1,1,1,2,1,1,1,1".split(","));
	
	

}
