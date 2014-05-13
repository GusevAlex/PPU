import com.PPU.DB.security.MD5;
import com.PPU.DB.tables.*;
import com.PPU.DB.workLogic.*;
import com.PPU.XML.ParseCorrection;
import org.zkoss.util.media.Media;
import org.zkoss.zhtml.Filedownload;
import org.zkoss.zk.ui.Sessions;

import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 02.05.14
 * Time: 16:05
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        Object [] obj = new WorkCorrectionMZ().getListRows().toArray();
        CorrectionsMZ [] cor = new CorrectionsMZ[obj.length];

        for (int i=0; i<cor.length; i++)
        {
            cor[i] = (CorrectionsMZ) obj[i];
        }
        ParseCorrection parse = new ParseCorrection();
        parse.setFileName("D:\\\\1.xml");

        parse.saveReportToXML((CorrectionsMZ []) cor);
    }
}
