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
import org.tablehack.tablehackserver.db.PlayerActionsRespository;
import org.tablehack.tablehackserver.db.entities.PlayerAction;
import org.tablehack.tablehackserver.db.entities.PlayerCharacter;

@CrossOrigin(origins = "http://localhost:4300")
@RestController
@RequestMapping("/api/characters")
public class CharactersController {
	@Autowired
	CharactersRespository characters;
	
	@Autowired
	PlayerActionsRespository actions;
	
	@GetMapping
	public 
	PagedModel<PlayerCharacter> getCharacters(@RequestParam(name = "ids", required = false) String ids) {
		Pageable pageRequest = PageRequest.of(0, 1000);
		if (ids != null) {
			List<Long> idList = List.of(ids.split(",")).stream().map(s -> Long.parseLong(s)).toList();
			return new PagedModel<PlayerCharacter>(characters.findByIdIn(idList, pageRequest).map(ch -> injectStandardActions(ch)));
		}
		return new PagedModel<PlayerCharacter>(characters.findAll(pageRequest).map(ch -> injectStandardActions(ch)));
	}
	

	@GetMapping("/{characterId}")
	public 
	PlayerCharacter getCharacter(@PathVariable Long characterId) {
		return characters.findById(characterId).map(ch -> injectStandardActions(ch)).get();
	}
	
	@PostMapping
	public 
	PlayerCharacter addCharacter(@RequestBody PlayerCharacter PlayerCharacter) {
		PlayerCharacter.setId(null);
		 return characters.save(PlayerCharacter);
	}
	
	@PutMapping("/{characterId}")
	public 
	PlayerCharacter updateCard(@PathVariable Long characterId, @RequestBody PlayerCharacter PlayerCharacter) {
		PlayerCharacter currentCharacter = characters.findById(characterId).get();
		currentCharacter.update(PlayerCharacter);
		return characters.save(PlayerCharacter);
	}
	
	@DeleteMapping("/{characterId}")
	public 
	void deleteCard(@PathVariable Long characterId) {
		PlayerCharacter currentCharacter = characters.findById(characterId).get();
		characters.delete(currentCharacter);
	}
	
	PlayerCharacter injectStandardActions(PlayerCharacter character) {
		List<PlayerAction> allActions = actions.findAll().stream()
				.filter(action -> action.appliesTo(character))
				.toList();
		
		character.setStandardActions(allActions);
		return character;
	}
}
