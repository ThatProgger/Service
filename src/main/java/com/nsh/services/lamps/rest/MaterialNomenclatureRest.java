package com.nsh.services.lamps.rest;

import com.google.gson.Gson;
import com.nsh.services.lamps.model.MaterialNomenclature;
import com.nsh.services.lamps.service.MaterialNomenclature.MaterialNomenclatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/material-nomenclatures")
public class MaterialNomenclatureRest {

    @Autowired
    private MaterialNomenclatureService materialNomenclatureService;

    @GetMapping
    public String getMaterialNomenclature(){
        List<MaterialNomenclature> materialNomenclatureRestList = materialNomenclatureService.findAll();
        return new Gson().toJson(materialNomenclatureRestList);
    }
}
