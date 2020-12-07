/*
 * Copyright (c) 2020.
 */

package rmunteanu.mongodb_connection;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import rmunteanu.mongodb_connection.PersonsMongoDO;

public class PersonsDao extends BasicDAO<PersonsMongoDO, ObjectId> {

    public PersonsDao(Datastore ds) {
        super(ds);
    }

}