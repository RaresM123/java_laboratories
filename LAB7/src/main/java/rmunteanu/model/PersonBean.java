package rmunteanu.model;

import rmunteanu.entities_model.PersonEntity;
import rmunteanu.postgres_connection.ManagerPerson;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean(name = "personBean")
@RequestScoped
public class PersonBean implements Serializable {
//    private String name;
//    private Integer id;
//
//    public PersonBean(){};
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

    @EJB
    private ManagerPerson managerPerson;

    private PersonEntity entity = new PersonEntity();

    public ManagerPerson getManagerPerson() {
        return managerPerson;

    }    public PersonEntity getEntity() {
        return entity;
    }

    public void setEntity(PersonEntity entity) {
        this.entity = entity;
    }
}