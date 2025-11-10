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
import org.tablehack.tablehackserver.db.SceneRespository;
import org.tablehack.tablehackserver.db.entities.Scene;

@CrossOrigin(origins = "http://localhost:4300")
@RestController
@RequestMapping("/api/scenes")
public class ScenesController {
	@Autowired
	SceneRespository scenes;
	
	@GetMapping
	public 
	PagedModel<Scene> getitems(@RequestParam(name = "ids", required = false) String ids) {
		Pageable pageRequest = PageRequest.of(0, 1000);
		if (ids != null) {
			return new PagedModel<Scene> (scenes.findByIdIn(List.of(ids.split(",")).stream().map(s -> Long.parseLong(s)).toList(), pageRequest));
		}
		return new PagedModel<Scene> (scenes.findAll(pageRequest));
	}
	
	

	@GetMapping("/{id}")
	public 
	Scene getScene(@PathVariable Long id) {
		return scenes.findById(id).get();
	}
	
	
	@PostMapping
	public 
	Scene addScene(@RequestBody Scene Scene) {
		Scene.setId(null);
		 return scenes.save(Scene);
	}
	
	@PutMapping("/{id}")
	public 
	Scene updateCard(@PathVariable Long id, @RequestBody Scene Scene) {
		Scene currentScene = scenes.findById(id).get();
		currentScene.update(currentScene);
		return scenes.save(Scene);
	}
	
	@DeleteMapping("/{SceneId}")
	public 
	void deleteCard(@PathVariable Long SceneId) {
		Scene currentScene = scenes.findById(SceneId).get();
		scenes.delete(currentScene);
	}
}
