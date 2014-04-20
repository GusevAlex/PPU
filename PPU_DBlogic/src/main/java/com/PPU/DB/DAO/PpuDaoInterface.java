package com.PPU.DB.DAO;

import com.PPU.DB.tables.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 12.04.14
 * Time: 23:54
 * To change this template use File | Settings | File Templates.
 */
public interface PpuDaoInterface {

    public MZ getMz(int id);
    public ComandMZ getComandMZ(int id);
    public ComandProject getComandProject(int id);
    public CorrectionsMZ getCorrectionsMz(int id);
    public CorrectionsProject getCorrectionsProject(int id);
    public DefaultParametrsServiceMZ getDefaultParametrsServiceMz(int id);
    public LimitsMZ getLimitsMz(int id);
    public LimitsProject getLimitsProject(int id);
    public Parametrs getParametrs(int id);
    public PartnersMZ getPartnersMz(int id);
    public PartnerCommercialMan getPartnerCommercialMan(int id);
    public Program getProgram(int id);
    public Project getProject(int id);
    public Providers getProviders(int id);
    public ResourcesMZ getResourcesMz(int id);
    public ResourcesProject getResourcesProject(int id);
    public TypeBudgetService getTypeBudgetService(int id);
    public TypeMU getTypeMu(int id);
    public TypeServiceMZ getTypeServiceMz(int id);
    public ValuesParametrForMZ getValuesParametrForMz(int id);
    public ValuesParametrForProject getValuesParametrForProject(int id);
    public UsersMunMan getUsersMunMan(int id);
    public UsersComMan getUsersComMan(int id);

    public boolean saveMz(MZ MZ);
    public boolean saveComandMZ(ComandMZ comandMZ);
    public boolean saveComandProject(ComandProject comandProject);
    public boolean saveCorrectionsMz(CorrectionsMZ correctionsMZ);
    public boolean saveCorrectionsProject(CorrectionsProject correctionsProject);
    public boolean saveDefaultParametrsServiceMz(DefaultParametrsServiceMZ defaultParametrsServiceMZ);
    public boolean saveLimitsMz(LimitsMZ limitsMZ);
    public boolean saveLimitsProject(LimitsProject limitsProject);
    public boolean saveParametrs(Parametrs parametrs);
    public boolean savePartnersMz(PartnersMZ partnersMZ);
    public boolean savePartnerCommercialMan(PartnerCommercialMan partnerCommercialMan);
    public boolean saveProgram(Program program);
    public boolean saveProject(Project project);
    public boolean saveProviders(Providers providers);
    public boolean saveResourcesMz(ResourcesMZ resourcesMZ);
    public boolean saveResourcesProject(ResourcesProject resourcesProject);
    public boolean saveTypeBudgetService(TypeBudgetService typeBudgetService);
    public boolean saveTypeMu(TypeMU typeMU);
    public boolean saveTypeServiceMz(TypeServiceMZ typeServiceMZ);
    public boolean saveValuesParametrForMz(ValuesParametrForMZ valuesParametrForMZ);
    public boolean saveValuesParametrForProject(ValuesParametrForProject valuesParametrForProject);
    public boolean saveUsersMunMan(UsersMunMan UsersMunMan);
    public boolean saveUsersComMan(UsersComMan UsersComMan);

    public boolean deleteMz(MZ MZ);
    public boolean deleteComandMZ(ComandMZ comandMZ);
    public boolean deleteComandProject(ComandProject comandProject);
    public boolean deleteCorrectionsMz(CorrectionsMZ correctionsMZ);
    public boolean deleteCorrectionsProject(CorrectionsProject correctionsProject);
    public boolean deleteDefaultParametrsServiceMz(DefaultParametrsServiceMZ defaultParametrsServiceMZ);
    public boolean deleteLimitsMz(LimitsMZ limitsMZ);
    public boolean deleteLimitsProject(LimitsProject limitsProject);
    public boolean deleteParametrs(Parametrs parametrs);
    public boolean deletePartnersMz(PartnersMZ partnersMZ);
    public boolean deletePartnerCommercialMan(PartnerCommercialMan partnerCommercialMan);
    public boolean deleteProgram(Program program);
    public boolean deleteProject(Project project);
    public boolean deleteProviders(Providers providers);
    public boolean deleteResourcesMz(ResourcesMZ resourcesMZ);
    public boolean deleteResourcesProject(ResourcesProject resourcesProject);
    public boolean deleteTypeBudgetService(TypeBudgetService typeBudgetService);
    public boolean deleteTypeMu(TypeMU typeMU);
    public boolean deleteTypeServiceMz(TypeServiceMZ typeServiceMZ);
    public boolean deleteValuesParametrForMz(ValuesParametrForMZ valuesParametrForMZ);
    public boolean deleteValuesParametrForProject(ValuesParametrForProject valuesParametrForProject);
    public boolean deleteUsersMunMan(UsersMunMan UsersMunMan);
    public boolean deleteUsersComMan(UsersComMan UsersComMan);

    public boolean updateMz(MZ MZ);
    public boolean updateComandMZ(ComandMZ comandMZ);
    public boolean updateComandProject(ComandProject comandProject);
    public boolean updateCorrectionsMz(CorrectionsMZ correctionsMZ);
    public boolean updateCorrectionsProject(CorrectionsProject correctionsProject);
    public boolean updateDefaultParametrsServiceMz(DefaultParametrsServiceMZ defaultParametrsServiceMZ);
    public boolean updateLimitsMz(LimitsMZ limitsMZ);
    public boolean updateLimitsProject(LimitsProject limitsProject);
    public boolean updateParametrs(Parametrs parametrs);
    public boolean updatePartnersMz(PartnersMZ partnersMZ);
    public boolean updatePartnerCommercialMan(PartnerCommercialMan partnerCommercialMan);
    public boolean updateProgram(Program program);
    public boolean updateProject(Project project);
    public boolean updateProviders(Providers providers);
    public boolean updateResourcesMz(ResourcesMZ resourcesMZ);
    public boolean updateResourcesProject(ResourcesProject resourcesProject);
    public boolean updateTypeBudgetService(TypeBudgetService typeBudgetService);
    public boolean updateTypeMu(TypeMU typeMU);
    public boolean updateTypeServiceMz(TypeServiceMZ typeServiceMZ);
    public boolean updateValuesParametrForMz(ValuesParametrForMZ valuesParametrForMZ);
    public boolean updateValuesParametrForProject(ValuesParametrForProject valuesParametrForProject);
    public boolean updateUsersMunMan(UsersMunMan UsersMunMan);
    public boolean updateUsersComMan(UsersComMan UsersComMan);

    public List<MZ> findMz(String fields, String fieldValue);
    public List<ComandMZ> findComandMZ(String fields, String fieldValue);
    public List<ComandProject> findComandProject(String fields, String fieldValue);
    public List<CorrectionsMZ> findCorrectionsMz(String fields, String fieldValue);
    public List<CorrectionsProject> findCorrectionsProject(String fields, String fieldValue);
    public List<DefaultParametrsServiceMZ> findDefaultParametrsServiceMz(String fields, String fieldValue);
    public List<LimitsMZ> findLimitsMz(String fields, String fieldValue);
    public List<LimitsProject> findLimitsProject(String fields, String fieldValue);
    public List<Parametrs> findParametrs(String fields, String fieldValue);
    public List<PartnersMZ> findPartnersMz(String fields, String fieldValue);
    public List<PartnerCommercialMan> findPartnerCommercialMan(String fields, String fieldValue);
    public List<Program> findProgram(String fields, String fieldValue);
    public List<Project> findProject( String fields, String fieldValue);
    public List<Providers> findProviders(String fields, String fieldValue);
    public List<ResourcesMZ> findResourcesMz(String fields, String fieldValue);
    public List<ResourcesProject> findResourcesProject(String fields, String fieldValue);
    public List<TypeBudgetService> findTypeBudgetService(String fields, String fieldValue);
    public List<TypeMU> findTypeMu(String fields, String fieldValue);
    public List<TypeServiceMZ> findTypeServiceMz(String fields, String fieldValue);
    public List<ValuesParametrForMZ> findValuesParametrForMz(String fields, String fieldValue);
    public List<ValuesParametrForProject> findValuesParametrForProject(String fields, String fieldValue);
    public List<UsersMunMan> findUsersMunMan(String fields, String fieldValue);
    public List<UsersComMan> findUsersComMan(String fields, String fieldValue);
}
