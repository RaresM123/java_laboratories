/*
 * Copyright (c) 2020.
 */

package rmunteanu.model;

import rmunteanu.dbutils.dbUtils;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "MeetingSearch")
@SessionScoped
public class SearchMeetingBean implements Serializable {
    private List<String> selectedItems;
    private String SearchItem;
    private List<MeetingBean> meetingsBeanEntities = new ArrayList<>();

    public SearchMeetingBean() {
        meetingsBeanEntities = dbUtils.retrieveMeetings();
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
            meetingsBeanEntities = dbUtils.retrieveMeetings();
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