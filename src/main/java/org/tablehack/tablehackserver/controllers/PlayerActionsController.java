package org.tablehack.tablehackserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.tablehack.tablehackserver.db.PlayerActionsRespository;
import org.tablehack.tablehackserver.db.entities.PlayerAction;

@CrossOrigin(origins = "http://localhost:4300")
@RestController
@RequestMapping("/api/player-actions")
public class PlayerActionsController {
	@Autowired
	PlayerActionsRespository items;
	
	@GetMapping
	public 
	PagedModel<PlayerAction> getitems(@RequestParam(name = "ids", required = false) String ids) {
		Pageable pageRequest = PageRequest.of(0, 1000);
		if (ids != null) {
			return new PagedModel<PlayerAction> (items.findByIdIn(List.of(ids.split(",")).stream().map(s -> Long.parseLong(s)).toList(), pageRequest));
		}
		return new PagedModel<PlayerAction> (items.findAll(pageRequest));
	}
	
	@GetMapping("/for-class/{characterClass}")
	public 
	List<PlayerAction> getClassActions(@PathVariable String characterClass) {
		return items.findByCharacterClass(characterClass);
	}
	@GetMapping("/all")
	public 
	List<PlayerAction> getAll() {
		return items.findAll();
	}
	

	@GetMapping("/{id}")
	public 
	PlayerAction getPlayerAction(@PathVariable Long id) {
		return items.findById(id).get();
	}
	
	
	@PostMapping
	public 
	PlayerAction addPlayerAction(@RequestBody PlayerAction PlayerAction) {
		PlayerAction.setId(null);
		 return items.save(PlayerAction);
	}
	
	@PutMapping("/{id}")
	public 
	PlayerAction updateCard(@PathVariable Long id, @RequestBody PlayerAction PlayerAction) {
		PlayerAction currentPlayerAction = items.findById(id).get();
		currentPlayerAction.update(currentPlayerAction);
		return items.save(PlayerAction);
	}
	
	@DeleteMapping("/{PlayerActionId}")
	public 
	void deleteCard(@PathVariable Long PlayerActionId) {
		PlayerAction currentPlayerAction = items.findById(PlayerActionId).get();
		items.delete(currentPlayerAction);
	}
}
