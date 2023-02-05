package com.nsh.services.lamps.controller;

import com.nsh.services.lamps.component.TimestampMarker;
import com.nsh.services.lamps.enums.LifeCycle;
import com.nsh.services.lamps.model.Lamp;
import com.nsh.services.lamps.service.Lamp.LampService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lamps")
public class TechnicalPage {

    private Logger logger = LogManager.getLogger(TechnicalPage.class);

    @Autowired
    private LampService lampService;
    @Autowired
    private TimestampMarker timestampMarker;


    @GetMapping("/repair")
    public String getLamp(Model model) {
        Iterable<Lamp> damagedLamps = lampService.findAllByDamagedStatus();
        Iterable<Lamp> inProgressLamps = lampService.findAllByInProgressStatus();
        Iterable<Lamp> repairedLamps = lampService.findAllByRepairedStatus();

        model.addAttribute("main", "Sfd8zrP7");
        model.addAttribute("section", "Sfd8zrP12");
        model.addAttribute("toRepair", true);
        model.addAttribute("brokenLamps", damagedLamps);
        model.addAttribute("inProcessLamps", inProgressLamps);
        model.addAttribute("refurbishedLamps", repairedLamps);
        return "lamps";
    }

    @GetMapping("/repair/{id}")
    public String getLampById(@PathVariable int id, Model model) {
        Lamp lamp = lampService.findLampById(id);
        if (lamp.getLifeCycle() != LifeCycle.inProgress)
            return "redirect:/lamps/repair";

        Iterable<Lamp> damagedLamps = lampService.findAllByDamagedStatus();
        Iterable<Lamp> inProgressLamps = lampService.findAllByInProgressStatus();
        Iterable<Lamp> repairedLamps = lampService.findAllByRepairedStatus();


        model.addAttribute("lamp", lamp);
        model.addAttribute("main", "Sfd8zrP7");
        model.addAttribute("section", "Sfd8zrP13");
        model.addAttribute("toRepair", true);
        model.addAttribute("brokenLamps", damagedLamps);
        model.addAttribute("inProcessLamps", inProgressLamps);
        model.addAttribute("refurbishedLamps", repairedLamps);

        return "lamps";
    }


    @GetMapping("/inProcess/{id}")
    public String inProcess(@PathVariable int id) {
        lampService.inProgress(id);
        timestampMarker.setTimestamp(System.currentTimeMillis());
        return "redirect:/lamps/repair";
    }
}
