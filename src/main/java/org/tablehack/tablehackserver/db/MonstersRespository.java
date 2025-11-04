package org.tablehack.tablehackserver.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.tablehack.tablehackserver.db.entities.Monster;

public interface MonstersRespository extends PagingAndSortingRepository<Monster, Long>, CrudRepository<Monster,Long> {
	List<Monster> findAll();
	List<Monster> findByIdIn(List<Long> ids);
	
	Optional<Monster> findByReference(String reference);
}
