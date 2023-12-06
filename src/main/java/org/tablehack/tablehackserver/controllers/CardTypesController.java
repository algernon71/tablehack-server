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
import org.tablehack.tablehackserver.db.entities.CardTypeEntry;
import org.tablehack.tablehackserver.db.entities.CardTypeEntryList;
import org.tablehack.tablehackserver.db.entities.CardTypeField;
import org.tablehack.tablehackserver.db.entities.CardTypeFieldList;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "http://localhost:4300")
@RestController
@RequestMapping("/api/cardtypes")
public class CardTypesController {
	@Autowired
	CardsTypesRespository types;

	
	@PostConstruct 
	public void init() {
	    log.info("init");
	    emptyDb();
	    bootStrap();
	}
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
	
	void emptyDb() {
	    types.deleteAll();
	}
	
	void bootStrap() {
	    addType("action", "Handling", fields(field("icon", "icon", "Symbol")), entries(entry("symbol")));
        addType("event", "Händelse", fields(field("icon", "icon", "Symbol")), entries(entry("symbol")));
        addType("monster", "Monster", fields(field("icon", "icon", "Symbol")), entries(entry("symbol")));
        addType("search", "Sökresultat", fields(field("icon", "icon", "Symbol")), entries(entry("symbol")));
        addType("treasure", "Skatt", fields(field("icon", "icon", "Symbol")), entries(entry("symbol")));
	}
	
	CardTypeEntry entry(String type) {
	    return CardTypeEntry.builder()
	            .type(type)
	            .build();
	}
	
    CardTypeField field(String id, String type, String name) {
        return CardTypeField.builder()
                .id(id)
                .type(type)
                .name(name)
                .build();
    }
    
	CardTypeEntryList entries(CardTypeEntry ... entries) {
	    return new CardTypeEntryList(entries);
	}
    CardTypeFieldList fields(CardTypeField entries) {
        return new CardTypeFieldList(entries);
    }
	void addType(String id, String name, CardTypeFieldList fields , CardTypeEntryList entries ) {
	    CardType type = CardType.builder()
	            .id(id)
	            .name(name)
	            .fields(fields)
	            .entries(entries)
	            
	            .build();
	    
	    types.save(type);
	}
}
