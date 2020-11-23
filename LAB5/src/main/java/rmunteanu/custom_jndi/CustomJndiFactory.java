/*
 * Copyright (c) 2020.
 */

package rmunteanu.custom_jndi;

import com.mongodb.Mongo;

import javax.naming.*;
import javax.naming.spi.ObjectFactory;
import java.util.Enumeration;
import java.util.Hashtable;

public class CustomJndiFactory implements ObjectFactory {

    public Object getObjectInstance(Object obj, Name name, Context nameCtx,
                                    Hashtable<?, ?> environment) throws Exception {

        validateProperty(obj, "Invalid JNDI object reference");

        MongoTemplate mongoTemplate = null;
        String db = null;
        String host = null;
        String username = null;
        String password = null;
        int port = 27017;

        Reference ref = (Reference) obj;
        Enumeration<RefAddr> props = ref.getAll();
        while (props.hasMoreElements()) {
            RefAddr addr = (RefAddr) props.nextElement();
            String propName = addr.getType();
            String propValue = (String) addr.getContent();
            if (propName.equals("db")) {
                db = propValue;
            } else if (propName.equals("host")) {
                host = propValue;
            } else if (propName.equals("username")) {
                username = propValue;
            } else if (propName.equals("password")) {
                password = propValue;
            } else if (name.equals("port")) {
                try {
                    port = Integer.parseInt(propValue);
                } catch (NumberFormatException e) {
                    throw new NamingException("Invalid port value " + propValue);
                }
            }

        }

        // validate properties
        validateProperty(db, "Invalid or empty mongo database name");
        validateProperty(host, "Invalid or empty mongo host");
        validateProperty(username, "Invalid or empty mongo username");
        validateProperty(password, "Invalid or empty mongo password");

        //create mongo template
        mongoTemplate = new MongoTemplate(new Mongo(host, port), db,
                new UserCredentials(username, password));

        return mongoTemplate;
    }


    /**
     * Validate internal String properties
     *
     * @param property
     * @param errorMessage
     * @throws NamingException
     */
    private void validateProperty(String property, String errorMessage)
            throws NamingException {
        if (property == null || property.trim().equals("")) {
            throw new NamingException(errorMessage);
        }
    }

    /**
     * Validate internal Object properties
     *
     * @param property
     * @param errorMessage
     * @throws NamingException
     */
    private void validateProperty(Object property, String errorMessage)
            throws NamingException {
        if (property == null) {
            throw new NamingException(errorMessage);
        }
    }