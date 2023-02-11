package com.nsh.services.lamps.controller;

import com.nsh.services.lamps.dtos.journalEntryDto.JournalEntryDto;
import com.nsh.services.lamps.dtos.journalEntryDto.enums.JournalEntryType;
import com.nsh.services.lamps.dtos.journalEntryDto.factory.JournalEntryFactory;
import com.nsh.services.lamps.model.Lamp;
import com.nsh.services.lamps.service.Lamp.LampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("lamps")
public class JournalPage {

    @Autowired
    private LampService lampService;

    @GetMapping("journal")
    public String getJournal (Model model){
        Map<Integer, List<Lamp>> map = lampService.findAllByGroup();
        List<JournalEntryDto> journalEntryDtoList = new ArrayList<>();

        for (Map.Entry<Integer, List<Lamp>> entry : map.entrySet())
            journalEntryDtoList.add(JournalEntryFactory.create(JournalEntryType.Default, entry.getValue(), entry.getKey()));


        Comparator<JournalEntryDto> comparator = Comparator.comparing(JournalEntryDto::getLampNumber);
        journalEntryDtoList = journalEntryDtoList.stream().sorted(comparator).collect(Collectors.toList());

        model.addAttribute("journals", journalEntryDtoList);
        model.addAttribute("main", "Sfd8zrP4");
        return "lamps";
    }

}
