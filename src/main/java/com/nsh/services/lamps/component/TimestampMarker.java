package com.nsh.services.lamps.component;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * The class stores timestamp data.
 * On the client side, the timestamp is constantly polled, and if the timestamp differs from the timestamp received at the time of loading,
 * then the client page will be reloaded.
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
@Component
@Data
public class TimestampMarker {
    private long timestamp = System.currentTimeMillis();
}
