/*
 * Copyright (c) 2020.
 */

package rmunteanu.mongodb_connection;

import rmunteanu.model.LocationBean;
import rmunteanu.model.PersonBean;

import java.util.Date;
import java.util.List;

public class MeetingsMongoDO extends BaseMongoDO {

    private Integer id;
    private String topic;
    private Integer duration;
    private Date startingTime;
    private List<PersonBean> personsSelected;
    private LocationBean locationSelected;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }

    public List<PersonBean> getPersonsSelected() {
        return personsSelected;
    }

    public void setPersonsSelected(List<PersonBean> personsSelected) {
        this.personsSelected = personsSelected;
    }

    public LocationBean getLocationSelected() {
        return locationSelected;
    }

    public void setLocationSelected(LocationBean locationSelected) {
        this.locationSelected = locationSelected;
    }
}