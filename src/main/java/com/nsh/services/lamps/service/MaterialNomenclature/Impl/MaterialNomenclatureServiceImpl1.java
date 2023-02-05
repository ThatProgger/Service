package com.nsh.services.lamps.service.MaterialNomenclature.Impl;

import com.nsh.services.lamps.model.MaterialNomenclature;
import com.nsh.services.lamps.repository.MaterialNomenclatureRepository;
import com.nsh.services.lamps.service.MaterialNomenclature.MaterialNomenclatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementing the DefectNomenclatureService interface.
 * The class allows you to store and retrieve data from a table
 * @author Mikhail Dedyukhin
 * @since 1.1
 * @see MaterialNomenclature
 */
@Service
public class MaterialNomenclatureServiceImpl1 implements MaterialNomenclatureService {
    @Autowired
    private MaterialNomenclatureRepository materialNomenclatureRepository;


    @Override
    public List<MaterialNomenclature> findAll (){
        Iterable <MaterialNomenclature> iterable = materialNomenclatureRepository.findAll();
        List<MaterialNomenclature> materialNomenclatureList = new ArrayList<>();

        iterable.forEach(materialNomenclature -> {
            materialNomenclatureList.add(materialNomenclature);
        });
        return materialNomenclatureList;
    }

    @Override
    public long getMaterialNomenclatureCount (){
        return materialNomenclatureRepository.count();
    }

    @Override
    public boolean save (MaterialNomenclature materialNomenclature){
        return materialNomenclature.equals(materialNomenclatureRepository.save(materialNomenclature));
    }
}
