package com.nsh.services.lamps.rest;

import com.nsh.services.lamps.component.TimestampMarker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/timestamp")
public class Timestamp {
    @Autowired
    private TimestampMarker timestampMarker;


    @GetMapping
    public String getTimestamp (){
        return String.valueOf(timestampMarker.getTimestamp());
    }
}
