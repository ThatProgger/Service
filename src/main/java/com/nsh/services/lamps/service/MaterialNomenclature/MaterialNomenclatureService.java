package com.nsh.services.lamps.service.MaterialNomenclature;


import com.nsh.services.lamps.model.MaterialNomenclature;

import java.util.List;

/**
 * The interface allows you to work with MaterialNomenclatureRest objects
 * @author Mikhail Dedyukhin
 * @since 1.0
 * @see MaterialNomenclature
 */
public interface MaterialNomenclatureService {
    public boolean save (MaterialNomenclature materialNomenclature);
    public List<MaterialNomenclature> findAll ();
    public long getMaterialNomenclatureCount ();
}
