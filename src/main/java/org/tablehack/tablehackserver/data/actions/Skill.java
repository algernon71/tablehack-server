package org.tablehack.tablehackserver.data.actions;

import java.io.Serializable;
import java.util.List;

import org.tablehack.tablehackserver.data.CardAttributes;
import org.tablehack.tablehackserver.data.Damage;
import org.tablehack.tablehackserver.data.Defence;
import org.tablehack.tablehackserver.data.ProgressionStat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Skill implements Serializable {
	private String id;
	private String name;
	private ProgressionStat progression;
	private String description;
	
	private Damage damage;
	private Defence defence;
}
