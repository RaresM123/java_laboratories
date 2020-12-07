package rmunteanu.model;

import rmunteanu.entities_model.LocationEntity;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean(name = "locationBean")
@RequestScoped
public class LocationBean implements Serializable {
//    private String name;
//    private Integer id;
//
//    public LocationBean(){};
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String toString() {
//        return name;
//    }
    LocationEntity entity = new LocationEntity();

    public LocationEntity getEntity() {
        return entity;
    }

    public void setEntity(LocationEntity entity) {
        this.entity = entity;
    }
}
