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
	CardTypeEntryList defaultBackEntries = entries(entry("name"));
	CardTypeEntryList defaultFrontEntries = entries(entry("thlogo"), entry("deck-type"), entry("deck-symbol") );
	
	void bootStrap() {
	    add(cardType("action", "Handling", "Handlingar", 
	    		fields(field("icon", "icon", "Symbol"), 
	    				field("actions", "Handling").maxCount(3)), 
	    		entries(entry("thlogo"), entry("name"), 
	    				entry("description"), 
	    				entry("actions"))));
        add(cardType("event", "Händelse", "Händelser", 
        		fields(field("icon", "Symbol")), 
        		entries(entry("name"), entry("symbol"),entry("description"))));
        add(cardType("monster", "Monster", "Monster",
        		fields(field("image"), field("mstats")), 
        		entries(entry("card-reference"),entry("name"), entry("mstats"), entry("image"), entry("resistences")))
        		.frontEntries(entries(entry("thlogo"), entry("card-reference"), entry("name"),entry("image"))));
        add(cardType("monster-actions", "Handling", "Monsterhandlingar",
        		fields(field("actions")), 
        		entries(entry("thlogo"), entry("name"), entry("symbol"), entry("actions")))
        		.frontEntries(entries(entry("thlogo"), entry("deck-type"),entry("deck-name"), entry("deck-symbol") )));
        
        add(cardType("search", "Sökresultat", "Sökresultat",
        		fields(field("symbol", "symbol", "Symbol")), entries(entry("name"), entry("symbol"), entry("description"))));
        add(cardType("treasure", "Skatt", "Skatter", fields(field("icon", "icon", "Symbol")), 
        		entries(entry("symbol"))));
        add(cardType("potions", "Dryck", "Drycker",
        		fields(field("image", "image", "Symbol")), entries(entry("name"), entry("description"), entry("spacer"))));
        add(cardType("item", "Föremål", "Föremål",
        		fields(field("image", "image", "Symbol")), entries(entry("symbol"))));
        add(cardType("weapon", "Vapen", "Vapen",
        		fields(field("image", "image", "Symbol")), entries(entry("symbol"))));
	    add(cardType("spell", "Trollformel", "Trollformler", fields(field("symbol", "symbol", "Symbol"), field("cost", "text", "Cost"), field("attack", "attack", "Skada")), 
	    		entries(entry("thlogo"), entry("name"), entry("symbol"), entry("description"))));
	}
	
	CardTypeEntry.CardTypeEntryBuilder entry(String type) {
	    return CardTypeEntry.builder()
	            .type(type)
	            .fieldId(type);
	}
	CardTypeEntry entry(String type, String fieldId) {
	    return CardTypeEntry.builder()
	            .type(type)
	            .fieldId(fieldId)
	            .build();
		
	}
	
    CardTypeField.CardTypeFieldBuilder field(String id, String type, String name, int maxCount) {
        return CardTypeField.builder()
                .id(id)
                .type(type)
                .name(name)
                .maxCount(maxCount);
    }
    CardTypeField.CardTypeFieldBuilder field(String id, String type, String name) {
    	return field(id, type, name, 1);
    }
    CardTypeField.CardTypeFieldBuilder field(String type) {
    	return field(type, type, type, 1);
    }
    CardTypeField.CardTypeFieldBuilder field(String type, String name) {
    	return field(type, type, name, 1);
    }
    
	CardTypeEntryList entries(CardTypeEntry.CardTypeEntryBuilder ... entries) {
	    return new CardTypeEntryList(entries);
	}
	
	
    CardTypeFieldList fields(CardTypeField.CardTypeFieldBuilder ... entries) {
        return new CardTypeFieldList(entries);
    }
	void addType(String id, String name, String namePlural, CardTypeFieldList fields , CardTypeEntryList entries ) {
	    CardType type = CardType.builder()
	            .id(id)
	            .name(name)
	            .namePlural(namePlural)
	            .fields(fields)
	            .entries(entries)
	            
	            .build();
	    
	    types.save(type);
	}
	void add(CardType.CardTypeBuilder type) {
		types.save(type.build());
	}
	
	CardType.CardTypeBuilder cardType(String id, String name, String namePlural, CardTypeFieldList fields , CardTypeEntryList entries ) {
	    return CardType.builder()
	            .id(id)
	            .name(name)
	            .namePlural(namePlural)
	            .fields(fields)
	            .entries(entries) 
	            .frontEntries(defaultFrontEntries);
	}
	CardType.CardTypeBuilder cardType(String id, String name, String namePlural) {
	    return CardType.builder()
	            .id(id)
	            .name(name)
	            .namePlural(namePlural)
	            .frontEntries(defaultFrontEntries);
	}
}
