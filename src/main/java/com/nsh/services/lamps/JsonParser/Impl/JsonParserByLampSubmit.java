package com.nsh.services.lamps.JsonParser.Impl;

import com.google.gson.Gson;
import com.nsh.services.lamps.JsonParser.JsonParser;
import com.nsh.services.lamps.model.SubmitLamp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class implements JsonParser interface and allows you to parse json string to object.
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public class JsonParserByLampSubmit implements JsonParser<SubmitLamp> {
    private Logger logger = LogManager.getLogger(JsonParserByLampSubmit.class);

    @Override
    public SubmitLamp parse(String json) {
        Gson gson = new Gson();
        SubmitLamp submitLamp = gson.fromJson(json, SubmitLamp.class);

        if(logger.isInfoEnabled())
            logger.info("[JsonParserByLampSubmit] - json string parsed to {} object", submitLamp.getClass().getSimpleName());
        return submitLamp;
    }
}
