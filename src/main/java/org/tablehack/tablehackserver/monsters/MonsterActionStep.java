package org.tablehack.tablehackserver.monsters;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
public class MonsterActionStep implements Serializable {
	
	private String name;
	private ActionType type;
	private String subtype;
	private String range;
	private Damage damage;
	private Defence defence;
	private String attributes;
	private String body;
	private Integer targettingId;
	private String description;
	
	
	
}
