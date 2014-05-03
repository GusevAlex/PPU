import com.PPU.DB.security.MD5;
import com.PPU.DB.tables.ProgramMZ;
import com.PPU.DB.workLogic.WorkWithProgramCommerc;
import com.PPU.DB.workLogic.WorkWithProgramMZ;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 02.05.14
 * Time: 16:05
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        WorkWithProgramMZ work = new WorkWithProgramMZ();

        Object pm = work.getEntity(1);

        WorkWithProgramCommerc work2 = new WorkWithProgramCommerc();
        pm = work2.getEntity(1);

        int y = 0;
    }
}
