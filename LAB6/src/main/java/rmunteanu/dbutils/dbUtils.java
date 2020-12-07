package rmunteanu.dbutils;

import com.mongodb.*;
import rmunteanu.entities_model.LocationEntity;
import rmunteanu.entities_model.MeetingEntity;
import rmunteanu.entities_model.PersonEntity;
import rmunteanu.model.LocationBean;
import rmunteanu.model.MeetingBean;
import rmunteanu.model.PersonBean;
import rmunteanu.mongodb_connection.MongoConnection;
import rmunteanu.mongodb_connection.*;

import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.servlet.Servlet;
import java.net.UnknownHostException;
import java.sql.SQLException;
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

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab6_persistence");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(person.getEntity());
        em.getTransaction().commit();

        em.close();
        factory.close();
    }

    public static void insertMeeting(MeetingBean meeting){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab6_persistence");
        EntityManager em = factory.createEntityManager();

        MeetingEntity meetingEntity = meeting.getEntity();

        List<PersonEntity> persons = new ArrayList<>();
        for (PersonBean personBean : meeting.getPersonsSelected()){
            persons.add(personBean.getEntity());
        }

        meetingEntity.setEntityPersons(persons);

        LocationEntity location = meeting.getLocationSelected().getEntity();
        meetingEntity.setEntityLocation(location);

        em.getTransaction().begin();
        em.persist(meeting);
        em.getTransaction().commit();

        em.close();
        factory.close();
    }

    public static List<PersonBean> retrievePersons() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lab6_persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String queryString = "SELECT p FROM PersonEntity p";
        Query query = entityManager.createQuery(queryString);
        List<PersonEntity> entities = query.getResultList();

        List<PersonBean> PersonBeanList = new ArrayList<>();
        for (PersonEntity entity : entities)
        {
            PersonBean personBean = new PersonBean();
            personBean.setEntity(entity);
            PersonBeanList.add(personBean);
        }

        entityManager.close();
        entityManagerFactory.close();
        return PersonBeanList;
    }

    public static void insertLocation(LocationBean location) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab6_persistence");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(location.getEntity());
        em.getTransaction().commit();

        em.close();
        factory.close();
    }

    public static List<LocationBean> retrieveLocations() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lab6_persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String queryString = "SELECT l FROM LocationEntity l";
        Query query = entityManager.createQuery(queryString);
        List<LocationEntity> entities = query.getResultList();

        List<LocationBean> LocationBeanList = new ArrayList<>();
        for (LocationEntity entity : entities)
        {
            LocationBean locationBean = new LocationBean();
            locationBean.setEntity(entity);
            LocationBeanList.add(locationBean);
        }

        entityManager.close();
        entityManagerFactory.close();
        return LocationBeanList;
    }

    public static List<MeetingBean> retrieveMeetings() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lab6_persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String queryString = "SELECT p FROM MeetingEntity p order by p.id";
        Query query = entityManager.createQuery(queryString);
        List<MeetingEntity> entities = query.getResultList();

        List<MeetingBean> MeetingBeanList = new ArrayList<>();
        for (MeetingEntity entityM : entities)
        {
            MeetingBean meetingBean = new MeetingBean();
            meetingBean.setEntity(entityM);

            LocationBean locationBean = new LocationBean();
            locationBean.setEntity(entityM.getEntityLocation());
            meetingBean.setLocationSelected(locationBean);

            List<PersonBean> persons = new ArrayList<>();
            for(PersonEntity p : entityM.getEntityPersons()){
                PersonBean personBean = new PersonBean();
                personBean.setEntity(p);
                persons.add(personBean);
            }
            meetingBean.setPersonsSelected(persons);
            MeetingBeanList.add(meetingBean);
        }

        entityManager.close();
        entityManagerFactory.close();
        return MeetingBeanList;
    }

    public static Predicate getPredicate(String topic, String personName, String locationName,
                                         CriteriaBuilder builder, Root<MeetingEntity> e,
                                         ListJoin<MeetingEntity, PersonEntity> person){
        Predicate condition = null;

        if (locationName != null)
        {
            if (condition != null)
                condition = builder.and(
                        condition,
                        builder.like(e.get(MeetingEntity_.location).get(LocationEntity_.name), "%" + locationName + "%")
                );
            else
                condition = builder.like(e.get(MeetingEntity_.location).get(LocationEntity_.name), "%" + locationName + "%");
        }

        if (topic != null)
        {
            condition = builder.equal(e.get(MeetingEntity_.topic), topic);
        }

        if (personName != null)
        {

            if (condition != null)
                condition = builder.and(
                        condition,
                        builder.like(person.get(PersonEntity_.name), "%"+personName+"%")
                );
            else
                condition = builder.like(person.get(PersonEntity_.name), "%"+personName+"%");
        }


        return condition;
    }

    public static List<MeetingBean> searchMeetingsAlgorithm(String topic, String personName, String locationName) throws SQLException
    {
        List<MeetingBean> result = new ArrayList<>();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab6_persistence");
        EntityManager em = factory.createEntityManager();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<MeetingEntity> query = builder.createQuery(MeetingEntity.class);

        Root<MeetingEntity> e = query.from(MeetingEntity.class);
        ListJoin<MeetingEntity, PersonEntity> person = e.join(MeetingEntity_.persons);

        Predicate condition = getPredicate(topic, personName, locationName, builder, e, person);

        query.where(condition).distinct(true).orderBy(builder.asc(e.get("id")));

        TypedQuery<MeetingEntity> q = em.createQuery(query);
        List<MeetingEntity> entities = q.getResultList();

        for (MeetingEntity entity : entities)
        {
            MeetingBean meetingBean = new MeetingBean();
            meetingBean.setEntity(entity);

            LocationBean locationBean = new LocationBean();
            locationBean.setEntity(entity.getEntityLocation());
            meetingBean.setLocationSelected(locationBean);

            List<PersonBean> persons = new ArrayList<>();
            for(PersonEntity p : entity.getEntityPersons()){
                PersonBean personBean = new PersonBean();
                personBean.setEntity(p);
                persons.add(personBean);
            }
            meetingBean.setPersonsSelected(persons);
            result.add(meetingBean);
        }

        em.close();
        factory.close();

        return result;
    }
}
