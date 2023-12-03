package org.tablehack.tablehackserver.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tablehack.tablehackserver.db.entities.Card;

public interface CardsRespository extends PagingAndSortingRepository<Card, Long>, CrudRepository<Card,Long> {
	List<Card> findAll();
	List<Card> findByType(String type);
}
