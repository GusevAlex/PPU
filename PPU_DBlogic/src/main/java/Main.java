import com.PPU.DB.security.MD5;
import com.PPU.DB.tables.*;
import com.PPU.DB.workLogic.WorkWithMZ;
import com.PPU.DB.workLogic.WorkWithUser;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 12.04.14
 * Time: 23:55
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {

        WorkWithUser workWithUser = new WorkWithUser();

        UsersMunMan usersMunMan = new UsersMunMan();
        usersMunMan.setLogin("alex");

        boolean bol = workWithUser.checkLogin(usersMunMan);

        usersMunMan.setLogin("alex2");

        bol = workWithUser.checkLogin(usersMunMan);

        UsersComMan usersComMan = new UsersComMan();
        usersComMan.setLogin("alex");

        bol = workWithUser.checkLogin(usersComMan);

        usersComMan.setLogin("alex3");

        bol = workWithUser.checkLogin(usersComMan);



        List list = workWithUser.findAndGetAllRow("login", "alex");

        int i = 0;
    }
}
