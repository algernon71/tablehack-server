package org.tablehack.tablehackserver.db;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.tablehack.tablehackserver.db.entities.ResourceEntry;

public interface ResourceRespository
		extends PagingAndSortingRepository<ResourceEntry, String>, CrudRepository<ResourceEntry, String> {
	Page<ResourceEntry> findAll(Pageable pageable);

	Page<ResourceEntry> findByType(String type, Pageable pageable);
	Page<ResourceEntry> findByTypeAndPathContaining(String type, String path, Pageable pageable);
}
