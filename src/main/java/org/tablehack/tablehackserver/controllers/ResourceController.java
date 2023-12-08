package org.tablehack.tablehackserver.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.multipart.MultipartFile;
import org.tablehack.tablehackserver.controllers.dto.ResourceInfo;
import org.tablehack.tablehackserver.db.ResourceRespository;
import org.tablehack.tablehackserver.db.entities.ResourceEntry;

@CrossOrigin(origins = "http://localhost:4300")
@RestController
@RequestMapping("/api/resources")
public class ResourceController {
	@Autowired
	ResourceRespository repo;

	@GetMapping
	public Page<ResourceInfo> getResources(@RequestParam(name = "type", required = false) String type,
			@RequestParam(name = "search", required = false) String search) {
		PageRequest pageRequest = PageRequest.of(0, 100);
		if (type == null) {
			return repo.findAll(pageRequest).map(res -> new ResourceInfo(res));
		} else {
			if (search != null) {
				
				return repo.findByTypeAndPathContaining(type, search, pageRequest).map(res -> new ResourceInfo(res));
			}
			return repo.findByType(type, pageRequest).map(res -> new ResourceInfo(res));
		}
	}

	@GetMapping("/{path}/info")
	public ResponseEntity<ResourceInfo> getResourceInfo(@PathVariable String path) {
		Optional<ResourceEntry> result = repo.findById(path);
		if (result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(new ResourceInfo(result.get()));
	}

	@GetMapping("/{path}")
	public ResponseEntity<org.springframework.core.io.Resource> getResource(@PathVariable String path) {
		Optional<ResourceEntry> result = repo.findById(path);
		if (result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		ResourceEntry entry = result.get();

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + entry.getPath() + "\"")
				.header(HttpHeaders.CONTENT_TYPE, entry.getMimeType()).body(entry);
	}

	@PostMapping("/upload/{type}")
	public void handleFileUpload(@PathVariable String type, @RequestParam("file") List<MultipartFile> files) {

		files.forEach(file -> {
			try {
				ResourceEntry resource = ResourceEntry.builder().path(file.getOriginalFilename()).type(type)
						.mimeType(file.getContentType()).data(file.getBytes()).build();
				repo.save(resource);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@DeleteMapping("/{path}")
	public void deleteResource(@PathVariable String path) {
		ResourceEntry currentResource = repo.findById(path).get();
		repo.delete(currentResource);
	}
}
