package org.tablehack.tablehackserver.db.entities;

import org.tablehack.tablehackserver.db.DeckEntryListConverter;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="decks")
@Data
@NoArgsConstructor
public class Deck {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	private String reference;
	private String name;
	private String type;
	private String symbol;
	
	@Convert(converter = DeckEntryListConverter.class)
	@Lob
	DeckEntryList entries = new DeckEntryList();
	
	public
	int getCardCount() {
		return entries.stream()
				.map(DeckEntry::getCount)
				.reduce(0, Integer::sum);
	}
	
}
