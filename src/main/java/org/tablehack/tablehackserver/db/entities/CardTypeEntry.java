package org.tablehack.tablehackserver.db.entities;

import java.io.Serializable;
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
	
	private Map<String, Object> properties;
	
}
