/*
 * Copyright (c) 2020.
 */

package rmunteanu.model;

import rmunteanu.dbutils.dbUtils;
import rmunteanu.postgres_connection.ManagerMeeting;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "MeetingSearch")
@SessionScoped
public class SearchMeetingBean implements Serializable {

    @EJB
    ManagerMeeting managerMeeting;

    private List<String> selectedItems;
    private String SearchItem;
    private List<MeetingBean> meetingsBeanEntities = new ArrayList<>();

    public SearchMeetingBean() {
        meetingsBeanEntities = managerMeeting.getMeetings();
    }

    public String getSearchItem() {
        return SearchItem;
    }

    public void setSearchItem(String searchItem) {
        this.SearchItem = searchItem;
    }

    public List<String> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<String> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public List<MeetingBean> getMeetingsBeanEntities() {
        return meetingsBeanEntities;
    }

    public void SearchCompleteItem(){
        String topic = null;
        String personName = null;
        String location = null;
        if(selectedItems.size()==0) {
            meetingsBeanEntities = managerMeeting.getMeetings();
            return;
        }
        if(selectedItems.contains("topic")){
            topic = SearchItem;
        }
        if(selectedItems.contains("name")){
            personName = SearchItem;
        }
        if(selectedItems.contains("location")){
            location = SearchItem;
        }
        meetingsBeanEntities = ManagerBean.MeetingsSearchAlg(topic,personName,location);
    }
}