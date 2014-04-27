import com.PPU.DB.security.MD5;
import com.PPU.DB.tables.*;
import com.PPU.DB.workLogic.WorkWithMZ;
import com.PPU.DB.workLogic.WorkWithPartnerMZ;
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


        WorkWithPartnerMZ workWithPartnerMZ = new WorkWithPartnerMZ();
        try {
            Object obj = workWithPartnerMZ.invokeCallMethode(workWithPartnerMZ.getEntity(1), "getName");

            int f = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
