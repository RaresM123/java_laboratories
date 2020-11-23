package rmunteanu.dbutils;

import com.mongodb.*;
import rmunteanu.model.LocationBean;
import rmunteanu.model.MeetingBean;
import rmunteanu.model.PersonBean;
import rmunteanu.mongodb_connection.MongoConnection;
import rmunteanu.mongodb_connection.*;
import javax.servlet.Servlet;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import com.mongodb.WriteResult;

public class dbUtils {

    public static MongoClient mongoClient;
    static public LocationsDao ldao = null;
    static public MeetingsDao mdao = null;
    static public PersonsDao pdao = null;

    static {
        try {
            MongoConnection conn = MongoConnection.getInstance();
            conn.init();
            pdao = new PersonsDao(conn.getPersonsDB());
            mdao = new MeetingsDao(conn.getMeetingDB());
            ldao = new LocationsDao(conn.getLocationDB());

//            mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void insertPerson(PersonBean person){

        DBObject personObject = new BasicDBObject("id", person.getId()).append("name", person.getName());

        WriteResult wResult = pdao.getCollection().insert(personObject);
//        personsCollection.insert(personObject);
    }

    public static void insertMeeting(MeetingBean meeting){

        List<Integer>personsId = new ArrayList<>();
        Integer locationId = 0;

        for(PersonBean p: meeting.getPersonsSelected())
        {
            personsId.add(p.getId());
        }

        locationId = meeting.getLocationSelected().getId();

        DBObject meetingObject = new BasicDBObject("id", meeting.getId()).append("topic", meeting.getTopic())
                .append("duration", meeting.getDuration()).append("StartingTime", meeting.getStartingTime())
                .append("personsId", personsId).append("locationId", locationId);

        WriteResult wResult = mdao.getCollection().insert(meetingObject);

//        meetingsCollection.insert(meetingObject);
    }

    public static List<PersonBean> retrievePersons() {

        DBCursor cursor = pdao.getCollection().find();
        List<PersonBean> persons = new ArrayList<>();
        try {
            while(cursor.hasNext()) {
                DBObject o = cursor.next();
                PersonBean person = new PersonBean();
                person.setId((Integer) o.get("id"));
                person.setName((String) o.get("name"));

                persons.add(person);
            }
        } catch (MongoException x) {
            x.printStackTrace();
        }
        return persons;
    }

    public static void insertLocation(LocationBean location) {
        DBObject locationObject = new BasicDBObject("id", location.getId())
                .append("name", location.getName());

        ldao.getCollection().insert(locationObject);

    }

    public static List<LocationBean> retrieveLocations() {

        DBCursor cursor = ldao.getCollection().find();;
        List<LocationBean> locations = new ArrayList<>();
        try {
            while(cursor.hasNext()) {
                DBObject o = cursor.next();
                LocationBean location = new LocationBean();
                location.setId((Integer) o.get("id"));
                location.setName((String) o.get("name"));

                locations.add(location);
            }
        } catch (MongoException x) {
            x.printStackTrace();
        }
        return locations;
    }
}
