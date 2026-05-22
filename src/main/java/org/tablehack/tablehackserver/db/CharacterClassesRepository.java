package org.tablehack.tablehackserver.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tablehack.tablehackserver.db.entities.Card;
import org.tablehack.tablehackserver.db.entities.CharacterClass;

public interface CharacterClassesRepository extends CrudRepository<CharacterClass,Long> {
	List<CharacterClass> findAll();
}
