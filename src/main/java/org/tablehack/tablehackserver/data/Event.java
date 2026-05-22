package org.tablehack.tablehackserver.data;

import lombok.Data;

@Data
public class Event {
	String name;
	String description;
	Integer count;
	
	CardPull cardPull;
	CardAttributes cardAttributes = new CardAttributes();
}
