package com.nsh.services.lamps.rest;

import com.google.gson.Gson;
import com.nsh.services.lamps.service.JobNomenclature.JobNomenclatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job-nomenclatures")
public class JobNomenclature {

    @Autowired
    private JobNomenclatureService jobNomenclatureService;

    @GetMapping
    public String getJobNomenclature() {
        return new Gson().toJson(jobNomenclatureService.findAll());
    }
}
