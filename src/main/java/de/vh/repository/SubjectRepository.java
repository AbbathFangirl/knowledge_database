package de.vh.repository;

import de.vh.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Pageable;
import java.util.List;


@Repository
public interface SubjectRepository extends CrudRepository<Subject, Integer> {


    @Query(value="select * from subject where description like '%?1%'", nativeQuery = true)
    List<Subject> getByQuery(String name);


}
