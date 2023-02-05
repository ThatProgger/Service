package com.nsh.services.lamps.service.JobNomenclature.impl;

import com.nsh.services.lamps.model.JobNomenclature;
import com.nsh.services.lamps.repository.JobNomenclatureRepository;
import com.nsh.services.lamps.service.JobNomenclature.JobNomenclatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementing the DefectNomenclatureService interface.
 * The class allows you to store and retrieve data from a table
 * @author Mikhail Dedyukhin
 * @since 1.1
 * @see JobNomenclatureService
 */
@Service
public class JobNomenclatureServiceImpl1 implements JobNomenclatureService {
    @Autowired
    private JobNomenclatureRepository jobNomenclatureRepository;


    public boolean save (JobNomenclature jobNomenclature){
        return jobNomenclature.equals(jobNomenclatureRepository.save(jobNomenclature));
    }

    /**
     * Find all entries in job_nomenclature table and then convert from iterable to list.
     * @return list of JobNomenclature objects
     */
    public List<JobNomenclature> findAll (){
        Iterable<JobNomenclature> jobs = jobNomenclatureRepository.findAll();;
        List<JobNomenclature> jobNomenclatureList = new ArrayList<>();
        jobs.forEach(jobNomenclatureList::add);
        return jobNomenclatureList;
    }

    public long getJobNomenclatureCount (){
        return jobNomenclatureRepository.count();
    }
}
