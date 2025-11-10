package org.tablehack.tablehackserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
import org.tablehack.tablehackserver.db.ItemsRespository;
import org.tablehack.tablehackserver.db.entities.EquipmentItem;

@CrossOrigin(origins = "http://localhost:4300")
@RestController
@RequestMapping("/api/items")
public class ItemsController {
	@Autowired
	ItemsRespository items;
	
	@GetMapping
	public 
	PagedModel<EquipmentItem> getitems(@RequestParam(name = "ids", required = false) String ids) {
		PageRequest pageRequest = PageRequest.of(0, 100);
		if (ids != null) {
			return new PagedModel<EquipmentItem>(items.findByIdIn(List.of(ids.split(",")).stream().map(s -> Long.parseLong(s)).toList(), pageRequest));
		}
		return new PagedModel<EquipmentItem>(items.findAll(pageRequest));
	}
	

	@GetMapping("/{EquipmentItemId}")
	public 
	EquipmentItem getEquipmentItem(@PathVariable Long EquipmentItemId) {
		return items.findById(EquipmentItemId).get();
	}
	
	@GetMapping("/by-reference/{reference}")
	public 
	EquipmentItem getEquipmentItemByReference(@PathVariable String reference) {
		return items.findByReference(reference).get();
	}
	
	@PostMapping
	public 
	EquipmentItem addEquipmentItem(@RequestBody EquipmentItem EquipmentItem) {
		EquipmentItem.setId(null);
		 return items.save(EquipmentItem);
	}
	
	@PutMapping("/{EquipmentItemId}")
	public 
	EquipmentItem updateCard(@PathVariable Long EquipmentItemId, @RequestBody EquipmentItem EquipmentItem) {
		EquipmentItem currentEquipmentItem = items.findById(EquipmentItemId).get();
		currentEquipmentItem.update(currentEquipmentItem);
		return items.save(EquipmentItem);
	}
	
	@DeleteMapping("/{EquipmentItemId}")
	public 
	void deleteCard(@PathVariable Long EquipmentItemId) {
		EquipmentItem currentEquipmentItem = items.findById(EquipmentItemId).get();
		items.delete(currentEquipmentItem);
	}
}
