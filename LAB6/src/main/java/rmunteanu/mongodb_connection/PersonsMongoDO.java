/*
 * Copyright (c) 2020.
 */

package rmunteanu.mongodb_connection;

public class PersonsMongoDO extends BaseMongoDO{

    private String name;
    private Integer id;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
