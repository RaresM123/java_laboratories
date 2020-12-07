/*
 * Copyright (c) 2020.
 */

package rmunteanu.mongodb_connection;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Version;

public class BaseMongoDO {

    @Id
    @Property("id")
    private ObjectId _id;

    public BaseMongoDO() {
        super();
    }

    public ObjectId get_Id() {
        return _id;
    }

    public void set_Id(ObjectId id) {
        this._id = id;
    }

}