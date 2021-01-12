package rmunteanu.javaInterfaces;

import rmunteanu.javaBeans.ServiceUploadBean;
import rmunteanu.javaEntities.UploadEntity;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

public interface InterfaceUpload {
    public boolean addDocumentUpload(UploadEntity m);
    public List<ServiceUploadBean> getDocumentsUploads();
    public UploadEntity uploadCreate(InputStream is);
    public UploadEntity uploadFind(BigDecimal id);
    public void uploadDelete(UploadEntity upload);
    public void uploadUpdate(UploadEntity uploadEntity);
    public List<UploadEntity> getUploadsDocumentsEntities();

}
