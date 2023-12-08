package org.tablehack.tablehackserver.db.entities;

import org.tablehack.tablehackserver.db.JSONData;
import org.tablehack.tablehackserver.db.JSONDataConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cards", indexes = {
		@Index(name="type_index", columnList = "type")
})
@Data
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;
	private String type;
	private String orientation;
	
	@Column
	private Boolean horizontal = true;
	private String description;

	@Convert(converter = JSONDataConverter.class)
	JSONData data;
}
