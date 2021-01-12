package rmunteanu.javaBeans;

import rmunteanu.javaAuthConfig.EncodingAlgorithm;
import rmunteanu.javaEntities.UserEntity;
import rmunteanu.javaInterfaces.InterfaceUser;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

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


    public void validateUser() throws IOException, NoSuchAlgorithmException {

        UserEntity user = serviceUser.findRegisteredUser(getUsr(), EncodingAlgorithm.encodeMD5(getPwd()));

        HttpServletRequest serviceRequest = (HttpServletRequest)
                FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            serviceRequest.login(getUsr(), EncodingAlgorithm.encodeMD5(getPwd()));
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("Login Failed, try again!",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", null));
            return;
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);

        if(user!=null){
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
            if (serviceRequest.isUserInRole("guest")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect(
                        FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/guest_directory/simpleUserUpload.xhtml");
            }
            else{
                FacesContext.getCurrentInstance().getExternalContext().redirect
                        (FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/admin_directory/adminUpload.xhtml");
            }
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Unknown type of login"));
        }
    }

}
