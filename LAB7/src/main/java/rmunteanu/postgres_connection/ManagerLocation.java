/*
 * Copyright (c) 2020.
 */

package rmunteanu.postgres_connection;

import rmunteanu.entities_model.LocationEntity;
import rmunteanu.model.LocationBean;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ManagerLocation extends RepositoryDataManager<LocationEntity,Integer>{
    public ManagerLocation(){
        super(LocationEntity.class);
    }

    public List<LocationBean> getLocations()
    {
        List<LocationEntity> entities = this.findAll();

        List<LocationBean> locations = new ArrayList<>();
        for (LocationEntity entity : entities)
        {
            LocationBean locationBean = new LocationBean();
            locationBean.setEntity(entity);
            locations.add(locationBean);
        }
        return locations;
    }

    public void addLocation(LocationBean p)
    {
        this.persist(p.getEntity());
    }
}
