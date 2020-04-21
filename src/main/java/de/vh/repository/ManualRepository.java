package de.vh.repository;

import de.vh.entity.Manuals;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManualRepository extends CrudRepository<Manuals, Integer> {
}
