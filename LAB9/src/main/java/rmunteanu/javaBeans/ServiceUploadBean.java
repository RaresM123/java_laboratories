package rmunteanu.javaBeans;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import rmunteanu.javaEntities.UploadEntity;
import rmunteanu.javaEntities.UserEntity;
import rmunteanu.javaInterfaces.InterfaceUpload;
import rmunteanu.javaUtils.UniqueAdnotation;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean(name="serviceUploadBean")
@SessionScoped
public class ServiceUploadBean implements Serializable {

    @Inject
    private InterfaceUpload serviceUpload;

    UploadedFile uploadedFile;

    UploadEntity entity = new UploadEntity();
    @Inject
    @UniqueAdnotation.Unique
    Instance<String> createdIdImpl;

    public InterfaceUpload getServiceUpload() {
        return serviceUpload;
    }

    public void setServiceUpload(InterfaceUpload serviceUpload) {
        this.serviceUpload = serviceUpload;
    }

    public UploadEntity getEntity() {
        return entity;
    }

    public void setEntity(UploadEntity entity) {
        this.entity = entity;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public void manageUploadFile(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void uploadNewFile(){
        getEntity().setRandomGeneratedId(createdIdImpl.get());
        getEntity().setFileName(uploadedFile.getFileName());
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        UserEntity currentUser = (UserEntity) externalContext.getSessionMap().get("user");
        getEntity().setUsr(currentUser);
        serviceUpload.addDocumentUpload(getEntity());
    }
}
