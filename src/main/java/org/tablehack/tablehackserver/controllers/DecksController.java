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
import org.tablehack.tablehackserver.db.DecksRespository;
import org.tablehack.tablehackserver.db.entities.Card;
import org.tablehack.tablehackserver.db.entities.Deck;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/decks")
public class DecksController {
	@Autowired
	DecksRespository decks;
	
	@GetMapping
	public 
	List<Deck> getDecks(@RequestParam(name = "type", required = false) String type) {
		if (type == null) {
			return decks.findAll();
		} else {
			return decks.findByType(type);
		}
	}
	
	@GetMapping("/{deckId}")
	public 
	Deck getDeck(@PathVariable long deckId) {
		Deck currentDeck = decks.findById(deckId).get();
		return currentDeck;
	}

	
	@PostMapping
	public 
	Deck addDeck(@RequestBody Deck deck) {
		return decks.save(deck);
	}
	
	@PutMapping("/{deckId}")
	public 
	Deck updateDeck(@PathVariable long deckId, @RequestBody Deck deck) {
		Deck currentDeck = decks.findById(deckId).get();
		deck.setId(currentDeck.getId());
		return decks.save(deck);
	}
	
	@DeleteMapping("/{deckId}")
	public 
	Deck deleteDeck(@PathVariable long deckId) {
		Deck currentDeck = decks.findById(deckId).get();
		decks.delete(currentDeck);
		return currentDeck;
	}
}
