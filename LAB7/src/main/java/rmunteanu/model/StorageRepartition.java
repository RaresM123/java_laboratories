/*
 * Copyright (c) 2020.
 */

package rmunteanu.model;

import rmunteanu.entities_model.MeetingEntity;
import rmunteanu.entities_model.PersonEntity;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.util.HashMap;
import java.util.List;

@ConcurrencyManagement(javax.ejb.ConcurrencyManagementType.CONTAINER)
@Singleton
@Startup

public class StorageRepartition {

    HashMap<MeetingEntity, List<PersonEntity>> repartitions;

    @PostConstruct
    public void init() {
        repartitions = new HashMap<>();
    }

    @Lock(LockType.WRITE)
    public void addRepartition(MeetingEntity meeting, List<PersonEntity> personList){
        repartitions.put(meeting,personList);
    }
}
