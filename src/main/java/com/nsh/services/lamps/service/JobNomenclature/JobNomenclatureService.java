package com.nsh.services.lamps.service.JobNomenclature;

import com.nsh.services.lamps.model.JobNomenclature;

import java.util.List;

/**
 * The interface allows you to work with JobNomenclature objects
 * @author Mikhail Dedyukhin
 * @since 1.0
 * @see JobNomenclature
 */
public interface JobNomenclatureService {

    public List<JobNomenclature> findAll ();
    public long getJobNomenclatureCount ();
    public boolean save (JobNomenclature jobNomenclature);
}
