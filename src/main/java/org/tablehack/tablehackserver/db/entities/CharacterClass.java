package org.tablehack.tablehackserver.db.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "character_classes")
@Data
public class CharacterClass {

	@Id
	private String id;
	
	private String description;
}
