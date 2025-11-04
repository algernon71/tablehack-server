package org.tablehack.tablehackserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tablehack.tablehackserver.db.CardsRespository;
import org.tablehack.tablehackserver.db.MonstersRespository;
import org.tablehack.tablehackserver.db.entities.Card;
import org.tablehack.tablehackserver.db.entities.Monster;

@CrossOrigin(origins = "http://localhost:4300")
@RestController
@RequestMapping("/api/monsters")
public class MonstersController {
	@Autowired
	MonstersRespository monsters;
	
	@GetMapping
	public 
	List<Monster> getMonsters(@RequestParam(name = "ids", required = false) String ids) {
		if (ids != null) {
			return monsters.findByIdIn(List.of(ids.split(",")).stream().map(s -> Long.parseLong(s)).toList());
		}
		return monsters.findAll();
	}
	

	@GetMapping("/{monsterId}")
	public 
	Monster getMonster(@PathVariable Long monsterId) {
		return monsters.findById(monsterId).get();
	}
	
	@GetMapping("/by-reference/{reference}")
	public 
	Monster getMonsterByReference(@PathVariable String reference) {
		return monsters.findByReference(reference).get();
	}
	
	@PostMapping
	public 
	Monster addMonster(@RequestBody Monster monster) {
		monster.setId(null);
		 return monsters.save(monster);
	}
	
	@PutMapping("/{monsterId}")
	public 
	Monster updateCard(@PathVariable Long monsterId, @RequestBody Monster monster) {
		Monster currentMonster = monsters.findById(monsterId).get();
		currentMonster.update(monster);
		return monsters.save(monster);
	}
	
	@DeleteMapping("/{monsterId}")
	public 
	void deleteCard(@PathVariable Long monsterId) {
		Monster currentMonster = monsters.findById(monsterId).get();
		monsters.delete(currentMonster);
	}
}
