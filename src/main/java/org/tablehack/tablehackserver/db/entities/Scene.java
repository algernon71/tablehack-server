package org.tablehack.tablehackserver.db.entities;

import org.tablehack.tablehackserver.encounter.EncounterData;
import org.tablehack.tablehackserver.encounter.EncounterRowsConverter;

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
@Table(name = "scenes")
@Data
public class Scene {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String reference;
	private String title;
	private String description;


	
	@Convert(converter = EncounterRowsConverter.class)
	@Lob
	@Column(columnDefinition="TEXT")
	EncounterData data;
	
	
	public void update(Scene encounter) {

		if (encounter.getDescription() != null) {
			this.description = encounter.getDescription();
		}
	}
	
}
