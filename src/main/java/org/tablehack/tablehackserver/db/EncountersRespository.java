package org.tablehack.tablehackserver.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.tablehack.tablehackserver.db.entities.Encounter;
import org.tablehack.tablehackserver.db.entities.Monster;

public interface EncountersRespository extends PagingAndSortingRepository<Encounter, Long>, CrudRepository<Encounter,Long> {
	List<Encounter> findAll();
	
}
