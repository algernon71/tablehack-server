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
import org.tablehack.tablehackserver.db.CharacterClassesRepository;
import org.tablehack.tablehackserver.db.entities.Card;
import org.tablehack.tablehackserver.db.entities.CharacterClass;

@CrossOrigin(origins = "http://localhost:4300")
@RestController
@RequestMapping("/api/classes")
public class CharacterClassesController {
	@Autowired
	CharacterClassesRepository classes;
	
	@GetMapping
	public 
	List<CharacterClass> getClasses() {
		return classes.findAll();
	}
	

}
