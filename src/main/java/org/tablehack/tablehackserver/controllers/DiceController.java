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
import org.tablehack.tablehackserver.data.Damage;
import org.tablehack.tablehackserver.db.SceneRespository;
import org.tablehack.tablehackserver.db.entities.Scene;
import org.tablehack.tablehackserver.dice.Dice;
import org.tablehack.tablehackserver.dice.RollResult;

@CrossOrigin(origins = "http://localhost:4300")
@RestController
@RequestMapping("/api/dice")
public class DiceController {
	@Autowired
	SceneRespository scenes;
	
	
	@PostMapping("damage")
	public 
	RollResult rollDamage(@RequestBody Damage damage) {
		return null;
	}
	
}
