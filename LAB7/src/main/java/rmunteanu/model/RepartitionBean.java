/*
 * Copyright (c) 2020.
 */

package rmunteanu.model;

import rmunteanu.postgres_connection.ManagerRepartition;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name="repartitionBean")
@SessionScoped
public class RepartitionBean implements Serializable {

    @EJB
    private ManagerRepartition managerRepartition;

    List<PersonBean> selectedPersons;
    MeetingBean selectedMeeting;

    public ManagerRepartition getManagerRepartition() {
        return managerRepartition;
    }

    public MeetingBean getSelectedMeeting() {
        return selectedMeeting;
    }

    public void setSelectedMeeting(MeetingBean selectedMeeting) {
        this.selectedMeeting = selectedMeeting;
    }

    public List<PersonBean> getSelectedPersons() {
        return selectedPersons;
    }

    public void setSelectedPersons(List<PersonBean> selectedPersons) {
        this.selectedPersons = selectedPersons;
    }
}
