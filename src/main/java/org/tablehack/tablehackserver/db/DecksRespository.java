package org.tablehack.tablehackserver.db;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tablehack.tablehackserver.db.entities.Card;
import org.tablehack.tablehackserver.db.entities.Deck;

public interface DecksRespository extends PagingAndSortingRepository<Deck, Long>, CrudRepository<Deck,Long> {
	List<Deck> findByType(String type);
	List<Deck> findAll();
	
	@Query("""
			SELECT d FROM Deck d WHERE (:type IS NULL OR d.type = :type) AND (:requireReference IS NULL OR (d.reference IS NOT NULL AND d.reference <> ''))
			""")
	List<Deck> search(@Param("type") String type, @Param("requireReference") String requireReference);
}
