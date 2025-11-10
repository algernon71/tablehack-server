package org.tablehack.tablehackserver.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.tablehack.tablehackserver.db.entities.Monster;
import org.tablehack.tablehackserver.db.entities.PlayerCharacter;
import org.tablehack.tablehackserver.db.entities.Scene;

public interface SceneRespository extends PagingAndSortingRepository<Scene, Long>, CrudRepository<Scene,Long> {
	Page<Scene> findAll(Pageable pageable);
	Page<Scene> findByIdIn(List<Long> ids, Pageable pageable);
	
}
