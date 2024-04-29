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
	CardTypeFieldList defaultColumns = new CardTypeFieldList();
	CardTypeEntryList defaultBackEntries = entries("name");
	CardTypeEntryList defaultFrontEntries = entries("thlogo","deck-type","deck-symbol" );
	
	void bootStrap() {
	    add(cardType("action", "Handling", "Handlingar", 
	    		fields("name", "card-level", "orientation", "description", "symbol", "actions"), 
	    		entries("thlogo", "name", "description","actions"))
	    		.frontEntries(entries("thlogo", "deck-type", "deck-symbol", "deck-name", "card-level")))
	    ;
        add(cardType("event", "Händelse", "Händelser", 
	    		fields("name","description", "symbol", "actions"), 
        		entries("name", "symbol", "description")));
        add(cardType("monster", "Monster", "Monster",
	    		fields("reference","name","image", "description", "mstats"), 
        		entries("card-reference","name","mstats","image","resistences"))
        		.frontEntries(entries("thlogo","card-reference","name", "image")));
        add(cardType("monster-actions", "Handling", "Monsterhandlingar",
	    		fields("name","icon", "description", "actions"), 
        		entries("thlogo","name","symbol","description", "actions"))
        		.frontEntries(entries("thlogo","deck-type","deck-name","deck-symbol") ));
        add(cardType("monster-passive", "Passiv", "Passiva monster",
	    		fields("name","icon", "alertness", "description"), 
        		entries("thlogo","name","symbol","alertness", "description"))
        		.frontEntries(entries("thlogo","deck-type","deck-name","deck-symbol") ));
        
        add(cardType("search", "Sökresultat", "Sökresultat",
	    		fields("name","symbol", "description", "actions"), 
        		entries("name","symbol","description")));
        add(cardType("treasure", "Skatt", "Skatter", 
	    		fields("name","symbol", "description"), 
        		entries("symbol")));
        add(cardType("potions", "Dryck", "Drycker",
	    		fields("name","symbol", "actions"), 
        		entries("name","description","spacer")));
        add(cardType("item", "Föremål", "Föremål",
	    		fields("name","symbol", "description"), 
        		entries("symbol")));
        add(cardType("weapon", "Vapen", "Vapen",
	    		fields("name","symbol", "description"), 
        		entries("symbol")));
	    add(cardType("spell", "Trollformel", "Trollformler", 
	    		fields("name","symbol", "actions"), 
	    		entries("thlogo","name","symbol","description")));
	    add(cardType("character", "Karaktär", "Karaktärer") 
	    		.fields(fields("name","image","description", "bio", "stats")) 
	    		.entries(entries(entry("thlogo"), entry("name-large"), entry("image"),entry("bio"), entry("stats"), entry("levels")))
	    		.frontEntries(entries("thlogo","name-large","big-image", "description") )
	    		.size("large"));
	}// entries(entry("thlogo"), entry("name"), entry("image"),entry("bio")), 
	
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
	CardTypeEntry.CardTypeEntryBuilder columnsEntry(CardTypeEntryList ...columns ) {
	    return CardTypeEntry.builder()
	            .type("columns")
	            .fieldId("columns")
	            .columns(List.of(columns));
		
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
	CardTypeEntryList entries(String ... entries) {
	    return new CardTypeEntryList(entries);
	}
	
	
    CardTypeFieldList fields(CardTypeField.CardTypeFieldBuilder ... entries) {
        return new CardTypeFieldList(entries);
    }
    CardTypeFieldList fields(String ... fields) {
        return new CardTypeFieldList(fields);
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
	            .size("small")
	            .namePlural(namePlural)
	            .fields(fields)
	            .entries(entries) 
	            .frontEntries(defaultFrontEntries)
	            .columns(defaultColumns);
	}
	CardType.CardTypeBuilder cardType(String id, String name, String namePlural) {
	    return CardType.builder()
	            .id(id)
	            .name(name)
	            .namePlural(namePlural)
	            .frontEntries(defaultFrontEntries);
	}
}
