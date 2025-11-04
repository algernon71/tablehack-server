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
import org.springframework.web.bind.annotation.RestController;
import org.tablehack.tablehackserver.db.EncountersRespository;
import org.tablehack.tablehackserver.db.entities.Encounter;

@CrossOrigin(origins = "http://localhost:4300")
@RestController
@RequestMapping("/api/encounters")
public class EncountersController {
	@Autowired
	EncountersRespository encounters;
	
	@GetMapping
	public 
	List<Encounter> getEncounters() {
		return encounters.findAll();
	}
	

	@GetMapping("/{encounterId}")
	public 
	Encounter getCard(@PathVariable Long encounterId) {
		return encounters.findById(encounterId).get();
	}
	
	@PostMapping
	public 
	Encounter addEncounter(@RequestBody Encounter encounter) {
		encounter.setId(null);
		 return encounters.save(encounter);
	}
	
	@PutMapping("/{encounterId}")
	public 
	Encounter updateCard(@PathVariable Long encounterId, @RequestBody Encounter encounter) {
		Encounter currentEncounter = encounters.findById(encounterId).get();
		currentEncounter.update(encounter);
		return encounters.save(encounter);
	}
	
	@DeleteMapping("/{encounterId}")
	public 
	void deleteCard(@PathVariable Long encounterId) {
		Encounter currentEncounter = encounters.findById(encounterId).get();
		encounters.delete(currentEncounter);
	}
}
