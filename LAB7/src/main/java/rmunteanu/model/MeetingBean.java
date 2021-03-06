package rmunteanu.model;

import rmunteanu.entities_model.MeetingEntity;
import rmunteanu.postgres_connection.ManagerMeeting;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
@ManagedBean(name = "meetingBean")
@RequestScoped

public class MeetingBean implements Serializable {
//    private Integer id;
//    private String topic;
//    private Integer duration;
//
//    private Date startingTime;
    @EJB
    private ManagerMeeting managerMeeting;

    private List<PersonBean> personsSelected;
    private LocationBean locationSelected;
    MeetingEntity entity = new MeetingEntity();

    public ManagerMeeting getManagerMeeting(){
        return managerMeeting;
    }
    @PostConstruct
    public void init() {
        List<Date> invalidDates = new ArrayList<>();
        Date today = new Date();
        invalidDates.add(today);
        long oneDay = 24 * 60 * 60 * 1000;
        for (int i = 0; i < 5; i++) {
            invalidDates.add(new Date(invalidDates.get(i).getTime() + oneDay));
        }

        List<Integer> invalidDays = new ArrayList<>();
        invalidDays.add(0); /* the first day of week is disabled */
        invalidDays.add(3);

    }

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getTopic() {
//        return topic;
//    }
//
//    public void setTopic(String topic) {
//        this.topic = topic;
//    }
//
//    public Date getStartingTime() {
//        return startingTime;
//    }
//
//    public void setStartingTime(Date startingTime) {
//        this.startingTime = startingTime;
//    }
//
//    public Integer getDuration() {
//        return duration;
//    }
//
//    public void setDuration(Integer duration) {
//        this.duration = duration;
//    }

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

    public MeetingEntity getEntity() {
        return entity;
    }
    public void setEntity(MeetingEntity entity) {
        this.entity = entity;
    }
}
