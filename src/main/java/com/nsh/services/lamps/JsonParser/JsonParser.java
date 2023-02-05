package com.nsh.services.lamps.JsonParser;

/**
 * This interface allows you to parse Json string to object.
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public interface JsonParser <T> {

    public T parse (String json);
}
