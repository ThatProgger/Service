package com.nsh.services.lamps.repository;


import com.nsh.services.lamps.enums.LifeCycle;
import com.nsh.services.lamps.model.Lamp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface extends the CrudRepository interface.
 * @see org.springframework.data.repository.CrudRepository
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
@Repository
public interface LampRepository extends CrudRepository<Lamp, Integer> {


    public List<Lamp> findByLifeCycleOrderByLampNumber(LifeCycle lifeCycle);
    public boolean existsByLampNumberAndLifeCycle(int lampNumber, LifeCycle lifeCycle);

}
