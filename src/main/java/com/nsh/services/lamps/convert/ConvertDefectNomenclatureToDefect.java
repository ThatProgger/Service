package com.nsh.services.lamps.convert;


import com.nsh.services.lamps.model.Defect;
import com.nsh.services.lamps.model.DefectNomenclature;
import com.nsh.services.lamps.repository.DefectNomenclatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * The class implements Converter interface and convert {@link DefectNomenclature} object to {@link Defect} object.
 * @author Mikhail Dedyukhin
 * @see org.springframework.core.convert.converter.Converter
 */
@Component
public class ConvertDefectNomenclatureToDefect implements Converter<String, Defect> {
    @Autowired
    private DefectNomenclatureRepository defectNomenclatureRepository;

    @Override
    public Defect convert(String source) {
        Optional<DefectNomenclature> defectNomenclature = defectNomenclatureRepository.findById(Integer.valueOf(source));
        if(defectNomenclature.isPresent()){
            Defect defect = Defect.builder()
                    .id(0).name(defectNomenclature.get().getNomenclature()).build();
            return defect;
        }
        return null;
    }
}
