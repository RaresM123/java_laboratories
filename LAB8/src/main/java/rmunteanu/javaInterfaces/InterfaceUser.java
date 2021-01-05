package rmunteanu.javaInterfaces;
import rmunteanu.javaBeans.UserBean;
import rmunteanu.javaEntities.UserEntity;

import java.util.List;
public interface InterfaceUser {

    public void addNewUser(UserBean u);

    public List<UserBean> getAllUsers();

    public UserEntity findRegisteredUser(String usr, String pwd);

    public UserEntity findRegisteredUser(String usr);
}
