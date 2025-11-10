package org.tablehack.tablehackserver.db.entities;

import org.tablehack.tablehackserver.data.ItemData;
import org.tablehack.tablehackserver.data.ItemDataConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "equipment_items")
@Data
public class EquipmentItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String reference;
	private String type;
	private String subType;

	private String description;
	private String image;

	
	@Convert(converter = ItemDataConverter.class)
	@Lob
	@Column(columnDefinition="TEXT")
	ItemData data = new ItemData();
	

	public void update(EquipmentItem monster) {
		if (monster.getReference() != null) {
			this.reference = monster.getReference();
		}
		if (monster.getName() != null) {
			this.name = monster.getName();
		}
		if (monster.getType() != null) {
			this.type = monster.getType();
		}
		if (monster.getDescription() != null) {
			this.description = monster.getDescription();
		}
		if (monster.getData() != null) {
			this.data= monster.getData();
		}
	}
}
