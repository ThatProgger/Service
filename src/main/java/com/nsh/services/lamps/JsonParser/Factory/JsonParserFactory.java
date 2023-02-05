package com.nsh.services.lamps.JsonParser.Factory;

import com.nsh.services.lamps.JsonParser.Enum.JsonType;
import com.nsh.services.lamps.JsonParser.Impl.JsonParserByLampSubmit;
import com.nsh.services.lamps.JsonParser.JsonParser;

/**
 * The final  class implements the factory programming pattern and allows you to implement the JsonParser interface
 * @author Mikhail Dedyukhin
 * @since 1.0
 * @see JsonParser
 */

public final class JsonParserFactory {
    private JsonParserFactory (){};

    /**
     * Allows you to create an implementation of the {@link JsonParser} interface.
     * @param jsonType Specifies which implementation to create.
     * @return implemented interface
     * @throws IllegalArgumentException - if there is no suitable value in the switch - case block.
     */
    public static JsonParser create (JsonType jsonType){
        JsonParser jsonParser = null;
        switch (jsonType){
            case LampSubmit: jsonParser = new JsonParserByLampSubmit(); break;
            default: throw  new IllegalArgumentException("The JsonType incorrect");
        }

        return jsonParser;
    }
}
