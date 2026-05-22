package org.tablehack.tablehackserver.data;

import java.util.List;

import lombok.Data;


@Data
public class CharacterStats  {
	ProgressionStat health = new ProgressionStat();
	ProgressionStat strength = new ProgressionStat();
	ProgressionStat agility  = new ProgressionStat();
	ProgressionStat mana  = new ProgressionStat();
	ProgressionStat percention  = new ProgressionStat();
	ProgressionStat luck  = new ProgressionStat();
}
