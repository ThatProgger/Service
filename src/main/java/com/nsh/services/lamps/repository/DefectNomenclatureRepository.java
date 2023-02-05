package com.nsh.services.lamps.repository;


import com.nsh.services.lamps.model.DefectNomenclature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefectNomenclatureRepository extends CrudRepository<DefectNomenclature, Integer> {
}
