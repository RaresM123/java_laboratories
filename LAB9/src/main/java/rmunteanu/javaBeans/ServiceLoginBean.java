package rmunteanu.javaBeans;

import rmunteanu.javaEntities.UserEntity;
import rmunteanu.javaInterfaces.InterfaceUser;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;

@ManagedBean(name="serviceLoginBean")
@SessionScoped
public class ServiceLoginBean implements Serializable {

    private String usr;
    private String pwd;

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Inject
    private InterfaceUser serviceUser;

    public InterfaceUser getServiceUser() {
        return serviceUser;
    }

    public void setServiceUser(InterfaceUser serviceUser) {
        this.serviceUser = serviceUser;
    }


    public void validateUser() throws IOException {

        UserEntity user = serviceUser.findRegisteredUser(getUsr(),getPwd());
        if(user!=null){
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
            if(user.getType().equals("guest"))
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/simpleUserUpload.xhtml");
            else
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/adminUpload.xhtml");
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Unknown type of login"));
        }
    }

}
