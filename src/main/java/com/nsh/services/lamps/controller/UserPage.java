package com.nsh.services.lamps.controller;


import com.nsh.services.lamps.component.TimestampMarker;
import com.nsh.services.lamps.enums.LampSaveStatus;
import com.nsh.services.lamps.model.DefectNomenclature;
import com.nsh.services.lamps.model.Lamp;
import com.nsh.services.lamps.service.DefectNomenclature.DefectNomenclatureService;
import com.nsh.services.lamps.service.Lamp.LampService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

/**
 * Controller class for /lamp/*
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
@Controller
@RequestMapping("/lamps")
public class UserPage {
    private Logger logger = LogManager.getLogger(UserPage.class);
    @Autowired
    private DefectNomenclatureService defectNomenclatureService;

    @Autowired
    private LampService lampService;


    @Autowired
    private TimestampMarker timestampMarker;

    @GetMapping("/add")
    public String getLamp(Model model) {
        Iterable<DefectNomenclature> defectNomenclatures = defectNomenclatureService.findAll();
        Iterable<Lamp> brokenLamps = lampService.findAllByDamagedStatus();
        Iterable<Lamp> inProcessLamps = lampService.findAllByInProgressStatus();
        Iterable<Lamp> refurbishedLamps = lampService.findAllByRepairedStatus();

        model.addAttribute("main", "Sfd8zrP6");
        model.addAttribute("selectStatus", true);
        model.addAttribute("toCanceled", true);
        model.addAttribute("brokenLamps", brokenLamps);
        model.addAttribute("inProcessLamps", inProcessLamps);
        model.addAttribute("refurbishedLamps", refurbishedLamps);
        model.addAttribute("defectNomenclatures", defectNomenclatures);

        model.addAttribute("lamp", new Lamp());

        return "lamps";
    }

    @PostMapping("/add")
    public String postLamp(@ModelAttribute Lamp lamp, RedirectAttributes redirectAttributes, Principal principal) {

        boolean correctFlag = false;
        String message = "No message";
        int lampNumber = lamp.getLampNumber();


//        LampSaveStatus lampSaveStatus = lampService.save(lamp);
        LampSaveStatus lampSaveStatus = lampService.save(lamp, principal);

        if(lampSaveStatus == LampSaveStatus.IncorrectLampNumber)
            message = String.format("Лампа №%d - указали неккоректный номер!", lampNumber);

        if(lampSaveStatus == LampSaveStatus.IncorrectLampStatus)
            message = String.format("Лампа №%d - уже занесена в журнал!", lampNumber);

        if(lampSaveStatus == LampSaveStatus.NotArguments)
            message = String.format("Лампа №%d - неисправность не указана!", lampNumber);

        if(lampSaveStatus == LampSaveStatus.Error)
            message = String.format("Лампа №%d - ошибка сохранения!", lampNumber);

        if(lampSaveStatus == LampSaveStatus.Success){
            message = String.format("Лампа №%d - добавлена в журнал!", lampNumber);
            correctFlag = true;
        }


        redirectAttributes.addFlashAttribute("lampStatus", correctFlag);
        redirectAttributes.addFlashAttribute("message", message);

        timestampMarker.setTimestamp(System.currentTimeMillis());

        if(logger.isInfoEnabled())
            logger.info("[postLamp] - the lamp: {}, initiator: {}, created: {}", lamp.getLampNumber(), principal.getName(), lamp.getCreated());
        return "redirect:/lamps/add";
    }



    @GetMapping("/canceled/{id}")
    public String canceled (@PathVariable int id){
        lampService.completed(id);
        timestampMarker.setTimestamp(System.currentTimeMillis());
        return "redirect:/lamps/add";
    }

    @GetMapping("/broken/{id}")
    public String broken (@PathVariable int id){
        lampService.damaged(id);
        timestampMarker.setTimestamp(System.currentTimeMillis());
        return "redirect:/lamps/add";
    }

    @GetMapping("/error/{id}")
    public String error (@PathVariable int id){
        lampService.inError(id);
        timestampMarker.setTimestamp(System.currentTimeMillis());
        return "redirect:/lamps/add";
    }
}
