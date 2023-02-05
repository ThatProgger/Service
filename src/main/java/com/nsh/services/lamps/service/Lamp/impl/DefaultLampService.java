package com.nsh.services.lamps.service.Lamp.impl;

import com.nsh.services.lamps.enums.LampSaveStatus;
import com.nsh.services.lamps.enums.LifeCycle;
import com.nsh.services.lamps.model.Lamp;
import com.nsh.services.lamps.repository.LampRepository;
import com.nsh.services.lamps.service.Lamp.LampService;
import com.nsh.services.user.model.User;
import com.nsh.services.user.userService.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

/**
 * The class implements the LampService interface and allows you to work with lamp objects
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */

@Service
public class DefaultLampService implements LampService {
    private Logger logger = LogManager.getLogger(DefaultLampService.class);

    @Autowired
    private LampRepository lampRepository;

    @Autowired
    private UserService userService;

    @Override
    public Iterable<Lamp> findAllByDamagedStatus() {
        Iterable<Lamp> lampIterable = lampRepository.findByLifeCycleOrderByLampNumber(LifeCycle.damaged);
        long size = lampIterable.spliterator().getExactSizeIfKnown();
        if(logger.isInfoEnabled())
            logger.info("[findAllByDamagedStatus] - collection size: {} ", size);
        return lampIterable;
    }

    @Override
    public Iterable<Lamp> findAllByInProgressStatus() {
        Iterable<Lamp> lampIterable = lampRepository.findByLifeCycleOrderByLampNumber(LifeCycle.inProgress);
        long size = lampIterable.spliterator().getExactSizeIfKnown();
        if(logger.isInfoEnabled())
            logger.info("[findAllByInProgressStatus] - collection size: {} ", size);
        return lampIterable;
    }

    @Override
    public Iterable<Lamp> findAllByRepairedStatus() {
        Iterable<Lamp> lampIterable = lampRepository.findByLifeCycleOrderByLampNumber(LifeCycle.repaired);
        long size = lampIterable.spliterator().getExactSizeIfKnown();
        if(logger.isInfoEnabled())
            logger.info("[findAllByRepairedStatus] - collection size: {} ", size);
        return lampIterable;
    }

    @Override
    public Lamp findLampById(int id) {
        Optional<Lamp> optionalLamp = lampRepository.findById(id);
        try {
            optionalLamp = lampRepository.findById(id);
        } catch (IllegalArgumentException e) {
            if(logger.isErrorEnabled()){
                logger.error(e.getMessage());
                e.printStackTrace();
            }

            return null;
        }

        if(optionalLamp.isPresent()){
            if(logger.isInfoEnabled())
                logger.info("[findLampById] - The lamp by ID: {} found", optionalLamp.get().getId());
            return optionalLamp.get();
        }

        return null;
    }

    @Override
    public LampSaveStatus save(Lamp lamp) {
        int lampNumber = lamp.getLampNumber();
        if (lampNumber < 1)
            return LampSaveStatus.IncorrectLampNumber;

        if (lampRepository.existsByLampNumberAndLifeCycle(lampNumber, LifeCycle.damaged))
            return LampSaveStatus.IncorrectLampStatus;

        if (lampRepository.existsByLampNumberAndLifeCycle(lampNumber, LifeCycle.inProgress))
            return LampSaveStatus.IncorrectLampStatus;
        if (lampRepository.existsByLampNumberAndLifeCycle(lampNumber, LifeCycle.repaired))
            return LampSaveStatus.IncorrectLampStatus;

        if (lamp.getDefects().size() == 0 && lamp.getMessage().equals(""))
            return LampSaveStatus.NotArguments;


        lamp.setId(0);
        lamp.setLifeCycle(LifeCycle.damaged);


        try {
            if (lamp.equals(lampRepository.save(lamp))) {
                logger.info("[save] - lamp: {} successfully stored", lamp);
            }
        } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }

            return LampSaveStatus.Error;
        }

        return LampSaveStatus.Success;
    }

    @Override
    public LampSaveStatus save(Lamp lamp, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        lamp.setInitiator(user.getFullName());

        int lampNumber = lamp.getLampNumber();
        if (lampNumber < 1)
            return LampSaveStatus.IncorrectLampNumber;

        if (lampRepository.existsByLampNumberAndLifeCycle(lampNumber, LifeCycle.damaged))
            return LampSaveStatus.IncorrectLampStatus;

        if (lampRepository.existsByLampNumberAndLifeCycle(lampNumber, LifeCycle.inProgress))
            return LampSaveStatus.IncorrectLampStatus;
        if (lampRepository.existsByLampNumberAndLifeCycle(lampNumber, LifeCycle.repaired))
            return LampSaveStatus.IncorrectLampStatus;

        if (lamp.getDefects().size() == 0 && lamp.getMessage().equals(""))
            return LampSaveStatus.NotArguments;


        lamp.setId(0);
        lamp.setLifeCycle(LifeCycle.damaged);


        try {
            if (lamp.equals(lampRepository.save(lamp))) {
                logger.info("[save] - lamp: {} successfully stored", lamp);
            }
        } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }

            return LampSaveStatus.Error;
        }

        return LampSaveStatus.Success;
    }

    @Override
    public boolean completed(int id) {
        Optional<Lamp> optionalLamp = lampRepository.findById(id);
        if (optionalLamp.isPresent()) {
            Lamp lamp = optionalLamp.get();
            lamp.setLifeCycle(LifeCycle.completed);
//            lamp.setDateTo(new Date());
            try {
                if (lamp.equals(lampRepository.save(lamp))) {
                    if (logger.isInfoEnabled())
                        logger.info("[completed] - The status lamp by id {} has been changed to canceled", id);
                    return true;
                }
            } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
                if (logger.isErrorEnabled()) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }

                return false;
            }
        }
        return false;
    }

    @Override
    public boolean inError(int id) {
        Optional<Lamp> optionalLamp = lampRepository.findById(id);
        if (optionalLamp.isPresent()) {
            Lamp lamp = optionalLamp.get();

            if(lamp.getMaterials().size() == 0 && lamp.getJobs().size() == 0 && lamp.getNotes().size() == 0){
                lamp.setLifeCycle(LifeCycle.inError);
                if(logger.isInfoEnabled())
                    logger.info("[inError] - the lamp status changed to error");
            } else {
                lamp.setLifeCycle(LifeCycle.completed);
                if(logger.isInfoEnabled())
                    logger.info("[inError] - the lamp status changed to completed");
            }


//            lamp.setDateTo(new Date());
            try {
                if (lamp.equals(lampRepository.save(lamp))) {
                    if (logger.isInfoEnabled())
                        logger.info("[inError] - The status lamp by id {} has been changed", id);
                    return true;
                }
            } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
                if (logger.isErrorEnabled()) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }

                return false;
            }
        }
        return false;
    }

    @Override
    public boolean damaged(int id) {
        Optional<Lamp> optionalLamp = lampRepository.findById(id);
        if (optionalLamp.isPresent()) {
            Lamp lamp = optionalLamp.get();
            lamp.setLifeCycle(LifeCycle.damaged);
            try {
                if (lamp.equals(lampRepository.save(lamp))) {
                    if (logger.isInfoEnabled())
                        logger.info("[damaged] - The status lamp by id {} has been changed to error", id);
                    return true;
                }
            } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
                if (logger.isErrorEnabled()) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean inProgress(int id) {
        Optional<Lamp> optionalLamp = lampRepository.findById(id);

        if (optionalLamp.isPresent()) {
            Lamp lamp = optionalLamp.get();
            lamp.setLifeCycle(LifeCycle.inProgress);
            try {
                if (lamp.equals(lampRepository.save(lamp))) {
                    if (logger.isInfoEnabled())
                        logger.info("[inProgress] - The status lamp by id {} has been changed to error", id);
                    return true;
                }
            } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
                if (logger.isErrorEnabled()) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    @Override
    public boolean update(Lamp lamp) {
        if (lamp == null) {
            if (logger.isInfoEnabled())
                logger.info("[update] - the lamp object is null");
        }

        try {
            Lamp updated = lampRepository.save(lamp);
            if (logger.isInfoEnabled())
                logger.info("[update] - The lamp update by ID: {} successful", updated.getId());
            return true;
        } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
            logger.error("[update] - update error of the lamp by ID: ", lamp.getId());
        }

        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            lampRepository.deleteById(id);
            if (logger.isInfoEnabled())
                logger.info("[deleteById] - the lamp by ID: {} was removed successfully", id);
        } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
            logger.error("[deleteById] - unable to delete by specified ID: {}, the message: {}", id, e.getMessage());
        }
        return true;
    }
}
