/*
 * Copyright (c) 2020.
 */

package rmunteanu.entities_model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("workshop_discriminator")
public class WorkshopEntity extends MeetingEntity implements Serializable {

    String resourcesWorkshop;

    public String getResourcesWorkshop() {
        return resourcesWorkshop;
    }

    public void setResourcesWorkshop(String resourcesWorkshop) {
        this.resourcesWorkshop = resourcesWorkshop;
    }
}