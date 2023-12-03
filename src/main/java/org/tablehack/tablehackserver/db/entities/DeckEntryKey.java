package org.tablehack.tablehackserver.db.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Embeddable
public class DeckEntryKey {
	@ManyToOne
	Deck deck;
	
	@ManyToOne
	Card card;
}
