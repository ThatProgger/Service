package com.nsh.services.lamps.dtos.journalEntryDto;

import com.nsh.services.lamps.model.Lamp;
import com.nsh.services.lamps.model.Material;

import java.util.Date;
import java.util.List;

/**
 * The interface allows you to pass a Dto class to the client side.
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public interface JournalEntryDto {

    public int getLampNumber ();
    public int getCollectionSize ();
    public String getLastName ();
    public Date getLastDate ();

    public List<Lamp> getCollection ();
}
