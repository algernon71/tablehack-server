package org.tablehack.tablehackserver.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.tablehack.tablehackserver.db.entities.EquipmentItem;
import org.tablehack.tablehackserver.db.entities.Monster;

public interface ItemsRespository extends PagingAndSortingRepository<EquipmentItem, Long>, CrudRepository<EquipmentItem,Long> {
	Page<EquipmentItem> findAll(Pageable pageable);
	Page<EquipmentItem> findByIdIn(List<Long> ids, Pageable pageable);
	
	Optional<EquipmentItem> findByReference(String reference);
}
