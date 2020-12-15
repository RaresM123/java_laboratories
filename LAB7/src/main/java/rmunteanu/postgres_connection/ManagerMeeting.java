/*
 * Copyright (c) 2020.
 */

package rmunteanu.postgres_connection;

import rmunteanu.entities_model.LocationEntity;
import rmunteanu.entities_model.MeetingEntity;
import rmunteanu.entities_model.PersonEntity;
import rmunteanu.model.LocationBean;
import rmunteanu.model.MeetingBean;
import rmunteanu.model.PersonBean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

public class ManagerMeeting extends RepositoryDataManager<MeetingEntity,Integer>{

    @PersistenceContext
    private EntityManager em;

    public ManagerMeeting(){
        super(MeetingEntity.class);
    }

    public void addMeeting(MeetingBean m){
        LocationEntity location = m.getLocationSelected().getEntity();
        MeetingEntity meetingEntity = m.getEntity();
        meetingEntity.setEntityLocation(location);

        List<PersonEntity> persons = new ArrayList<>();
        for (PersonBean personBean : m.getPersonsSelected()){
            persons.add(personBean.getEntity());
        }

        meetingEntity.setEntityPersons(persons);
        this.persist(meetingEntity);
    }

    public List<MeetingBean> getMeetings(){
        List<MeetingEntity> entities = this.findAll();

        List<MeetingBean> meetingList = new ArrayList<>();
        for (MeetingEntity entity : entities)
        {
            MeetingBean meetingBean = new MeetingBean();
            meetingBean.setEntity(entity);

            LocationBean locationBean = new LocationBean();
            locationBean.setEntity(entity.getEntityLocation());
            meetingBean.setLocationSelected(locationBean);

            List<PersonBean> persons = new ArrayList<>();
            for(PersonEntity p : entity.getEntityPersons()){
                PersonBean personBean = new PersonBean();
                personBean.setEntity(p);
                persons.add(personBean);
            }
            meetingBean.setPersonsSelected(persons);
            meetingList.add(meetingBean);
        }
        return meetingList;
    }

}
