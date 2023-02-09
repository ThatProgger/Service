package com.nsh.services.lamps.dtos.journalEntryDto.Impl;

import com.nsh.services.lamps.dtos.journalEntryDto.JournalEntryDto;
import com.nsh.services.lamps.model.Lamp;
import com.nsh.services.user.roleService.RoleService;
import com.nsh.services.user.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * The class implements JournalEntryDto interface and allows you to transfer data to the client side
 * @author Mikhail Dedyukhin
 * @since 1.0
 * @see com.nsh.services.lamps.dtos.journalEntryDto.JournalEntryDto
 */

public class JournalEntryDtoImpl1 implements JournalEntryDto{

    @Autowired
    private UserService userService;
    private List<Lamp> lamps;
    private int lampNumber;

    public JournalEntryDtoImpl1(int lampNumber, List<Lamp> lamps) {
        this.lamps = lamps;
        this.lampNumber = lampNumber;
    }

    @Override
    public int getLampNumber() {
        return lampNumber;
    }

    @Override
    public int getCollectionSize() {
        return lamps.size();
    }

    @Override
    public String getLastName() {
        Comparator<Lamp>  lampComparator = Comparator.comparing(Lamp::getId);
        Lamp lamp = lamps.stream().max(lampComparator).get();
        return lamp.getExecutor();
    }

    @Override
    public Date getLastDate() {
        Comparator<Lamp>  lampComparator = Comparator.comparing(Lamp::getId);
        Lamp lamp = lamps.stream().max(lampComparator).get();
        return lamp.getUpdated();
    }

    @Override
    public List<Lamp> getCollection() {
        return lamps;
    }
}
