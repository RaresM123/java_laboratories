package rmunteanu.javaServices;

import rmunteanu.javaEntities.GroupEntity;

import rmunteanu.javaInterfaces.InterfaceGroup;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@PermitAll
public class ServiceGroup extends DataRepositoryManager<GroupEntity,Integer> implements InterfaceGroup {

    @PersistenceContext
    private EntityManager entityManager;

    public ServiceGroup() {
        super(GroupEntity.class);
    }

    @Override
    public GroupEntity GroupDiscovery(String name) {
        String queryString =
                "select e from " + entityClass.getSimpleName() + " e where e.name = '" + name + "'";
        List<GroupEntity> listOfUsers = (List<GroupEntity>) entityManager.createQuery(queryString).getResultList();
        if (listOfUsers.size() == 0) {
            return null;
        }
        return listOfUsers.get(0);
    }
}

