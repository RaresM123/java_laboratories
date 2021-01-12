package rmunteanu.javaBeans;

import rmunteanu.javaAuthConfig.EncodingAlgorithm;
import rmunteanu.javaEntities.GroupEntity;
import rmunteanu.javaEntities.UserEntity;
import rmunteanu.javaInterfaces.InterfaceGroup;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@ManagedBean(name="serviceRegisterBean")
@SessionScoped
public class ServiceRegisterBean implements Serializable {

    public UserEntity resolveGroupSituation(UserBean userBean, UserEntity user) throws NoSuchAlgorithmException {
        ArrayList<GroupEntity> groupEntities = new ArrayList<>();
        GroupEntity groupEntity;
        if(userBean.getUserEntity().getUsr().equals("admin")
                && userBean.getUserEntity().getPwd().equals("admin")){
            groupEntity = serviceGroup.GroupDiscovery("admin");
        }
        else{
            groupEntity = serviceGroup.GroupDiscovery("guest");
        }
        groupEntities.add(groupEntity);
        userBean.getUserEntity().setUsersGroup(groupEntities);
        String newPwd = EncodingAlgorithm.encodeMD5(userBean.getUserEntity().getPwd());
        userBean.getUserEntity().setPwd(newPwd);
        userBean.getServiceUser().addNewUser(userBean);
        user = userBean.getServiceUser().findRegisteredUser(userBean.getUserEntity().getUsr());
        return user;
    }
    public void addUsr(UserBean userBean){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        UserEntity user = userBean.getServiceUser().findRegisteredUser(userBean.getUserEntity().getUsr());
        try{
            if(user==null){

                user = resolveGroupSituation(userBean, user);
                if(user==null){
                    throw new Exception("Cannot insert new user");
                }
                externalContext.getSessionMap().put("user", user);
                context.addMessage(null, new FacesMessage("Registration succeeded."));
            }
            else{
                context.addMessage(null, new FacesMessage("These username already exists"));
            }
        }
        catch(Exception e){
            context.addMessage(null, new FacesMessage("The registration has failed"));
        }
    }
    @Inject
    private InterfaceGroup serviceGroup;

    public InterfaceGroup getServiceGroup() {
        return serviceGroup;
    }

    public void setServiceGroup(InterfaceGroup serviceGroup) {
        this.serviceGroup = serviceGroup;
    }

}
