package com.PPU.DB.DAO;

import com.PPU.DB.MailService.MailSender;
import com.PPU.DB.tables.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 12.04.14
 * Time: 23:53
 * To change this template use File | Settings | File Templates.
 */

@Repository
@Transactional(readOnly = true)
public class PpuDao implements PpuDaoInterface {

    private static String MZ_TABLE = "MZ";
    private static String COMADN_MZ_TABLE = "ComandMZ";
    private static String COMAND_PROJECT_TABLE = "ComandProject";
    private static String CORRECTION_MZ_TABLE = "CorrectionsMZ";
    private static String CORRECTION_PROJECT_TABLE = "CorrectionsProject";
    private static String DEFAULT_PARAMETRS_SERVICE_MZ_TABLE = "DefaultParametrsServiceMZ";
    private static String LIMITS_MZ_TABLE = "LimitsMZ";
    private static String LIMITS_PROJECT_TABLE = "LimitsProject";
    private static String PARAMETRS_TABLE = "Parametrs";
    private static String PARTNERS_MZ_TABLE = "PartnersMZ";
    private static String PARTNERS_PROJECT_TABLE = "PartnerCommercialMan";
    private static String PROGRAM_MZ_TABLE = "ProgramMZ";
    private static String PROGRAM_COMMERC_TABLE = "ProgramCommerc";
    private static String PROJECT_TABLE = "Project";
    private static String PROVIDERS_TABLE = "Providers";
    private static String RESOURCES_MZ_TABLE = "ResourcesMZ";
    private static String RESOURCES_PROJECT_TABLE = "ResourcesProject";
    private static String TYPE_BUDGET_SERVICE_TABLE = "TypeBudgetService";
    private static String TYPE_MU_TABLE = "TypeMU";
    private static String TYPE_SERVICE_MZ_TABLE = "TypeServiceMZ";
    private static String VALUES_PARAMETR_FOR_MZ_TABLE = "ValuesParametrForMZ";
    private static String VALUES_PARAMETR_FOR_PROJECT_TABLE = "ValuesParametrForProject";
    private static String USERS_MUN_MAN_TABLE = "UsersMunMan";
    private static String USERS_COM_MAN_TABLE = "UsersComMan";
    private static String NOTIFICATION_MU_TABLE = "NotificationMU";
    private static String NOTIFICATION_COM_TABLE = "NotificationCom";

    private static SessionFactory sessionFactory;

    //получаем наш datasource
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        PpuDao.sessionFactory = sessionFactory;
    }

    @Override
    public MZ getMz(int id)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (MZ) session.get(MZ.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new MZ();
        }
    }

    @Override
    public ComandMZ getComandMZ(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (ComandMZ) session.get(ComandMZ.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new ComandMZ();
        }
    }

    @Override
    public ComandProject getComandProject(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (ComandProject) session.get(ComandProject.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new ComandProject();
        }
    }

    @Override
    public CorrectionsMZ getCorrectionsMz(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (CorrectionsMZ) session.get(CorrectionsMZ.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new CorrectionsMZ();
        }
    }

    @Override
    public CorrectionsProject getCorrectionsProject(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (CorrectionsProject) session.get(CorrectionsProject.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new CorrectionsProject();
        }
    }

    @Override
    public DefaultParametrsServiceMZ getDefaultParametrsServiceMz(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (DefaultParametrsServiceMZ) session.get(DefaultParametrsServiceMZ.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new DefaultParametrsServiceMZ();
        }
    }

    @Override
    public LimitsMZ getLimitsMz(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (LimitsMZ) session.get(LimitsMZ.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new LimitsMZ();
        }
    }

    @Override
    public LimitsProject getLimitsProject(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (LimitsProject) session.get(LimitsProject.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new LimitsProject();
        }
    }

    @Override
    public Parametrs getParametrs(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (Parametrs) session.get(Parametrs.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new Parametrs();
        }
    }

    @Override
    public PartnersMZ getPartnersMz(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (PartnersMZ) session.get(PartnersMZ.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new PartnersMZ();
        }
    }

    @Override
    public PartnerCommercialMan getPartnerCommercialMan(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (PartnerCommercialMan) session.get(PartnerCommercialMan.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new PartnerCommercialMan();
        }
    }

    @Override
    public ProgramMZ getProgramMZ(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (ProgramMZ) session.get(ProgramMZ.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new ProgramMZ();
        }
    }

    @Override
    public ProgramCommerc getProgramCommerc(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (ProgramCommerc) session.get(ProgramCommerc.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new ProgramCommerc();
        }
    }

    @Override
    public Project getProject(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (Project) session.get(Project.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new Project();
        }
    }

    @Override
    public Providers getProviders(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (Providers) session.get(Providers.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new Providers();
        }
    }

    @Override
    public ResourcesMZ getResourcesMz(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (ResourcesMZ) session.get(ResourcesMZ.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new ResourcesMZ();
        }
    }

    @Override
    public ResourcesProject getResourcesProject(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (ResourcesProject) session.get(ResourcesProject.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new ResourcesProject();
        }
    }

    @Override
    public TypeBudgetService getTypeBudgetService(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (TypeBudgetService) session.get(TypeBudgetService.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new TypeBudgetService();
        }
    }

    @Override
    public TypeMU getTypeMu(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (TypeMU) session.get(TypeMU.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new TypeMU();
        }
    }

    @Override
    public TypeServiceMZ getTypeServiceMz(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (TypeServiceMZ) session.get(TypeServiceMZ.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new TypeServiceMZ();
        }
    }

    @Override
    public ValuesParametrForMZ getValuesParametrForMz(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (ValuesParametrForMZ) session.get(ValuesParametrForMZ.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new ValuesParametrForMZ();
        }
    }

    @Override
    public ValuesParametrForProject getValuesParametrForProject(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (ValuesParametrForProject) session.get(ValuesParametrForProject.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new ValuesParametrForProject();
        }
    }

    @Override
    public UsersMunMan getUsersMunMan(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (UsersMunMan) session.get(UsersMunMan.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new UsersMunMan();
        }
    }

    @Override
    public UsersComMan getUsersComMan(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (UsersComMan) session.get(UsersComMan.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new UsersComMan();
        }
    }

    @Override
    public NotificationCom getNotificationCom(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (NotificationCom) session.get(NotificationCom.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new NotificationCom();
        }
    }

    @Override
    public NotificationMU getNotificationMU(int id) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            return (NotificationMU) session.get(NotificationMU.class, id);
        } catch (Exception e)
        {
            System.out.println(e);
            return new NotificationMU();
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean saveMz(MZ MZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();

            MZ.setIdLeader(MZ.getLeader().getId());
            MZ.setIdProgram(MZ.getProgram().getId());
            MZ.setService_type(MZ.getTypeServiceMZ().getId());


            session.save(MZ);

            for (LimitsMZ limitsMZ : MZ.getLimitsMZ())
            {
                limitsMZ.setIdMZ(MZ.getId());

                if (limitsMZ.getId() == 0)
                    this.saveLimitsMz(limitsMZ);
                else
                    this.updateLimitsMz(limitsMZ);
            }

            for (ValuesParametrForMZ valuesParametrForMZ : MZ.getValuesParametrForMZ())
            {
                valuesParametrForMZ.setIdMZ(MZ.getId());

                if (valuesParametrForMZ.getId() == 0)
                    this.saveValuesParametrForMz(valuesParametrForMZ);
                else
                    this.updateValuesParametrForMz(valuesParametrForMZ);
            }

            for (ComandMZ comandMZ : MZ.getComandMZ())
            {
                comandMZ.setIdMZ(MZ.getId());

                if (comandMZ.getId() == 0)
                    this.saveComandMZ(comandMZ);
                else
                    this.updateComandMZ(comandMZ);
            }

            for (ResourcesMZ resourcesMZ : MZ.getResourcesMZ())
            {
                resourcesMZ.setIdMZ(MZ.getId());

                if (resourcesMZ.getId() == 0)
                    this.saveResourcesMz(resourcesMZ);
                else
                    this.updateResourcesMz(resourcesMZ);
            }

            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean saveComandMZ(ComandMZ comandMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.save(comandMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean saveComandProject(ComandProject comandProject) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.save(comandProject);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean saveCorrectionsMz(CorrectionsMZ correctionsMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
			correctionsMZ.setCorrectionDate(Calendar.getInstance().getTime());

            session.save(correctionsMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean saveCorrectionsProject(CorrectionsProject correctionsProject) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
			correctionsProject.setCorrectionDate(Calendar.getInstance().getTime());

            session.save(correctionsProject);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean saveDefaultParametrsServiceMz(DefaultParametrsServiceMZ defaultParametrsServiceMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.save(defaultParametrsServiceMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean saveLimitsMz(LimitsMZ limitsMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.save(limitsMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean saveLimitsProject(LimitsProject limitsProject) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.save(limitsProject);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean saveParametrs(Parametrs parametrs) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.save(parametrs);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean savePartnersMz(PartnersMZ partnersMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.save(partnersMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean savePartnerCommercialMan(PartnerCommercialMan partnerCommercialMan) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.save(partnerCommercialMan);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean saveProgramMZ(ProgramMZ programMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.save(programMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean saveProgramCommerc(ProgramCommerc programCommerc) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.save(programCommerc);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean saveProject(Project project) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.save(project);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean saveProviders(Providers providers) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.save(providers);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean saveResourcesMz(ResourcesMZ resourcesMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.save(resourcesMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean saveResourcesProject(ResourcesProject resourcesProject) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.save(resourcesProject);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean saveTypeBudgetService(TypeBudgetService typeBudgetService) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.save(typeBudgetService);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean saveTypeMu(TypeMU typeMU) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.save(typeMU);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean saveTypeServiceMz(TypeServiceMZ typeServiceMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.save(typeServiceMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean saveValuesParametrForMz(ValuesParametrForMZ valuesParametrForMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.save(valuesParametrForMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean saveValuesParametrForProject(ValuesParametrForProject valuesParametrForProject) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.save(valuesParametrForProject);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean saveUsersMunMan(UsersMunMan UsersMunMan) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.save(UsersMunMan);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean saveUsersComMan(UsersComMan UsersComMan) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.save(UsersComMan);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean saveNotificationCom(NotificationCom notificationCom) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            notificationCom.setReceiver(notificationCom.getPartners().getId());

            for (UsersComMan user : notificationCom.getPartners().getUser())
                if (user.isSendMail())
                    MailSender.sendMessage(user.getEmail(), "", notificationCom.getText());

            session.save(notificationCom);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean saveNotificationMU(NotificationMU notificationMU) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            notificationMU.setReceiver(notificationMU.getPartners().getId());

            for (UsersMunMan user : notificationMU.getPartners().getUser())
                if (user.isSendMail())
                    MailSender.sendMessage(user.getEmail(), "", notificationMU.getText());

            session.save(notificationMU);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteMz(MZ MZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(MZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteComandMZ(ComandMZ comandMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(comandMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteComandProject(ComandProject comandProject) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(comandProject);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteCorrectionsMz(CorrectionsMZ correctionsMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(correctionsMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteCorrectionsProject(CorrectionsProject correctionsProject) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(correctionsProject);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteDefaultParametrsServiceMz(DefaultParametrsServiceMZ defaultParametrsServiceMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(defaultParametrsServiceMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteLimitsMz(LimitsMZ limitsMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(limitsMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteLimitsProject(LimitsProject limitsProject) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(limitsProject);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteParametrs(Parametrs parametrs) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(parametrs);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deletePartnersMz(PartnersMZ partnersMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(partnersMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deletePartnerCommercialMan(PartnerCommercialMan partnerCommercialMan) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(partnerCommercialMan);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteProgramMZ(ProgramMZ programMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(programMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteProgramCommerc(ProgramCommerc programCommerc) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(programCommerc);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteProject(Project project) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(project);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteProviders(Providers providers) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(providers);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteResourcesMz(ResourcesMZ resourcesMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(resourcesMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteResourcesProject(ResourcesProject resourcesProject) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(resourcesProject);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteTypeBudgetService(TypeBudgetService typeBudgetService) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(typeBudgetService);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteTypeMu(TypeMU typeMU) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(typeMU);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteTypeServiceMz(TypeServiceMZ typeServiceMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(typeServiceMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteValuesParametrForMz(ValuesParametrForMZ valuesParametrForMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(valuesParametrForMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteValuesParametrForProject(ValuesParametrForProject valuesParametrForProject) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(valuesParametrForProject);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteUsersMunMan(UsersMunMan UsersMunMan) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(UsersMunMan);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteUsersComMan(UsersComMan UsersComMan) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(UsersComMan);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteNotificationCom(NotificationCom notificationCom) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(notificationCom);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteNotificationMU(NotificationMU notificationMU) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(notificationMU);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateMz(MZ MZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(MZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateComandMZ(ComandMZ comandMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(comandMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateComandProject(ComandProject comandProject) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(comandProject);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateCorrectionsMz(CorrectionsMZ correctionsMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
			correctionsMZ.setCorrectionDate(Calendar.getInstance().getTime());

            session.update(correctionsMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateCorrectionsProject(CorrectionsProject correctionsProject) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
			correctionsProject.setCorrectionDate(Calendar.getInstance().getTime());

            session.update(correctionsProject);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateDefaultParametrsServiceMz(DefaultParametrsServiceMZ defaultParametrsServiceMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(defaultParametrsServiceMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateLimitsMz(LimitsMZ limitsMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(limitsMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateLimitsProject(LimitsProject limitsProject) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(limitsProject);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateParametrs(Parametrs parametrs) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(parametrs);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updatePartnersMz(PartnersMZ partnersMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(partnersMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updatePartnerCommercialMan(PartnerCommercialMan partnerCommercialMan) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(partnerCommercialMan);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateProgramMZ(ProgramMZ programMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(programMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean updateProgramCommerc(ProgramCommerc programCommerc) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(programCommerc);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateProject(Project project) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(project);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateProviders(Providers providers) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(providers);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateResourcesMz(ResourcesMZ resourcesMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(resourcesMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateResourcesProject(ResourcesProject resourcesProject) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(resourcesProject);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateTypeBudgetService(TypeBudgetService typeBudgetService) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(typeBudgetService);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateTypeMu(TypeMU typeMU) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(typeMU);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateTypeServiceMz(TypeServiceMZ typeServiceMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(typeServiceMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateValuesParametrForMz(ValuesParametrForMZ valuesParametrForMZ) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(valuesParametrForMZ);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateValuesParametrForProject(ValuesParametrForProject valuesParametrForProject) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(valuesParametrForProject);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateUsersMunMan(UsersMunMan UsersMunMan) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(UsersMunMan);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateUsersComMan(UsersComMan UsersComMan) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(UsersComMan);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateNotificationCom(NotificationCom notificationCom) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(notificationCom);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateNotificationMU(NotificationMU notificationMU) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(notificationMU);
            return true;
        } catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    private String getSQLForFindByParam(String fields, String fieldValue)
    {


        String [] fieldsMas = fields.split(";");
        String [] fieldValueMas = fieldValue.split(";");

        if (fieldsMas.length != fieldValueMas.length)
            throw new IllegalArgumentException("Кол-во полей не совпадает с кол-вом значений");

        String s = " Where ";

        for (int i=0; i<fieldsMas.length; i++)
        {
            s += fieldsMas[i] + "='" + fieldValueMas[i] + "'" + ((i+1) == fieldsMas.length ? "" : " and ");
        }

        if (fields.equals(""))
            return "";

        return s;
    }

    @Override
    public List<MZ> findMz(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+MZ_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<ComandMZ> findComandMZ(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+COMADN_MZ_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<ComandProject> findComandProject(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+COMAND_PROJECT_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<CorrectionsMZ> findCorrectionsMz(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+CORRECTION_MZ_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<CorrectionsProject> findCorrectionsProject(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+CORRECTION_PROJECT_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<DefaultParametrsServiceMZ> findDefaultParametrsServiceMz(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+DEFAULT_PARAMETRS_SERVICE_MZ_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<LimitsMZ> findLimitsMz(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+LIMITS_MZ_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<LimitsProject> findLimitsProject(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+LIMITS_PROJECT_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<Parametrs> findParametrs(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+PARAMETRS_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<PartnersMZ> findPartnersMz(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+PARTNERS_MZ_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<PartnerCommercialMan> findPartnerCommercialMan(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+PARTNERS_PROJECT_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<ProgramMZ> findProgramMZ(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+PROGRAM_MZ_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<ProgramCommerc> findProgramCommerc(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+PROGRAM_COMMERC_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<Project> findProject(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+PROJECT_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<Providers> findProviders(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+PROVIDERS_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<ResourcesMZ> findResourcesMz(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+RESOURCES_MZ_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<ResourcesProject> findResourcesProject(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+RESOURCES_PROJECT_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<TypeBudgetService> findTypeBudgetService(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+TYPE_BUDGET_SERVICE_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<TypeMU> findTypeMu(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+TYPE_MU_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<TypeServiceMZ> findTypeServiceMz(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+TYPE_SERVICE_MZ_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<ValuesParametrForMZ> findValuesParametrForMz(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+VALUES_PARAMETR_FOR_MZ_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<ValuesParametrForProject> findValuesParametrForProject(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+VALUES_PARAMETR_FOR_PROJECT_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<UsersMunMan> findUsersMunMan(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+USERS_MUN_MAN_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<UsersComMan> findUsersComMan(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+USERS_COM_MAN_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<NotificationCom> findNotificationCom(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery("from "+NOTIFICATION_MU_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<NotificationMU> findNotificationMU(String fields, String fieldValue) {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String s = getSQLForFindByParam(fields, fieldValue);
            return session.createQuery(	"from "+NOTIFICATION_COM_TABLE + s).list();
        } catch (Exception e)
        {
            System.out.println(e);
            return Collections.emptyList();
        }
    }
}
