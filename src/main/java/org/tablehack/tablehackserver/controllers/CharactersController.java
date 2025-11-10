package org.tablehack.tablehackserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
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
import org.tablehack.tablehackserver.db.CharactersRespository;
import org.tablehack.tablehackserver.db.entities.PlayerCharacter;

@CrossOrigin(origins = "http://localhost:4300")
@RestController
@RequestMapping("/api/characters")
public class CharactersController {
	@Autowired
	CharactersRespository characters;
	
	@GetMapping
	public 
	PagedModel<PlayerCharacter> getMonsters(@RequestParam(name = "ids", required = false) String ids) {
		Pageable pageRequest = PageRequest.of(0, 1000);
		if (ids != null) {
			return new PagedModel<PlayerCharacter>(characters.findByIdIn(List.of(ids.split(",")).stream().map(s -> Long.parseLong(s)).toList(), pageRequest));
		}
		return new PagedModel<PlayerCharacter>(characters.findAll(pageRequest));
	}
	

	@GetMapping("/{monsterId}")
	public 
	PlayerCharacter getMonster(@PathVariable Long monsterId) {
		return characters.findById(monsterId).get();
	}
	
	@PostMapping
	public 
	PlayerCharacter addMonster(@RequestBody PlayerCharacter PlayerCharacter) {
		PlayerCharacter.setId(null);
		 return characters.save(PlayerCharacter);
	}
	
	@PutMapping("/{monsterId}")
	public 
	PlayerCharacter updateCard(@PathVariable Long monsterId, @RequestBody PlayerCharacter PlayerCharacter) {
		PlayerCharacter currentMonster = characters.findById(monsterId).get();
		currentMonster.update(PlayerCharacter);
		return characters.save(PlayerCharacter);
	}
	
	@DeleteMapping("/{monsterId}")
	public 
	void deleteCard(@PathVariable Long monsterId) {
		PlayerCharacter currentMonster = characters.findById(monsterId).get();
		characters.delete(currentMonster);
	}
}
