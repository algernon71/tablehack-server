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
@Table(name = "monster_targets")
@Data
public class MonsterTarget {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	boolean meleeAttacks = true;
	boolean move = true;
	boolean rangedAttacks = true;
	private String name;
	private String description;

	

}
