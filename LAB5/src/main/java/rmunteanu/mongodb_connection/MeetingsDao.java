/*
 * Copyright (c) 2020.
 */

package rmunteanu.mongodb_connection;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import rmunteanu.mongodb_connection.MeetingsMongoDO;

public class MeetingsDao extends BasicDAO<MeetingsMongoDO, ObjectId> {

    public MeetingsDao(Datastore ds) {
        super(ds);
    }

}