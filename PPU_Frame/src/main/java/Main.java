import com.PPU.DB.security.MD5;
import com.PPU.DB.tables.MZ;
import com.PPU.DB.tables.ProgramMZ;
import com.PPU.DB.workLogic.WorkWithMZ;
import com.PPU.DB.workLogic.WorkWithProgramCommerc;
import com.PPU.DB.workLogic.WorkWithProgramMZ;
import com.PPU.DB.workLogic.WorkWithUser;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 02.05.14
 * Time: 16:05
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
		WorkWithMZ work = new WorkWithMZ();

		int y = work.getUserRole((MZ)work.getEntity(1),new WorkWithUser().getUserMunManEntity(1));

		int u = 0;
    }
}
