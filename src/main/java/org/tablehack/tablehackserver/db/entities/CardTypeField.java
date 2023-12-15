package org.tablehack.tablehackserver.db.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardTypeField implements Serializable {
	private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String type;
    int minCount;
    int maxCount;
    
}
