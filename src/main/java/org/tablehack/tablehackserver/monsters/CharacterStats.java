package org.tablehack.tablehackserver.monsters;

import java.util.List;

import lombok.Data;


@Data
public class CharacterStats  {
	CharacterStat health;
	CharacterStat strength;
	CharacterStat agility;
	CharacterStat mana;
	CharacterStat percention;
	CharacterStat luck;
}
