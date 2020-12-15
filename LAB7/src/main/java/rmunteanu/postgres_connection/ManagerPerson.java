/*
 * Copyright (c) 2020.
 */

package rmunteanu.postgres_connection;

import rmunteanu.entities_model.PersonEntity;
import rmunteanu.model.PersonBean;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class ManagerPerson extends RepositoryDataManager<PersonEntity,Integer>{
    public ManagerPerson(){
        super(PersonEntity.class);
    }

    public List<PersonBean> getPersons()
    {
        List<PersonEntity> entities = this.findAll();

        List<PersonBean> persons = new ArrayList<>();
        for (PersonEntity entity : entities)
        {
            PersonBean personBean = new PersonBean();
            personBean.setEntity(entity);
            persons.add(personBean);
        }
        return persons;
    }

    @Transactional
    public void addPerson(PersonBean p)
    {

        this.persist(p.getEntity());
    }

}
