package rmunteanu.javaBeans;

import rmunteanu.javaEntities.UserEntity;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean(name="serviceRegisterBean")
@SessionScoped
public class ServiceRegisterBean implements Serializable {
    public void addUsr(UserBean userBean){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        UserEntity user = userBean.getServiceUser().findRegisteredUser(userBean.getUserEntity().getUsr());
        try{
            if(user==null){

                userBean.getServiceUser().addNewUser(userBean);
                user = userBean.getServiceUser().findRegisteredUser(userBean.getUserEntity().getUsr());
                if(user==null){
                    throw new Exception("Cannot insert new user");
                }
                externalContext.getSessionMap().put("user", user);
                if(user.getType().equals("guest"))
                    externalContext.redirect(externalContext.getRequestContextPath() + "/simpleUserUpload.xhtml");
                else
                    externalContext.redirect(externalContext.getRequestContextPath() + "/adminUpload.xhtml");
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
}
