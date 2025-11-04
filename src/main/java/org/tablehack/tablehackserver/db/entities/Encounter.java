package org.tablehack.tablehackserver.db.entities;

import org.tablehack.tablehackserver.db.JSONData;
import org.tablehack.tablehackserver.db.JSONDataConverter;
import org.tablehack.tablehackserver.encounter.EncounterData;
import org.tablehack.tablehackserver.encounter.EncounterRows;
import org.tablehack.tablehackserver.encounter.EncounterRowsConverter;
import org.tablehack.tablehackserver.monsters.CardAttributes;
import org.tablehack.tablehackserver.monsters.CardAttributesConverter;
import org.tablehack.tablehackserver.monsters.MonsterActions;
import org.tablehack.tablehackserver.monsters.MonsterActionsConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name = "encounters")
@Data
public class Encounter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String tokenId;
	private String title;
	private String description;


	@Convert(converter = EncounterRowsConverter.class)
	@Lob
	@Column(columnDefinition="TEXT")
	EncounterData data;
	
	
	public void update(Encounter encounter) {

		if (encounter.getDescription() != null) {
			this.description = encounter.getDescription();
		}
	}
}
