import com.PPU.DB.DAO.PpuDao;
import com.PPU.DB.DAO.PpuDaoInterface;
import com.PPU.DB.tables.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.math.BigInteger;
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
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        PpuDaoInterface ppu = (PpuDaoInterface) context.getBean("dataDao");

        List<MZ> mz = ppu.findMz("id;name","1;dfgfdg");


        ComandProject comandProject = ppu.getComandProject(1);

        CorrectionsMZ correctionsMZ = ppu.getCorrectionsMz(1);
        CorrectionsProject correctionsProject = ppu.getCorrectionsProject(1);

        DefaultParametrsServiceMZ defaultParametrsServiceMZ = ppu.getDefaultParametrsServiceMz(1);

        LimitsMZ limitsMZ = ppu.getLimitsMz(1);
        LimitsProject limitsProject = ppu.getLimitsProject(1);

        MZ mz1 = ppu.getMz(1);

        Parametrs parametrs = ppu.getParametrs(1);

        PartnerCommercialMan partnerCommercialMan = ppu.getPartnerCommercialMan(1);
        PartnersMZ partnersMZ = ppu.getPartnersMz(1);

        Program program = ppu.getProgram(1);
        Project project = ppu.getProject(1);

        Providers providers = ppu.getProviders(1);

        ResourcesProject resourcesProject = ppu.getResourcesProject(1);
        ResourcesMZ resourcesMZ = ppu.getResourcesMz(1);

        TypeBudgetService typeBudgetService = ppu.getTypeBudgetService(1);

        TypeMU typeMU = ppu.getTypeMu(1);

        TypeServiceMZ typeServiceMZ = ppu.getTypeServiceMz(1);

        ValuesParametrForProject valuesParametrForProject = ppu.getValuesParametrForProject(1);
        ValuesParametrForMZ valuesParametrForMZ = ppu.getValuesParametrForMz(1);

        int y = 0;
    }
}
