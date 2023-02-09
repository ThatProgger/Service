package com.nsh.services.lamps.dtos.journalEntryDto.factory;

import com.nsh.services.lamps.dtos.journalEntryDto.Impl.JournalEntryDtoImpl1;
import com.nsh.services.lamps.dtos.journalEntryDto.JournalEntryDto;
import com.nsh.services.lamps.dtos.journalEntryDto.enums.JournalEntryType;

import java.util.List;

/**
 * The factory class allows you to specify the type of implementation depending on the type of enumeration.
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public final class JournalEntryFactory {
    private JournalEntryFactory () {}

    public static JournalEntryDto create (JournalEntryType journalEntryType, List collection, int lampNumber){
        JournalEntryDto journalEntryDto = null;
        switch (journalEntryType){
            case Default: journalEntryDto = new JournalEntryDtoImpl1(lampNumber, collection); break;
            default: throw new IllegalArgumentException("The journalEntryType incorrect");
        }

        return journalEntryDto;
    }
}
