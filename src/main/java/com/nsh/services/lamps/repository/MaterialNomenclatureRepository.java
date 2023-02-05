package com.nsh.services.lamps.repository;


import com.nsh.services.lamps.model.MaterialNomenclature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialNomenclatureRepository extends CrudRepository<MaterialNomenclature, Integer> {

}
