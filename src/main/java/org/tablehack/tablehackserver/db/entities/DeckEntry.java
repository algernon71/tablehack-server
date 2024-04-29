package org.tablehack.tablehackserver.db.entities;

import java.io.Serializable;

import org.tablehack.tablehackserver.db.JSONData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeckEntry implements Serializable {
	private static final long serialVersionUID = 1L;

    private long cardId;
	
	private int count;
	JSONData data;
	
}
