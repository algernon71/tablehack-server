package org.tablehack.tablehackserver.db.entities;

import org.tablehack.tablehackserver.db.JSONData;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardData {

	private String reference;
	private String name;
	private String type;
	private String orientation;
	
	private Boolean horizontal = true;
	@Lob
	private String description;

	JSONData data;
}
