package de.vh.repository;

import de.vh.entity.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;


@Repository
public interface SubjectRepository extends CrudRepository<Subject, Integer> {


    String findVideoById(@RequestParam int id);


}
