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
import org.tablehack.tablehackserver.db.entities.Card;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/cards")
public class CardsController {
	@Autowired
	CardsRespository cards;
	
	@GetMapping
	public 
	List<Card> getCards(@RequestParam(name = "type", required = false) String type) {
		if (type == null) {
			return cards.findAll();
		} else {
			return cards.findByType(type);
		}
	}
	

	@GetMapping("/{cardId}")
	public 
	Card getCard(@PathVariable long cardId) {
		Card currentCard = cards.findById(cardId).get();
		return currentCard;
	}
	
	@PostMapping
	public 
	Card addCard(@RequestBody Card card) {
		 return cards.save(card);
	}
	
	@PostMapping("/import")
	public 
	void addCards(@RequestBody List<Card> cardList) {
		 cards.saveAll(cardList);
	}
	@PutMapping("/{cardId}")
	public 
	Card updateCard(@PathVariable long cardId, @RequestBody Card card) {
		Card currentCard = cards.findById(cardId).get();
		card.setId(currentCard.getId());
		return cards.save(card);
	}
	
	@DeleteMapping("/{cardId}")
	public 
	Card deleteCard(@PathVariable long cardId) {
		Card currentCard = cards.findById(cardId).get();
		cards.delete(currentCard);
		return currentCard;
	}
}
