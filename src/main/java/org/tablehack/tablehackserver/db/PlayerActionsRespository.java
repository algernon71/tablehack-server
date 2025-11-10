package org.tablehack.tablehackserver.db;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.tablehack.tablehackserver.db.entities.PlayerAction;

public interface PlayerActionsRespository extends PagingAndSortingRepository<PlayerAction, Long>, CrudRepository<PlayerAction,Long> {
	List<PlayerAction> findAll();
	Page<PlayerAction> findAll(Pageable pageable);
	Page<PlayerAction> findByIdIn(List<Long> ids, Pageable pageable);
	
	@Query(value = "SELECT action FROM PlayerAction action WHERE action.characterClass = :characterClass OR action.characterClass is null")
	List<PlayerAction> findByCharacterClass(@Param("characterClass") String characterClass);
	
}
