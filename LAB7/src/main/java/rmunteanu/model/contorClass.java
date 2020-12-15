/*
 * Copyright (c) 2020.
 */

package rmunteanu.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import java.io.Serializable;

@ManagedBean(name = "contorBean")
@ApplicationScoped

public class contorClass implements Serializable {
    int number;

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberOfSessions() {
        return contorBean.getInstanceSingleton().getNumberOfSessions();
    }
}