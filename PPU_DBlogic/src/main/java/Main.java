import com.PPU.DB.security.MD5;
import com.PPU.DB.tables.*;
import com.PPU.DB.workLogic.ClassInvokeCall;
import com.PPU.DB.workLogic.WorkWithMZ;
import com.PPU.DB.workLogic.WorkWithPartnerMZ;
import com.PPU.DB.workLogic.WorkWithUser;

import java.util.Set;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 12.04.14
 * Time: 23:55
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {
//        WorkWithMZ work1 = new WorkWithMZ();
//        MZ mz = (MZ) work1.getEntity(1);

		com.PPU.DB.workLogic.WorkWithPartnerMZ work = new WorkWithPartnerMZ();

		PartnersMZ part = (PartnersMZ) work.getEntity(1);

		UsersMunMan user = new UsersMunMan();
		user.setLogin("Potato");
		user.setHash("hash");
		user.setPartnerMZ(part);

		WorkWithUser workus = new WorkWithUser();

		try {
			workus.addEntity(user);
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
