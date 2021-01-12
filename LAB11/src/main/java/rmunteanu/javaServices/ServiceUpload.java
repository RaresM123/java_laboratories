package rmunteanu.javaServices;

import rmunteanu.javaBeans.ServiceUploadBean;
import rmunteanu.javaEntities.UploadEntity;
import rmunteanu.javaInterfaces.InterfaceUpload;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Stateless
public class ServiceUpload extends DataRepositoryManager<UploadEntity,Integer> implements InterfaceUpload {
    public ServiceUpload() {
        super(UploadEntity.class);
    }

    public Set<ConstraintViolation<UploadEntity>> validateUpload(UploadEntity uploadEntity) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<UploadEntity>> constraintViolations = validator.validate(uploadEntity);
        if (constraintViolations.size() > 0) {
            Iterator<ConstraintViolation<UploadEntity>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<UploadEntity> cv = iterator.next();
                System.err.println(cv.getRootBeanClass().getName() + "." + cv.getPropertyPath() + " " + cv.getMessage());

                System.out.println(cv.getRootBeanClass().getSimpleName() + "." + cv.getPropertyPath() + " " + cv.getMessage());
            }

        }
        return constraintViolations;
    }

    @Override
    @RolesAllowed("admin")
    public List<ServiceUploadBean> getDocumentsUploads() {
        List<UploadEntity> entities = this.findAll();

        List<ServiceUploadBean> listOfUploads = new ArrayList<>();
        for (UploadEntity entity : entities) {
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
    @RolesAllowed("admin")
    public UploadEntity uploadFind(BigDecimal id) {

        String qlString =
                "select e from " + entityClass.getSimpleName() + " e where e.id = " + id.toString();
        List<UploadEntity> listOfUsers = (List<UploadEntity>) entityManager
                .createQuery(qlString).getResultList();
        if (listOfUsers.size() == 0) {
            return null;
        }
        return listOfUsers.get(0);
    }

    @Override
    @RolesAllowed("admin")
    public void uploadDelete(UploadEntity upload) {
        remove(upload);
    }

    @Override
    @RolesAllowed({"admin", "guest"})
    public void uploadUpdate(UploadEntity uploadEntity) {
        this.update(uploadEntity);
    }

    @Override
    @RolesAllowed("admin")
    public List<UploadEntity> getUploadsDocumentsEntities() {
        return this.findAll();
    }

    @Override
    @RolesAllowed("guest")
    public boolean addDocumentUpload(UploadEntity m) {

        Set<ConstraintViolation<UploadEntity>> constraintViolations = validateUpload(m);
        if (constraintViolations.size() <= 0) {
            try {
                this.persist(m);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
