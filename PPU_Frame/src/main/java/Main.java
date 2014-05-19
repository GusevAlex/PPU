import com.PPU.DB.security.MD5;
import com.PPU.DB.tables.*;
import com.PPU.DB.workLogic.*;
import com.PPU.XML.ParseCorrection;
import org.zkoss.util.media.Media;
import org.zkoss.zhtml.Filedownload;
import org.zkoss.zk.ui.Sessions;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 02.05.14
 * Time: 16:05
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        ComandProject com = (ComandProject) new WorkWithComandProject().getEntity(1);
        com.getPartnerProject();
        int role = new WorkWithProject().getUserRole((Project)new WorkWithProject().getEntity(1),(UsersComMan) new WorkWithUser().getUserComManEntity(1));

        int y = 0;
    }
}
