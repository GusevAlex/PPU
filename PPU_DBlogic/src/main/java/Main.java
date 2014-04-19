import com.PPU.DB.DAO.PpuDao;
import com.PPU.DB.DAO.PpuDaoInterface;
import com.PPU.DB.security.MD5;
import com.PPU.DB.tables.*;
import com.PPU.DB.workLogic.ClassInvokeCall;
import com.PPU.DB.workLogic.WorkWithMZ;
import com.PPU.DB.workLogic.WorkWithUser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.zip.CRC32;
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
        String gh = MD5.getMD5("mypassword");
        WorkWithMZ workWithMZ = new WorkWithMZ(1);

        try {
            workWithUser.addEntity(new Users());
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
