package com.nsh.services.lamps.service.Lamp;


import com.nsh.services.lamps.enums.LampSaveStatus;
import com.nsh.services.lamps.model.Lamp;

import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * The interface allows you to work with the Lamp object
 *
 * @author Mikhail Dedyukhin
 * @see Lamp
 * @since 1.0
 */
public interface LampService {
    public boolean deleteById(int id);

    public Iterable<Lamp> findAllByDamagedStatus();

    public Iterable<Lamp> findAllByInProgressStatus();

    public Iterable<Lamp> findAllByRepairedStatus();

    public Iterable<Lamp> findAllByCompletedStatus ();

    public Map<Integer, List<Lamp>> findAllByGroup ();

    public Lamp findLampById(int id);

    public LampSaveStatus save(Lamp lamp);

    public LampSaveStatus save(Lamp lamp, Principal principal);

    public boolean completed(int id);

    public boolean inError(int id);

    public boolean damaged(int id);

    public boolean inProgress(int id);

    public boolean update(Lamp lamp);
}
