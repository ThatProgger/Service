package com.nsh.services.lamps.dtos.LampSubmitDto.Impl;

import com.nsh.services.lamps.model.Job;
import com.nsh.services.lamps.model.Material;
import com.nsh.services.lamps.model.Note;
import lombok.Data;

import java.util.List;

/**
 * TODO: make description
 */
@Data
public class SubmitLamp {
    private int id;
    private List<Material> materials;
    private List<Job> jobs;
    private List<Note> notes;
}
