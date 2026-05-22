package org.tablehack.tablehackserver.data;

import java.util.List;

import lombok.Data;

@Data
public class LocationEvent {
	String id;
	String name;
	String description;
	
	List<CardPull> pullCards;
}
