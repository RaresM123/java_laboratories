package rmunteanu.javaServices;

import rmunteanu.javaBeans.ServiceUploadBean;
import rmunteanu.javaEntities.UploadEntity;
import rmunteanu.javaInterfaces.InterfaceUpload;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ServiceUpload extends DataRepositoryManager<UploadEntity,Integer> implements InterfaceUpload {
    public ServiceUpload(){super(UploadEntity.class);}

    @Override
    public List<ServiceUploadBean> getDocumentsUploads(){
        List<UploadEntity> entities = this.findAll();

        List<ServiceUploadBean> listOfUploads = new ArrayList<>();
        for (UploadEntity entity : entities)
        {
            ServiceUploadBean serviceUploadBean = new ServiceUploadBean();
            serviceUploadBean.setEntity(entity);

            listOfUploads.add(serviceUploadBean);
        }
        return listOfUploads;
    }

    @Override
    public boolean addDocumentUpload(UploadEntity m){
        try{
            this.persist(m);}
        catch (Exception e){
            return false;
        }
        return true;
    }
}
