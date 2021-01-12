package rmunteanu.javaBeans;

import rmunteanu.javaEntities.UserEntity;
import rmunteanu.javaInterfaces.InterfaceUser;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.xml.registry.infomodel.Organization;
import java.io.Serializable;

@ManagedBean(name="userBean")
@SessionScoped
public class UserBean implements Serializable {
    @Inject
    private InterfaceUser serviceUser;

    private UserEntity userEntity = new UserEntity();

    public InterfaceUser getServiceUser() {
        return serviceUser;
    }

    public void setServiceUser(InterfaceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

}
