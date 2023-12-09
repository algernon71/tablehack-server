package org.tablehack.tablehackserver.db.entities;

import org.tablehack.tablehackserver.db.CardTypeEntryListConverter;
import org.tablehack.tablehackserver.db.CardTypeFieldListConverter;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "card_types")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardType {
	@Id
	private String id;
	private String size;

	private String name;
	private String namePlural;
	private String description;
	private String template;

    @Convert(converter = CardTypeEntryListConverter.class)
    @Lob
    CardTypeEntryList entries;
    
    @Lob
    @Convert(converter = CardTypeFieldListConverter.class)
    CardTypeFieldList fields;
}
