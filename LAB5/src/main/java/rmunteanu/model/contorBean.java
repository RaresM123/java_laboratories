/*
 * Copyright (c) 2020.
 */

package rmunteanu.model;

public class contorBean {
    private int number = 1; // current session
    private static contorBean InstanceOfSingleton = null;

    public synchronized void incrementContor(){
        number+=1;
    }

    public synchronized void decrementContor(){
        number-=1;
    }

    public int getNumberOfSessions() {
        return number;
    }


    public static contorBean getInstanceSingleton(){
        if(InstanceOfSingleton == null){
            InstanceOfSingleton = new contorBean();
        }
        return InstanceOfSingleton;
    }
}