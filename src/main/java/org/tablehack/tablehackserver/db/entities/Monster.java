package org.tablehack.tablehackserver.db.entities;

import org.tablehack.tablehackserver.db.JSONData;
import org.tablehack.tablehackserver.db.JSONDataConverter;
import org.tablehack.tablehackserver.monsters.MonsterActions;
import org.tablehack.tablehackserver.monsters.MonsterActionsConverter;
import org.tablehack.tablehackserver.monsters.MonsterData;
import org.tablehack.tablehackserver.monsters.MonsterDataConverter;

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
@Table(name = "monsters")
@Data
public class Monster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String reference;
	private Integer level;
	private String name;
	private String type;
	private Integer health;

	private String description;
	private String image;

	
	@Convert(converter = MonsterDataConverter.class)
	@Lob
	@Column(columnDefinition="TEXT")
	MonsterData data = new MonsterData();
	
	public void update(Monster monster) {
		if (monster.getReference() != null) {
			this.reference = monster.getReference();
		}
		if (monster.getName() != null) {
			this.name = monster.getName();
		}
		if (monster.getLevel() != null) {
			this.level = monster.getLevel();
		}
		if (monster.getHealth() != null) {
			this.health = monster.getHealth();
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
