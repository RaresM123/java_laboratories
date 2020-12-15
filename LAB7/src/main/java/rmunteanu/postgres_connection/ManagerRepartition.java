/*
 * Copyright (c) 2020.
 */

package rmunteanu.postgres_connection;

import rmunteanu.entities_model.MeetingEntity;
import rmunteanu.entities_model.PersonEntity;
import rmunteanu.model.PersonBean;
import rmunteanu.model.RepartitionBean;
import rmunteanu.model.StorageRepartition;
import rmunteanu.test_package.NewCustomInterceptor;

import javax.ejb.EJB;
import javax.interceptor.Interceptor;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Interceptors(NewCustomInterceptor.class)
public class ManagerRepartition {
    @EJB
    private AvailabilityRepartation checkAvailabilityRepartition;
    @EJB
    private StorageRepartition storageRepartion;
    @PersistenceContext
    private EntityManager em;

    public void addRepartition(RepartitionBean repartition){

        if(checkAvailabilityRepartition.available(repartition.getSelectedMeeting().getEntity().getId(),
                repartition.getSelectedMeeting().getEntity().getEntityCapacity(),
                repartition.getSelectedPersons()))
        {
            MeetingEntity meetingNew = repartition.getSelectedMeeting().getEntity();
            List<PersonEntity> personList = meetingNew.getEntityPersons();
            for(PersonBean p : repartition.getSelectedPersons()){
                if(!personList.contains(p.getEntity()))
                    personList.add(p.getEntity());
            }
            meetingNew.setEntityPersons(personList);
            em.merge(meetingNew);
            storageRepartion.addRepartition(meetingNew,meetingNew.getEntityPersons());
        }
    }

}
