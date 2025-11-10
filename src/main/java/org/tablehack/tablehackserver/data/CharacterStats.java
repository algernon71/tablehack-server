package org.tablehack.tablehackserver.data;

import java.util.List;

import lombok.Data;


@Data
public class CharacterStats  {
	CharacterStat health = new CharacterStat();
	CharacterStat strength = new CharacterStat();
	CharacterStat agility  = new CharacterStat();
	CharacterStat mana  = new CharacterStat();
	CharacterStat percention  = new CharacterStat();
	CharacterStat luck  = new CharacterStat();
}
