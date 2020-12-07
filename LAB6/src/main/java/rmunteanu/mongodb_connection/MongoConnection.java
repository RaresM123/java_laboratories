/*
 * Copyright (c) 2020.
 */

package rmunteanu.mongodb_connection;


import static java.lang.String.format;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import rmunteanu.mongodb_connection.BaseMongoDO;

public class MongoConnection {

    private static MongoConnection instance = new MongoConnection();

    private MongoClient mongo = null;
    private Datastore meetingDB = null;
    private Datastore personsDB = null;
    private Datastore locationDB = null;

    private Morphia morphia = null;

    private MongoConnection() {}

    public MongoClient getMongo() throws RuntimeException {
        if (mongo == null) {
            MongoClientOptions.Builder options = MongoClientOptions.builder()
                    .connectionsPerHost(4)
                    .maxConnectionIdleTime((60 * 1_000))
                    .maxConnectionLifeTime((120 * 1_000));
            ;

            MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017/tema4", options);

            try {
                mongo = new MongoClient(uri);
                mongo.setWriteConcern(WriteConcern.ACKNOWLEDGED);
            } catch (Exception ex) {
                System.out.println("Mongo Exception");
            }

            mongo.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        }

        return mongo;
    }

    public Morphia getMorphia() {
        if (morphia == null) {
            morphia = new Morphia();

            morphia.mapPackageFromClass(BaseMongoDO.class);
        }

        return morphia;
    }

    public Datastore getMeetingDB() {
        if (meetingDB == null) {
            String dbName = "Meetings";
            meetingDB = getMorphia().createDatastore(getMongo(), dbName);
        }

        return meetingDB;
    }

    public Datastore getPersonsDB() {
        if (personsDB == null) {
            String dbName = "Persons";
            personsDB = getMorphia().createDatastore(getMongo(), dbName);
        }

        return personsDB;
    }


    public Datastore getLocationDB() {
        if (locationDB == null) {
            String dbName = "Locations";
            locationDB = getMorphia().createDatastore(getMongo(), dbName);
        }

        return locationDB;
    }

    public void init() {
        getMongo();
        getMorphia();
        getMeetingDB();
        getPersonsDB();
        getLocationDB();

    }

    public void close() {
        if (mongo != null) {
            try {
                mongo.close();
                mongo = null;
                morphia = null;
                meetingDB = null;
                personsDB = null;
                locationDB = null;

            } catch (Exception e) {
                System.out.println("Mongo Exception");
            }
        } else {
            System.out.println("mongo object was null, wouldn't close connection");
        }
    }

    public static MongoConnection getInstance() {
        return instance;
    }
}