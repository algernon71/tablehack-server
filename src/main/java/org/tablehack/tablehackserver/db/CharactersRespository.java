package org.tablehack.tablehackserver.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.tablehack.tablehackserver.db.entities.Monster;
import org.tablehack.tablehackserver.db.entities.PlayerCharacter;

public interface CharactersRespository extends PagingAndSortingRepository<PlayerCharacter, Long>, CrudRepository<PlayerCharacter,Long> {
	List<PlayerCharacter> findAll();
	List<PlayerCharacter> findByIdIn(List<Long> ids);
	
}
