/*
 * Copyright (c) 2020.
 */

package rmunteanu.entities_model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,name = "meeting_type_discriminator")
@DiscriminatorValue("simple_value")
public class MeetingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigDecimal id;
    @NotNull
    private String topic;

    private Integer entityCapacity;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date starting_time;
    private BigDecimal duration = BigDecimal.valueOf(0);

    @JoinTable(name = "person_meeting",
            joinColumns = {
                    @JoinColumn(name = "id_meeting",
                            referencedColumnName = "id", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_person",
                            referencedColumnName = "id", nullable = false)})
    @ManyToMany
    private List<PersonEntity> persons;

    @ManyToOne
    @JoinColumn(name = "id_location", referencedColumnName = "id", nullable = false)
    private LocationEntity locationEntity;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getEntityCapacity() {
        return entityCapacity;
    }

    public void setEntityCapacity(Integer capacity) {
        this.entityCapacity = capacity;
    }

    public Date getStarting_time() {
        return starting_time;
    }

    public void setStarting_time(Date starting_time) {
        this.starting_time = starting_time;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public List<PersonEntity> getEntityPersons() {
        return persons;
    }

    public void setEntityPersons(List<PersonEntity> selectedPersons) {
        this.persons = selectedPersons;

    }

    public LocationEntity getEntityLocation() {
        return locationEntity;
    }

    public void setEntityLocation(LocationEntity selectedLocation) {
        this.locationEntity = selectedLocation;
    }

}