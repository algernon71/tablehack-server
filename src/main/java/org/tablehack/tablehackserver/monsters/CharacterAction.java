package org.tablehack.tablehackserver.monsters;

import java.io.Serializable;
import java.util.List;

import org.tablehack.tablehackserver.db.JSONData;
import org.tablehack.tablehackserver.db.JSONDataConverter;
import org.tablehack.tablehackserver.db.entities.Monster;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
public class CharacterAction implements Serializable {
	private String initiative;
	private Integer count;
	private String name;
	private String description;
	
	
	private ActionType type;
	private String subtype;
	private String range;
	private Damage damage;
	private Defence defence;
	private CardAttributes attributes;
}
