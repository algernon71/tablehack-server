package org.tablehack.tablehackserver.data;

import java.util.ArrayList;
import java.util.List;

import org.tablehack.tablehackserver.data.actions.Action;
import org.tablehack.tablehackserver.data.actions.Skill;

import lombok.Data;

@Data
public class CharacterData  {
	CharacterStats stats = new CharacterStats();
	List<Action> actions = new ArrayList<>();
	List<Skill> skills = new ArrayList<>();
}
