package org.tablehack.tablehackserver.data;

import java.util.ArrayList;
import java.util.List;

import org.tablehack.tablehackserver.data.actions.Action;
import org.tablehack.tablehackserver.encounter.EncounterType;

import lombok.Data;

@Data
public class SceneData  {
	List<EncounterType> encounterTypes = List.of();
	List<LootType> lootTypes = List.of();
	List<LocationEvent> eventTypes = List.of();
	List<LocationEvent> locationEvents = List.of();
	
}
