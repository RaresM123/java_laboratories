package rmunteanu.javaBeans;

import rmunteanu.javaEntities.UploadEntity;
import rmunteanu.javaInterceptor.LoggerClass;
import rmunteanu.javaInterfaces.InterfaceUpload;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import static javax.faces.annotation.FacesConfig.Version.JSF_2_3;

@Named("cacheBean")
@ApplicationScoped
@FacesConfig(
        // Activates CDI build-in beans
        version = JSF_2_3
)
public class ServiceUploadsCacheBean {

    @Inject
    protected InterfaceUpload serviceUpload;
    protected List<ServiceUploadBean> documentsUploadList;

    public List<ServiceUploadBean> getDocumentsUploadList(){
        if (documentsUploadList == null){
            documentsUploadList = serviceUpload.getDocumentsUploads();
        }
        return documentsUploadList;
    }
    @LoggerClass
    public void onUploadsUpdate(@Observes UploadEntity uploadEntity){
        documentsUploadList = null;
    }
}
