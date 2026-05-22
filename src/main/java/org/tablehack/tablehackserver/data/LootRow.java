package org.tablehack.tablehackserver.data;

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
public class LootRow  {
	private String type;
	private String itemReference;
	private String image;
	private int count;
	
	
	
}
