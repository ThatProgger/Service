package com.nsh.services.lamps.service.DefectNomenclature.Impl;


import com.nsh.services.lamps.model.DefectNomenclature;
import com.nsh.services.lamps.repository.DefectNomenclatureRepository;
import com.nsh.services.lamps.service.DefectNomenclature.DefectNomenclatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementing the DefectNomenclatureService interface.
 * The class allows you to store and retrieve data from a table
 * @author Mikhail Dedyukhin
 * @since 1.1
 * @see Lamp.service.DefectNomenclature.DefectNomenclatureService
 */
@Service
public class DefectNomenclatureServiceImpl1 implements DefectNomenclatureService {

    @Autowired
    private DefectNomenclatureRepository defectNomenclatureRepository;

    public boolean save (DefectNomenclature defectNomenclature){
        return defectNomenclature.equals(defectNomenclatureRepository.save(defectNomenclature));
    }

    public Iterable<DefectNomenclature> findAll (){
        return defectNomenclatureRepository.findAll();
    }

    public long getDefectNomenclatureCount (){
        return defectNomenclatureRepository.count();
    }
}
