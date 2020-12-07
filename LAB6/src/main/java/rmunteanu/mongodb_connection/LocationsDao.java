/*
 * Copyright (c) 2020.
 */

package rmunteanu.mongodb_connection;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import rmunteanu.mongodb_connection.LocationsMongoDO;

public class LocationsDao extends BasicDAO<LocationsMongoDO, ObjectId> {

    public LocationsDao(Datastore ds) {
        super(ds);
    }

}