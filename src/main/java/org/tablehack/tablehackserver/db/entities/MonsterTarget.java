package org.tablehack.tablehackserver.db.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
