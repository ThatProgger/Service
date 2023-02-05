package com.nsh.services.lamps.repository;


import com.nsh.services.lamps.model.JobNomenclature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobNomenclatureRepository extends CrudRepository<JobNomenclature, Integer> {
}
