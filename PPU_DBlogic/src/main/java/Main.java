import com.PPU.DB.DAO.PpuDao;
import com.PPU.DB.DAO.PpuDaoInterface;
import com.PPU.DB.tables.*;
import com.PPU.DB.workLogic.ClassInvokeCall;
import com.PPU.DB.workLogic.WorkWithMZ;
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

        WorkWithMZ workWithMZ = new WorkWithMZ();

        List<MZ> list = workWithMZ.findAndGetAllRow("","");
        
        workWithMZ.setRowById(1);

        String s = "";
        try {
            s = (String) workWithMZ.getColumnValue(WorkWithMZ.COLUMN_NAME);
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        int y = 0;
    }
}
