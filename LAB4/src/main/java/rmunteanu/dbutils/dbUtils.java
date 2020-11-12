package rmunteanu.dbutils;

import com.mongodb.*;
import rmunteanu.model.MeetingBean;
import rmunteanu.model.PersonBean;

import javax.servlet.Servlet;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class dbUtils {

    public static MongoClient mongoClient;

    static {
        try {
            mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static DB database = mongoClient.getDB("tema4");
    public static DBCollection personsCollection= database.getCollection("Persons");
    public static DBCollection meetingsCollection = database.getCollection("Meetings");

    public static void insertPerson(PersonBean person){

        DBObject personObject = new BasicDBObject("id", person.getId()).append("name", person.getName());
        personsCollection.insert(personObject);
    }

    public static void insertMeeting(MeetingBean meeting){

        List<Integer>personsId = new ArrayList<>();
        for(PersonBean p: meeting.getPersonsSelected())
        {
            personsId.add(p.getId());
        }
        DBObject meetingObject = new BasicDBObject("id", meeting.getId()).append("topic", meeting.getTopic())
                .append("duration", meeting.getDuration()).append("StartingTime", meeting.getStartingTime())
                .append("personsId", personsId);

        meetingsCollection.insert(meetingObject);
    }

    public static List<PersonBean> retrievePersons() {
        DBCursor cursor = personsCollection.find();
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
}
