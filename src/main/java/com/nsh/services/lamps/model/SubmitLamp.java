package com.nsh.services.lamps.model;

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
