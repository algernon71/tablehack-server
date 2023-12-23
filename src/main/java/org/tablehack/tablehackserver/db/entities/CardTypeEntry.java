package org.tablehack.tablehackserver.db.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardTypeEntry implements Serializable {
	private static final long serialVersionUID = 1L;

    private String type;
    private String fieldId;
	
	private Map<String, Object> properties;
	private List<List<CardTypeEntry>> columns;
	
}
