/*
 * Copyright (c) 2020.
 */

package rmunteanu.postgres_connection;

import rmunteanu.entities_model.MeetingEntity;
import rmunteanu.model.PersonBean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.*;

public class AvailabilityRepartation {
    @PersistenceContext
    private EntityManager em;

    public boolean available(BigDecimal meetingID, Integer capacity, List<PersonBean> persons){
        MeetingEntity getMeeting = em.find(MeetingEntity.class, meetingID);

        return (getMeeting.getEntityPersons().size() + persons.size()) <= capacity;
    }
}
