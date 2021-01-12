package rmunteanu.javaServices;

import rmunteanu.javaBeans.ServiceUploadBean;
import rmunteanu.javaEntities.UploadEntity;
import rmunteanu.javaInterfaces.InterfaceUpload;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.InputStream;
import java.math.BigDecimal;
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
    public UploadEntity uploadCreate(InputStream is) {
        return this.createEntity(is);
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UploadEntity uploadFind(BigDecimal id) {

        String qlString =
                "select e from " + entityClass.getSimpleName() + " e where e.id = "+id.toString();
        List<UploadEntity> listOfUsers = (List<UploadEntity>)entityManager
                .createQuery(qlString).getResultList();
        if(listOfUsers.size()==0){
            return null;
        }
        return listOfUsers.get(0);
    }

    @Override
    public void uploadDelete(UploadEntity upload) {
        remove(upload);
    }

    @Override
    public void uploadUpdate(UploadEntity uploadEntity) {
        this.update(uploadEntity);
    }

    @Override
    public List<UploadEntity> getUploadsDocumentsEntities() {
        return this.findAll();
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
