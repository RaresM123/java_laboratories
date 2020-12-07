/*
 * Copyright (c) 2020.
 */

package rmunteanu.entities_model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("conference_discriminator")
public class ConferenceEntity extends MeetingEntity implements Serializable {

    String personToSpeak;

    public String getPersonToSpeak() {
        return personToSpeak;
    }

    public void setPersonToSpeak(String personToSpeak) {
        this.personToSpeak = personToSpeak;
    }

}