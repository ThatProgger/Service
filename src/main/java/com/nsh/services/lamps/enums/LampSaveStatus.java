package com.nsh.services.lamps.enums;

/**
 * Enumeration allows you to specify the outcome of the save operation of the lamp object
 * @author Mikhail Dedyukhin
 * @since 1.0
 * @see com.nsh.services.lamps.model.Lamp
 */
public enum LampSaveStatus {
    Success, Error, IncorrectLampStatus, IncorrectLampNumber, NotArguments
}
