/*
 * Copyright (c) 2020.
 */

package rmunteanu.custom_jndi;

import javax.inject.Qualifier;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Configuration
@Qualifier("mongoTemplate")
public class MongoTemplate  {


    public MongoTemplate() throws NamingException {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        return (MongoTemplate) envCtx.lookup("bean/MyMongoBean");

    }
}
