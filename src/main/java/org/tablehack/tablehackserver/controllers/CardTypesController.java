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
import org.tablehack.tablehackserver.db.CardsTypesRespository;
import org.tablehack.tablehackserver.db.entities.CardType;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/cardtypes")
public class CardTypesController {
	@Autowired
	CardsTypesRespository types;

	@GetMapping
	public List<CardType> getCardTypes() {
		long start = System.currentTimeMillis();
		List<CardType> result = types.findAll();
		long duration = System.currentTimeMillis() - start;
		
		System.out.println("getCardTypes() " + duration + " ms");
		return result;
	}

	@GetMapping("/{typeId}")
	public CardType getCardType(@PathVariable String typeId) {
		CardType currentType = types.findById(typeId).get();
		return currentType;
	}

	@PostMapping
	public CardType addCardType(@RequestBody CardType type) {
		return types.save(type);
	}

	@PutMapping("/{typeId}")
	public CardType updateCardType(@PathVariable String typeId, @RequestBody CardType type) {
		CardType currentCardType = types.findById(typeId).get();
		type.setId(currentCardType.getId());
		return types.save(type);
	}

	@DeleteMapping("/{typeId}")
	public CardType deleteCardType(@PathVariable String typeId) {
		CardType currentCardType = types.findById(typeId).get();
		types.delete(currentCardType);
		return currentCardType;
	}
}
