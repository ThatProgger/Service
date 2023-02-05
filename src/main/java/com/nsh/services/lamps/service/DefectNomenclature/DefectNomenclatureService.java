package com.nsh.services.lamps.service.DefectNomenclature;


import com.nsh.services.lamps.model.DefectNomenclature;

/**
 * The interface allows you to work with DefectNomenclature objects
 * @author Mikhail Dedyukhin
 * @since 1.0
 * @see DefectNomenclature
 */
public interface DefectNomenclatureService {

    public boolean save (DefectNomenclature defectNomenclature);
    public Iterable<DefectNomenclature> findAll ();
    public long getDefectNomenclatureCount ();
}
