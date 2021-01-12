package rmunteanu.javaEntities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class GroupEntity implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private BigDecimal id;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    @ManyToMany(mappedBy = "groups")
    private List<UserEntity> serviceUsers;

    public List<UserEntity> getServiceUsers() {
        return serviceUsers;
    }

    public void setServiceUsers(List<UserEntity> serviceUsers) {
        this.serviceUsers = serviceUsers;
    }

    @NotNull(message = "Name of the group can't be None")
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
