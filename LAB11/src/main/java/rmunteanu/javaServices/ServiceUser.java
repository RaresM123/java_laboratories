package rmunteanu.javaServices;

import rmunteanu.javaBeans.UserBean;
import rmunteanu.javaEntities.UserEntity;
import rmunteanu.javaInterfaces.InterfaceUser;
import javax.annotation.security.PermitAll;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Stateless
@PermitAll
public class ServiceUser extends DataRepositoryManager<UserEntity,Integer> implements InterfaceUser {
    @PersistenceContext
    private EntityManager em;

    public ServiceUser(){super(UserEntity.class);}

    @Override
    public List<UserBean> getAllUsers()
    {
        List<UserEntity> entities = this.findAll();

        List<UserBean> listOfUsers = new ArrayList<>();
        for (UserEntity entity : entities)
        {
            UserBean userBean = new UserBean();
            userBean.setUserEntity(entity);
            listOfUsers.add(userBean);
        }
        return listOfUsers;
    }

    @Override
    public UserEntity findRegisteredUser(String username) {
        String newQueryString =
                "select e from " +
                        entityClass.getSimpleName() +
                        " e where e.username = '"+username+"'";
        List<UserEntity> returnUserList = (List<UserEntity>)em.createQuery(newQueryString).getResultList();
        if(returnUserList.size()<=0){
            return null;
        }
        return returnUserList.get(0);
    }

    @Override
    public UserEntity userCreate(InputStream is) {
        return this.createEntity(is);
    }

    @Override
    public UserEntity findRegisteredUser(String username, String password) {
        String newQueryString =
                        "select e from " +
                        entityClass.getSimpleName() +
                                " e where e.username = '"+username+
                                "' and e.password = '"+password+"'";
        List<UserEntity> returnUserlist = (List<UserEntity>)em.createQuery(newQueryString).getResultList();
        if(returnUserlist.size()<= 0){
            return null;
        }
        return returnUserlist.get(0);
    }

    @Override
    public void addNewUser(UserBean u)
    {
        if(u.getUserEntity().getUsr().equals("admin_user") && u.getUserEntity().getPwd().equals("admin_password")){
            u.getUserEntity().setType("admin");
        }
        else{
            u.getUserEntity().setType("guest");
        }

        this.persist(u.getUserEntity());
    }

}
