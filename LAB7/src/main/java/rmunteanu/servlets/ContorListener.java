/*
 * Copyright (c) 2020.
 */

package rmunteanu.servlets;

import javax.servlet.http.*;
import rmunteanu.model.*;
/** Listener that keeps track of the number of sessions
 * that the Web application is currently using.
 */

public class ContorListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent event) {
        contorBean.getInstanceSingleton().incrementContor();
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        contorBean.getInstanceSingleton().decrementContor();
    }

}