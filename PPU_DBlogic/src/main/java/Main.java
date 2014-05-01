import com.PPU.DB.security.MD5;
import com.PPU.DB.tables.*;
import com.PPU.DB.workLogic.ClassInvokeCall;
import com.PPU.DB.workLogic.WorkWithMZ;
import com.PPU.DB.workLogic.WorkWithPartnerMZ;
import com.PPU.DB.workLogic.WorkWithUser;

import java.util.HashSet;
import java.util.LinkedHashSet;
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

        WorkWithPartnerMZ work = new WorkWithPartnerMZ();

        PartnersMZ part = (PartnersMZ) work.getEmptyEntity();

        Set set = new LinkedHashSet();
        set.add(new ComandMZ());

        ClassInvokeCall.callMethod(part, "setComandMZ", new Object []{ set });

        int y = 0;

	}
}
