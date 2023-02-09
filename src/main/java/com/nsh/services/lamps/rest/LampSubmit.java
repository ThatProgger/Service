package com.nsh.services.lamps.rest;

import com.nsh.services.lamps.JsonParser.Enum.JsonType;
import com.nsh.services.lamps.JsonParser.Factory.JsonParserFactory;
import com.nsh.services.lamps.JsonParser.JsonParser;
import com.nsh.services.lamps.component.TimestampMarker;
import com.nsh.services.lamps.dtos.LampSubmitDto.Impl.SubmitLamp;
import com.nsh.services.lamps.enums.LifeCycle;
import com.nsh.services.lamps.model.*;
import com.nsh.services.lamps.service.Lamp.LampService;
import com.nsh.services.user.model.User;
import com.nsh.services.user.userService.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The rest controller is used to receive the submitted form data.
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
@RestController
@RequestMapping("/lamp-submit")
public class LampSubmit {
    private Logger logger = LogManager.getLogger(LampSubmit.class);

    @Autowired
    private LampService lampService;
    @Autowired
    private UserService userService;
    @Autowired
    private TimestampMarker timestampMarker;



    /**
     * Parses a JSON string into its components and then saves it to the database
     *
     * @param json contains a text string to parse the data
     * @return true if the parse is successful or false if the parse fails
     */
    @PostMapping
    public boolean parsingJson(@RequestBody String json, Principal principal) {

        User user = userService.findByUsername(principal.getName());

        JsonParser jsonParser = JsonParserFactory.create(JsonType.LampSubmit);
        SubmitLamp submitLamp = (SubmitLamp) jsonParser.parse(json);

        List<Material> materialSet = filterMaterials(submitLamp.getMaterials());
        List<Job> jobSet = filterJobs(submitLamp.getJobs());
        List<Note> noteSet = filterNotes(submitLamp.getNotes());


        if (materialSet.size() == 0 && jobSet.size() == 0 && noteSet.size() == 0) {
            logger.error("[parsingJson] There are no arguments to write");
            return false;
        }

        if(logger.isInfoEnabled()){
            logger.info("[parsingJson] - The size of material collection: {}", materialSet.size());
            logger.info("[parsingJson] - The size of job collection: {}", jobSet.size());
            logger.info("[parsingJson] - The size of note collection: {}", noteSet.size());
        }


        Lamp lamp = lampService.findLampById(submitLamp.getId());

        materialSet.forEach(material -> {
            lamp.getMaterials().add(Material.builder().id(0).nomenclature(material.getNomenclature()).amount(material.getAmount()).build());
        });

        jobSet.forEach(job -> {
            lamp.getJobs().add(Job.builder().id(0).nomenclature(job.getNomenclature()).build());
        });

        noteSet.forEach(note -> {
            lamp.getNotes().add(Note.builder().id(0).message(note.getMessage()).build());
        });


        lamp.setLifeCycle(LifeCycle.repaired);
        lamp.setExecutor(user.getFullName());

        if (lampService.update(lamp)) {
            timestampMarker.setTimestamp(System.currentTimeMillis());
            return true;
        }
        logger.error("[parsingJson] Failed to save lamp # {} to the database.", lamp.getLampNumber());
        return false;
    }


    /**
     * filters the material collection from unwanted elements.
     *
     * @param materialList unfiltered collection
     * @return filtered collection
     */
    private List<Material> filterMaterials(List<Material> materialList) {
        return materialList.stream().filter(material -> {
            if (material.getNomenclature().equals("No"))
                return false;
            return true;
        }).collect(Collectors.toList());
    }

    /**
     * filters the job collection from unwanted elements.
     *
     * @param jobList unfiltered collection
     * @return filtered collection
     */
    private List<Job> filterJobs(List<Job> jobList) {
        return jobList.stream().filter(job -> {
            if (job.getNomenclature().equals("No"))
                return false;
            return true;
        }).collect(Collectors.toList());
    }


    /**
     * filters the note collection from unwanted elements.
     *
     * @param noteList unfiltered collection
     * @return filtered collection
     */
    private List<Note> filterNotes(List<Note> noteList) {
        return noteList.stream().filter(note -> {
            if (note.getMessage().equals("Введите примечание"))
                return false;
            return true;
        }).collect(Collectors.toList());
    }
}
