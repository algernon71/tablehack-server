package org.tablehack.tablehackserver.db.entities;

import java.util.List;

import org.springframework.data.annotation.Transient;
import org.tablehack.tablehackserver.data.CharacterData;
import org.tablehack.tablehackserver.data.CharacterDataConverter;

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
@Table(name = "characters")
@Data
public class PlayerCharacter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String characterClass;
	private String race;
	private String image;

	@Lob
	@Column(columnDefinition="TEXT")
	private String description;

	
	@Convert(converter = CharacterDataConverter.class)
	@Lob
	@Column(columnDefinition="TEXT")
	CharacterData data = new CharacterData();
	
	@jakarta.persistence.Transient
	List<PlayerAction> standardActions;
	
	public void update(PlayerCharacter character) {
		if (character.getName() != null) {
			this.name = character.getName();
		}
		if (character.getDescription() != null) {
			this.description = character.getDescription();
		}
		if (character.getData() != null) {
			this.data= character.getData();
		}
	}
	
}
