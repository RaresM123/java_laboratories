package rmunteanu.javaInterfaces;

import rmunteanu.javaBeans.ServiceUploadBean;
import rmunteanu.javaEntities.UploadEntity;
import java.util.List;

public interface InterfaceUpload {
    public boolean addDocumentUpload(UploadEntity m);
    public List<ServiceUploadBean> getDocumentsUploads();
}
