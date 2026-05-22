package org.tablehack.tablehackserver.db.entities;

import org.tablehack.tablehackserver.data.CharacterData;
import org.tablehack.tablehackserver.data.CharacterDataConverter;
import org.tablehack.tablehackserver.data.actions.Action;
import org.tablehack.tablehackserver.data.actions.ActionConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "character_actions")
@Data
public class PlayerAction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String characterClass;


	
	@Convert(converter = ActionConverter.class)
	@Lob
	@Column(columnDefinition="TEXT")
	Action action;
	
	public void update(PlayerAction action) {
		if (action.getAction() != null) {
			this.action = action.getAction();
		}
	}
	
	public boolean appliesTo(PlayerCharacter character) {
		if (characterClass == null || characterClass.equals("ALL")) {
			return true;
		}
		
		if (characterClass.equals(character.getCharacterClass())) {
			return true;
		}
		
		return false;
	}
	
}
