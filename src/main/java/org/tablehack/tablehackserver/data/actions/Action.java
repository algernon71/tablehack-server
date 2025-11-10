package org.tablehack.tablehackserver.data.actions;

import java.io.Serializable;
import java.util.List;

import org.tablehack.tablehackserver.data.CardAttributes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Action implements Serializable {
	private String initiative;
	private Integer level;
	private Integer count;
	private String title;
	private String description;
	private String targettingId;
	
	
	private CardAttributes attributes;
	private List<ActionStep> steps;
}
