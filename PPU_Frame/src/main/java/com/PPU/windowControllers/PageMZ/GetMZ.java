package com.PPU.windowControllers.PageMZ;

import com.PPU.DB.tables.*;
import com.PPU.DB.workLogic.*;
import com.PPU.composite.ButtonOpenList;
import com.PPU.composite.DualObjectListBox;
import com.PPU.composite.ObjectListBox;
import com.PPU.funcControl.NotificationService;
import com.PPU.windowControllers.ButtonClickInterf;
import com.PPU.windowControllers.FileController;
import com.PPU.windowControllers.GetListParam;
import com.PPU.windowControllers.report.ComandMZDataSource;
import com.PPU.windowControllers.report.LimitsMZDataSource;
import com.PPU.windowControllers.report.MZDataSource;
import com.PPU.windowControllers.report.ValuesMZDataSource;
import org.zkoss.bind.annotation.*;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkex.zul.Jasperreport;
import org.zkoss.zul.*;
import org.zkoss.chart.model.CategoryModel;
import org.zkoss.chart.model.DefaultCategoryModel;
import org.zkoss.zul.Messagebox;

import java.lang.Object;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Alex on 05.05.2014.
 */
public class GetMZ implements GetListParam {

    private int id;

	MZ mz;
	MZ oldMz;

    String mzName;

    String program;

    String name;
    Date startDate;
    Date expirationDate;
    String description;
    float budget;

    Object comandMZ;
    Object[] program1;
    WorkWithProgramMZ workerProgr = new WorkWithProgramMZ();

    String typeServiceMZ;

    List listTypeService;

    Object [] limitsMZ;
    Object [] parametr;
    private Object [] programs;
    private Object leaders;
    private Object [] allLeader;
    private Object [] providers;

    private String nameNextLevel;
    private String namePrevisLevel;
    private String nameLevel;
    private String notWorkString = new String();
    private String statusName;

    Object valuesParametrForMZ;
    Object resourcesMZ;
    Object correctionsMZ;

    ListModelList<Object> limitModelList;
    ListModelList<Object> valModelList;
    ListModelList<Object> fileModelList;

	Window wind = new Window();

    private int level;

    @Wire
    private Grid valuesParametrForMZ1;

    @Wire
    private Grid limitsMZ1;

    @Wire
    private ObjectListBox leaderMZ1;

    @Wire
    private DualObjectListBox comandMZ1;

    @Wire
    private ObjectListBox program2;

    @Wire
    private Combobox typeService1;

    @Wire
    private DualObjectListBox resourcesMZ1;

    @Wire
    private Grid fileList;

    @Wire
    private Jasperreport report;

    public GetMZ()
    {

    }

    public String getMzName() {
        return mzName;
    }

    public void setMzName(String mzName) {
        this.mzName = mzName;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public Object getComandMZ() {
        return comandMZ;
    }

    public void setComandMZ(Object comandMZ) {
        this.comandMZ = comandMZ;
    }

    public Object[] getProgram1() {
        return program1;
    }

    public void setProgram1(Object[] program1) {
        this.program1 = program1;
    }

    public WorkWithProgramMZ getWorkerProgr() {
        return workerProgr;
    }

    public void setWorkerProgr(WorkWithProgramMZ workerProgr) {
        this.workerProgr = workerProgr;
    }

    public String getTypeServiceMZ() {
        return typeServiceMZ;
    }

    public void setTypeServiceMZ(String typeServiceMZ) {
        this.typeServiceMZ = typeServiceMZ;
    }

    public List getListTypeService() {
        return listTypeService;
    }

    public void setListTypeService(List listTypeService) {
        this.listTypeService = listTypeService;
    }

    public Object[] getLimitsMZ() {
        return limitsMZ;
    }

    public void setLimitsMZ(Object[] limitsMZ) {
        this.limitsMZ = limitsMZ;
    }

    public Object[] getParametr() {
        return parametr;
    }

    public void setParametr(Object[] parametr) {
        this.parametr = parametr;
    }

    public Object getValuesParametrForMZ() {
        return valuesParametrForMZ;
    }

    public void setValuesParametrForMZ(Object valuesParametrForMZ) {
        this.valuesParametrForMZ = valuesParametrForMZ;
    }

    public Object getResourcesMZ() {
        return resourcesMZ;
    }

    public void setResourcesMZ(Object resourcesMZ) {
        this.resourcesMZ = resourcesMZ;
    }

    public Object getCorrectionsMZ() {
        return correctionsMZ;
    }

    public void setCorrectionsMZ(Object correctionsMZ) {
        this.correctionsMZ = correctionsMZ;
    }

    public int getId() {
        return id;
    }

    public ListModelList<Object> getValModelList() {
        return valModelList;
    }

    public void setValModelList(ListModelList<Object> valModelList) {
        this.valModelList = valModelList;
    }

    public ListModelList<Object> getLimitModelList() {
        return limitModelList;
    }

    public void setLimitModelList(ListModelList<Object> limitModelList) {
        this.limitModelList = limitModelList;
    }

    public int getLevel() {
        return level;
    }

    public Object[] getPrograms() {
        return programs;
    }

    public void setPrograms(Object[] programs) {
        this.programs = programs;
    }

    public Object getLeaders() {
        return leaders;
    }

    public void setLeaders(Object leaders) {
        this.leaders = leaders;
    }

    public Object[] getAllLeader() {
        return allLeader;
    }

    public void setAllLeader(Object[] allLeader) {
        this.allLeader = allLeader;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public MZ getMz() {
        return mz;
    }

    public void setMz(MZ mz) {
        this.mz = mz;
    }

    public Object[] getProviders() {
        return providers;
    }

    public void setProviders(Object[] providers) {
        this.providers = providers;
    }

    public String getNameNextLevel() {
        return nameNextLevel;
    }

    public void setNameNextLevel(String nameNextLevel) {
        this.nameNextLevel = nameNextLevel;
    }

    public String getNamePrevisLevel() {
        return namePrevisLevel;
    }

    public void setNamePrevisLevel(String namePrevisLevel) {
        this.namePrevisLevel = namePrevisLevel;
    }

    public String getNameLevel() {
        return nameLevel;
    }

    public void setNameLevel(String nameLevel) {
        this.nameLevel = nameLevel;
    }

    public String getNotWorkString() {
        return notWorkString;
    }

    public void setNotWorkString(String notWorkString) {
        this.notWorkString = notWorkString;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    private CategoryModel getModelChartParam()
    {
        CategoryModel model;
        model = new DefaultCategoryModel();
//        model.setValue("Tokyo", "Jan", 7.0);
        Integer [] numMas = new Integer[valModelList.size()];

        int i = 0;
        for (Object val : valModelList)
            if (((ValuesParametrForMZ) val).getParametr().getType() == 'd')
                numMas[i++] = ((ValuesParametrForMZ) val).getId();

        Arrays.sort(numMas);

        for (Integer intObj : numMas)
            for (Object val : valModelList)
            if (((ValuesParametrForMZ) val).getId() == intObj)
                if (((ValuesParametrForMZ) val).getParametr().getType() == 'd')
                {
                    model.setValue(((ValuesParametrForMZ) val).getParametr().getName(), new SimpleDateFormat("dd.MM.yyyy").format(((ValuesParametrForMZ) val).getDateRecValue()), new Float(((ValuesParametrForMZ) val).getValue()));
                    break;
                }

        return model;
    }

    public ListModelList<Object> getFileModelList() {
        return fileModelList;
    }

    public void setFileModelList(ListModelList<Object> fileModelList) {
        this.fileModelList = fileModelList;
    }

    public void setId(int id) {
        this.id = id;
        MZ MZ;

        if (id!=0)
		{
            MZ = ((com.PPU.DB.tables.MZ) new WorkWithMZ().getEntity(id));
			oldMz = ((com.PPU.DB.tables.MZ) new WorkWithMZ().getEntity(id));
		}
        else
		{
            MZ = ((com.PPU.DB.tables.MZ) new WorkWithMZ().getEmptyEntity());
			oldMz = ((com.PPU.DB.tables.MZ) new WorkWithMZ().getEmptyEntity());
		}

		mz = MZ;

        if (id!=0)
        {
            mzName = "������������� �������: " + MZ.getName();
            program = "���������: " + MZ.getProgram().getName();

            name = MZ.getName();
            startDate = MZ.getStartDate();
            expirationDate = MZ.getExpirationDate();

            if (Calendar.getInstance().getTime().before(expirationDate))
                Messagebox.show("������� ��� ��� �� �������! ���� ��������� ��� ������", "Error", Messagebox.OK, Messagebox.ERROR);

            description = MZ.getDescription();
            budget = MZ.getBudget();

            providers = new Object[mz.getResourcesMZ().size()];

            int a = 0;
            for (ResourcesMZ r : mz.getResourcesMZ())
                providers[a++] = r.getProviders();

            programs = mz.getProgram().getPartnersMZ().getProgramMZs().toArray();

            allLeader = new WorkWithPartnerMZ().getListRows().toArray();
            leaders = mz.getLeader();

            comandMZ = (Object) MZ.getComandMZ().toArray();
            program1 = new Object[1];
            program1[0] = (Object) MZ.getProgram();

            typeServiceMZ = (String) MZ.getTypeServiceMZ().getName();

            listTypeService = new WorkWithTypeServiceMZ().getListRows();

            limitsMZ =  MZ.getLimitsMZ().toArray();

            List parametrsList = new ArrayList();

            for (Object l : limitsMZ)
                parametrsList.add(((LimitsMZ)l).getParametr());

            parametr = parametrsList.toArray();
			valuesParametrForMZ = (Object) MZ.getValuesParametrForMZ().toArray();

			List val = new ArrayList();

			for (Object v : (Object []) valuesParametrForMZ)
				if (((ValuesParametrForMZ)v).getParametr().getId()!=1)
					val.add(v);
			valModelList = new ListModelList<Object>((Object []) val.toArray());

            resourcesMZ = (Object) MZ.getResourcesMZ().toArray();
            correctionsMZ = (Object) MZ.getCorrectionsMZ().toArray();

			val = new ArrayList();
			for (Object v : limitsMZ)
				if (((LimitsMZ)v).getParametr().getId()!=1)
					val.add(v);

            limitModelList = new ListModelList<Object>(val.toArray());
            fileModelList = new ListModelList<Object>(mz.getFileMZs());

            List user = new WorkWithUser().findAndGetAllRow("login", (String) Sessions.getCurrent().getAttribute("login"));
            setLevel(new WorkWithMZ().getUserRole(mz, (UsersMunMan) user.get(0)));
           // setLevel(4);

            String levelName = new String();
            namePrevisLevel = "";

            switch (mz.getStatus()+1)
            {
                case 1 : levelName = "������";
                    nameLevel = "������ ��: ���������";
                    break;
                case 2 : levelName = "������������";
                    nameLevel = "������ ��: ������";
                    break;
                case 3 : levelName = "�� ������������";
                    nameLevel = "������ ��: ������������";
                    break;
                case 4 : levelName = "�����������";
                    nameLevel = "������ ��: �� ������������";
                    namePrevisLevel = "�������� �� ������ \"������������\"";
                    break;
                case 5 : levelName = "���������";
                    namePrevisLevel = "�������� �� ������ \"������������\"";
                    nameLevel = "������ ��: �����������";
                    break;
            }
            nameNextLevel = "";

            if (!levelName.equals(""))
                nameNextLevel = "��������� � ��������� �� ������ \"" + levelName + "\"";

            for (NotificationMU not : ((UsersMunMan)user.get(0)).getPartnerMZ().getNotificationMU())
            {
                Pattern pattern = Pattern.compile("\\Q"+Executions.getCurrent().getDesktop().getRequestPath()+"?id="+Executions.getCurrent().getParameter("id")+"\\E");

                if (pattern.matcher(not.getText()).find())
                {
                    not.setRead(true);

                    try {
                        new WorkWithNotificationMU().changeEntity(not);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            statusName = "";

            int levelUser = new WorkWithMZ().getUserRole(mz, ((UsersMunMan)user.get(0)));
            if (levelUser != 0)
                if (levelUser == 1)
                    statusName = "�� ��������� ����������";
            else
                if (levelUser == 2)
                    statusName = "�� ��������� �������������";
            else
                if (levelUser == 3)
                    statusName = "�� ��������� ���������� � �������������";
            else
                if (levelUser == 4)
                    statusName = "�� ��������� ������������";
            else
                if (levelUser == 6)
                    statusName = "�� ��������� ����������, ������������� � ������������";
            else
                statusName = "�� �� �������� �� ����� �������������� �������";


            if (getEndCommandMZ().size() != 0)
                notWorkString = "������� �����������, ����������� ������";
        }
        else
        {
            mzName = "�������� �������������� �������";
            program = "";

            name = "";
            startDate = Calendar.getInstance().getTime();
            expirationDate = new Date();
            description = "";
            budget = 0;

            mz.setName(name);
            mz.setStartDate(startDate);
            mz.setExpirationDate(expirationDate);
            mz.setDescription(description);
            mz.setBudget(budget);

            providers = new Object[]{new Providers()};
            programs = ((UsersMunMan)new WorkWithUser().findAndGetAllRow("login", (String) Sessions.getCurrent().getAttribute("login")).get(0)).getPartnerMZ().getProgramMZs().toArray();

            allLeader = new WorkWithPartnerMZ().getListRows().toArray();
            leaders = new PartnersMZ();

            comandMZ = (Object) new Object []{new ComandMZ()};
            program1 = new Object[1];

            String progr = Executions.getCurrent().getParameter("progr");
            if (progr == null)
                program1[0] = new Object();
            else {
                program1[0] = new WorkWithProgramMZ().getEntity(new Integer(progr));

                program = "���������: "+((ProgramMZ) program1[0]).getName();
            }

            typeServiceMZ = "";

            listTypeService = new WorkWithTypeServiceMZ().getListRows();

            limitsMZ =  new Object [0];

            List parametrsList = new ArrayList();

            for (Object l : limitsMZ)
                parametrsList.add(((LimitsMZ)l).getParametr());

            parametr = parametrsList.toArray();

//            Object [] valObj = new Object[mz.getTypeServiceMZ().getDefaultParametrsServiceMZs().size()];
//
//            int i = 0;
//            for (DefaultParametrsServiceMZ def : mz.getTypeServiceMZ().getDefaultParametrsServiceMZs())
//            {
//                ValuesParametrForMZ valMZ = new ValuesParametrForMZ();
//                valMZ.setIdParametr(def.getIdParametr());
//                valMZ.setParametr((Parametrs) new WorkWithParametrs().getEntity(def.getIdParametr()));
//                valObj[i++] = valMZ;
//            }

            valuesParametrForMZ = (Object) new Object[0];
            resourcesMZ = (Object) new Object []{new ResourcesMZ()};
            correctionsMZ = (Object) new Object [0];

            limitModelList = new ListModelList<Object>(limitsMZ);
            fileModelList = new ListModelList<Object>(new ArrayList<FileMZ>());
            valModelList = new ListModelList<Object>((Object []) valuesParametrForMZ);

            namePrevisLevel = "";
            nameNextLevel = "��������� � ��������� �� ������ \"������\"";
            statusName = "";
        }

//        setLevel(3);
    }

    private boolean checkAllFields()
    {
        boolean success = true;

        String name = mz.getName();
        Date startDate = mz.getStartDate();
        Date expirationDate = mz.getExpirationDate();
        String description = mz.getDescription();
        float budget = mz.getBudget();

        if (name.equals(""))
        {
            Messagebox.show("�� ������� ��������!", "Error", Messagebox.OK, Messagebox.ERROR);
            success = false;
        }

        if (description.equals(""))
        {
            Messagebox.show("�� ������� ��������!", "Error", Messagebox.OK, Messagebox.ERROR);
            success = false;
        }

        if (budget == 0)
        {
            Messagebox.show("������ �� ������ ���� ����� 0!", "Error", Messagebox.OK, Messagebox.ERROR);
            success = false;
        }

        if (leaderMZ1.getSelectedIndex() == -1)
        {
            Messagebox.show("�� ������ ������������ �������!", "Error", Messagebox.OK, Messagebox.ERROR);
            success = false;
        }

        if (comandMZ1.getRightListbox().getObjs().length == 0)
        {
            Messagebox.show("�� ������� �������� �������!", "Error", Messagebox.OK, Messagebox.ERROR);
            success = false;
        }

        if (typeService1.getSelectedIndex() == -1)
        {
            Messagebox.show("�� ������� ��� �������!", "Error", Messagebox.OK, Messagebox.ERROR);
            success = false;
        }

        if (resourcesMZ1.getRightListbox().getObjs().length == 0)
        {
            Messagebox.show("�� ������� �������!", "Error", Messagebox.OK, Messagebox.ERROR);
            success = false;
        }

        for (Object obj : new WorkWithParametrs().getListRows())
            if (((Parametrs)obj).getType() == 'd')
            {
                Float res = 0f;
                for (Object o : (Object[]) valuesParametrForMZ)
                {
                    if (((ValuesParametrForMZ)o).getParametr().equals(obj))
                    {
                        res+=new Float(((ValuesParametrForMZ)o).getValue());
                    }
                }

                for (Object lim : (Object[]) limitsMZ)
                {
                    if (((LimitsMZ)lim).getParametr().equals(obj))
                    {
                        Float limVal = new Float(((LimitsMZ)lim).getValue());
                        if (limVal<=res)
                        {
                            Messagebox.show("����� ����������� ��������� \""+((Parametrs) obj).getName()+"\" ��������� ����������� ("+limVal+")", "Error", Messagebox.OK, Messagebox.ERROR);
                            break;
                        }
                    }
                }
            }

        return success;
    }

	private void compareAndSaveCorrection()
	{
		WorkWithCorrectionMZ worker = new WorkWithCorrectionMZ();

		if (oldMz.getStatus() != mz.getStatus())
		{
			try {
				CorrectionsMZ val = new CorrectionsMZ();
				val.setIdParametr(((Parametrs) new WorkWithParametrs().getEntity(1)).getId());
				String levelName = "";

				switch (mz.getStatus())
				{
					case 1 : levelName = "���������";
						break;
					case 2 : levelName = "������";
						break;
					case 3 : levelName = "������������";
						break;
					case 4 : levelName = "�� ������������";
						break;
					case 5 : levelName = "�����������";
						break;
				}

				val.setValueAfter(levelName);
				val.setLimits(false);
                val.setIdMZ(mz.getId());

				worker.addEntity(val);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List listLimitList = limitModelList.getInnerList();
		List listValList = valModelList.getInnerList();

		for (Object listO : listLimitList)
		{
			boolean isFind = false;

			for (Object o : limitsMZ)
            if (((LimitsMZ)listO).getId() == (((LimitsMZ)o).getId())) {
                isFind = true;
                if (((LimitsMZ) listO).getParametr().equals((((LimitsMZ) o)).getParametr())) {
                    if (!((LimitsMZ) listO).getValue().equals(((LimitsMZ) o).getValue())) {
                        try {
                            CorrectionsMZ val = new CorrectionsMZ();
                            val.setIdParametr(((LimitsMZ) listO).getParametr().getId());
                            val.setValueBefore(((LimitsMZ) listO).getValue());
                            val.setIdMZ(mz.getId());
							val.setLimits(true);

                            worker.addEntity(val);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

			if (!isFind)
			{
				try {
					CorrectionsMZ val = new CorrectionsMZ();
					val.setIdParametr(((LimitsMZ) listO).getParametr().getId());
					val.setValueBefore(((LimitsMZ)listO).getValue());
                    val.setIdMZ(mz.getId());
					val.setLimits(true);

					worker.addEntity(val);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		for (Object listO : listValList)
		{
            boolean isFind = false;

            for (Object o : (Object [])valuesParametrForMZ)

                if (((ValuesParametrForMZ)listO).getId() == (((ValuesParametrForMZ)o).getId())) {
                    isFind = true;
                    if (((ValuesParametrForMZ) listO).getParametr().equals((((ValuesParametrForMZ) o)).getParametr())) {
                        if (!((ValuesParametrForMZ) listO).getValue().equals(((ValuesParametrForMZ) o).getValue())) {
                            try {
                                CorrectionsMZ val = new CorrectionsMZ();
                                val.setIdParametr(((ValuesParametrForMZ) listO).getParametr().getId());
                                val.setValueBefore(((ValuesParametrForMZ) listO).getValue());
                                val.setIdMZ(mz.getId());
								val.setLimits(false);

                                worker.changeEntity(val);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            if (!isFind)
            {
                try {
                    CorrectionsMZ val = new CorrectionsMZ();
                    val.setIdParametr(((ValuesParametrForMZ) listO).getParametr().getId());
                    val.setValueBefore(((ValuesParametrForMZ)listO).getValue());
                    val.setIdMZ(mz.getId());
					val.setLimits(false);

                    worker.addEntity(val);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
	}

    public void saveMZ()
    {
        if (level == 1 || level == 0  || level == 5 || level == 6)
        {
            PartnersMZ leaderLocal = (PartnersMZ) leaderMZ1.getObjs()[leaderMZ1.getSelectedIndex()];
            mz.setLeader(leaderLocal);
        }

        Object[] objs = comandMZ1.getRightListbox().getObjs();
        ComandMZ [] comLocal = new ComandMZ [objs.length] ;

        for (int i=0; i<objs.length; i++)
            comLocal[i] = (ComandMZ) objs[i];

        for (ComandMZ com : oldMz.getComandMZ())
        {
            boolean isFind = false;

            for (ComandMZ com2 : comLocal)
                if (com2.equals(com))
                {
                    isFind = true;
                    break;
                }

            if (!isFind)
                try {
                    com.setWork(true);
                    new WorkWithCommandMz().deleteEntity(com);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

        mz.setComandMZ(new LinkedHashSet<ComandMZ>(Arrays.asList(comLocal)));

        mz.setValuesParametrForMZ(new LinkedHashSet<ValuesParametrForMZ>((List) valModelList.getInnerList()));

        if (program2.getSelectedIndex()!=-1 && (level==1 || level == 0 || level == 5 || level == 6))
            mz.setProgram((ProgramMZ) program2.getObjs()[program2.getSelectedIndex()]);

        mz.setLimitsMZ(new LinkedHashSet<LimitsMZ>((List) limitModelList.getInnerList()));

        if (typeService1.getSelectedIndex()!=-1)
        {
            TypeServiceMZ typeServiceMZLocal = (TypeServiceMZ) new WorkWithTypeServiceMZ().getListRows().get(typeService1.getSelectedIndex());
            mz.setTypeServiceMZ(typeServiceMZLocal);
        }

        objs = resourcesMZ1.getRightListbox().getObjs();
        ResourcesMZ [] res = new ResourcesMZ[objs.length];

        for (int i=0; i<objs.length; i++)
        {
            res[i] = new ResourcesMZ();
            List list = new WorkWithResourcesMZ().findAndGetAllRow("id_mz;id_provider_resources", ""+mz.getId()+";"+((Providers) objs[i]).getId());

            if (list.size() != 0)
            {
                res[i].setId(((ResourcesMZ)list.get(0)).getId());
            }

            res[i].setProviders((Providers) objs[i]);
            res[i].setIdProviderResources(((Providers) objs[i]).getId());
            res[i].setIdMZ(mz.getId());
        }

        for (ResourcesMZ com : oldMz.getResourcesMZ())
        {
            boolean isFind = false;

            for (ResourcesMZ com2 : res)
                if (com2.equals(com))
                {
                    isFind = true;
                    break;
                }

            if (!isFind)
                try {
                    new WorkWithResourcesMZ().deleteEntity(com);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

        mz.setResourcesMZ(new LinkedHashSet<ResourcesMZ>(Arrays.asList(res)));

        if (id == 0)
            mz.setStatus(1);

        if (id !=0)
        {
            try {
                new WorkWithMZ().changeEntity(mz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            try {
                new WorkWithMZ().addEntity(mz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        compareAndSaveCorrection();
    }

    @Command
    public void onClickBtn1(@ContextParam(ContextType.COMPONENT) Component comp)
    {
        if (!checkAllFields()) return;

        Clients.showBusy("���� ����������...");

        saveMZ();
        Executions.sendRedirect("");

        Clients.clearBusy();
    }

    @Command
    public void onClickBtn2(@ContextParam(ContextType.COMPONENT) Component comp)
    {
        if (!checkAllFields()) return;

        mz.setStatus(mz.getStatus() + 1);
        onClickBtn1(comp);
        PartnersMZ partnersMZ = new PartnersMZ();

        switch (mz.getStatus())
        {
            case 1 :
                NotificationService.addNotifHtmlForAdd(mz.getProgram().getPartnersMZ(), "/pages/pagesMZ/MZ.zul", mz, mz.getStatus());
                break;
            case 2 :
                NotificationService.addNotifHtmlForAdd(mz.getLeader(), "/pages/pagesMZ/MZ.zul", mz, mz.getStatus());
                break;
            case 3 :
                for (ComandMZ com : mz.getComandMZ())
                {
                    NotificationService.addNotifHtmlForAdd(com.getPartnerMZ(), "/pages/pagesMZ/MZ.zul", mz, mz.getStatus());
                }
                break;
            case 4 :
                for (ComandMZ com : mz.getComandMZ())
                {
                    NotificationService.addNotifHtmlForAdd(com.getPartnerMZ(), "/pages/pagesMZ/MZ.zul", mz, mz.getStatus());
                }
        }
        Executions.sendRedirect("");

//        compareAndSaveCorrection();
    }

    @Command
    public void onClickBtn3(@ContextParam(ContextType.COMPONENT) Component comp)
    {
        if (!checkAllFields()) return;

//        mz.setStatus(2);
//        onClickBtn1(comp);


		Map arg = new HashMap<String, Object>(){
			{
				put("mz", mz);
                put("clickBut", new ButtonClickInterf() {
                    @Override
                    public void click(Textbox textbox) {
                        NotificationService.revertNotifHtmlForAdd(mz.getLeader(), "/pages/pagesMZ/MZ.zul", mz, mz.getStatus(), textbox.getText());
                        mz.setStatus(2);

                        try {
                            new WorkWithMZ().changeEntity(mz);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Executions.sendRedirect("");
                    }
                });

			}
		};

        final Window wind1 = (Window) Executions.createComponents("/pages/window/inputTextDialog.zul", null,arg);
		wind1.setTitle("�������� ������� ������");
        wind1.addEventListener("onClose", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
//                String text = ((Textbox) wind1.getChildren().get(0)).getText();
//
//                NotificationService.revertNotifHtmlForAdd(mz.getLeader(), "/pages/pagesMZ/MZ.zul", mz, mz.getStatus(), text);
            }
        });

        wind1.doModal();

//        compareAndSaveCorrection();
    }

    @Command
    public void onClickBtn4(@ContextParam(ContextType.COMPONENT) Component comp)
    {
        Map arg = new HashMap<String, Object>(){
            {
                put("mz", mz);
                put("clickBut", new ButtonClickInterf() {
                    @Override
                    public void click(Textbox textbox) {
                        NotificationService.addNotifHtmlForRevertComandWork(mz.getLeader(), "/pages/pagesMZ/MZ.zul", mz, mz.getStatus(), textbox.getText());
                        Executions.sendRedirect("");
                    }
                });
            }
        };

        final Window wind1 = (Window) Executions.createComponents("/pages/window/inputTextDialog.zul", null,arg);
        wind1.setTitle("�������� ����������");
        wind1.addEventListener("onClose", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
//                String text = ((Textbox) wind1.getChildren().get(0)).getText();
//
//                NotificationService.addNotifHtmlForRevertComandWork(mz.getLeader(), "/pages/pagesMZ/MZ.zul", mz, mz.getStatus(), text);
            }
        });
        wind1.doModal();
    }

    @Command
    public void onClickBtn5(@ContextParam(ContextType.COMPONENT) Component comp)
    {
        mz.setStatus(5);

        try {
            new WorkWithMZ().changeEntity(mz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Executions.sendRedirect("");
    }

    @Command
    public void onClickBtn6(@ContextParam(ContextType.COMPONENT) Component comp)
    {
        Map arg = new HashMap<String, Object>(){
            {
                put("mz", mz);
                put("clickBut", new ButtonClickInterf() {
                    @Override
                    public void click(Textbox textbox) {
                        NotificationService.revertEndNotifHtmlForRevertComandWork(((UsersMunMan)new WorkWithUser().findAndGetAllRow("login", (String) Sessions.getCurrent().getAttribute("login")).get(0)).getPartnerMZ(),
                                 "/pages/pagesMZ/MZ.zul", mz, mz.getStatus(), textbox.getText());
                        Executions.sendRedirect("");
                    }
                });
            }
        };

        final Window wind1 = (Window) Executions.createComponents("/pages/window/inputTextDialog.zul", null,arg);
        wind1.setTitle("�������� ������� ��������");
        wind1.addEventListener("onClose", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
//                        String text = ((Textbox) wind1.getChildren().get(0)).getText();
//
//                        NotificationService.revertNotifHtmlForRevertComandWork(com.getPartnerMZ(), "/pages/pagesMZ/MZ.zul", mz, mz.getStatus(), text);
            }
        });

        wind1.doModal();
    }

    @Command
    public void onAddNewParamByList(@ContextParam(ContextType.COMPONENT) Component comp)
    {
        final List<Parametrs> listParam = new ArrayList<Parametrs>();

        for (Object value : (Object []) new WorkWithParametrs().getListRows().toArray())
        {
            for (int i=0; i<((Object [])valuesParametrForMZ).length; i++)
                if (!((Parametrs)value).equals(((ValuesParametrForMZ)((Object [])valuesParametrForMZ)[i]).getParametr()))
                {
                    listParam.add((Parametrs)value);
                }
        }

        Map arg = new HashMap<String, Object>(){
            {
                put("obj", listParam.toArray());
                put("getter", GetMZ.this);
                put("type", "1");
            }
        };
        wind = (Window) Executions.createComponents("/pages/pagesMZ/selectParamsWindow.zul", null, arg );

        wind.doModal();
    }

    @Command
    public void onAddOldParamByList(@ContextParam(ContextType.COMPONENT) Component comp)
    {
        final List<Parametrs> listParam = new ArrayList<Parametrs>();

        for (Object value : (Object []) new WorkWithParametrs().getListRows().toArray())
        {
            for (int i=0; i<((Object [])valuesParametrForMZ).length; i++)
                if (((Parametrs)value).equals(((ValuesParametrForMZ)((Object [])valuesParametrForMZ)[i]).getParametr()))
                {
                    listParam.add((Parametrs)value);
                }
        }

        Map arg = new HashMap<String, Object>(){
            {
                put("obj", listParam.toArray());
                put("getter", GetMZ.this);
                put("type", "1");
            }
        };
        wind = (Window) Executions.createComponents("/pages/pagesMZ/selectParamsWindow.zul", null, arg );

        wind.doModal();
    }

    @Command
    public void onAddNewLimitByList(@ContextParam(ContextType.COMPONENT) Component comp)
    {
        final List<Parametrs> listParam = new ArrayList<Parametrs>();

        for (Object value : (Object []) new WorkWithParametrs().getListRows().toArray())
        {
            for (int i=0; i<((Object [])limitsMZ).length; i++)
                if (!((Parametrs)value).equals(((LimitsMZ) ((Object[]) limitsMZ)[i]).getParametr()))
                {
                    listParam.add((Parametrs)value);
                }
        }

        Map arg = new HashMap<String, Object>(){
            {
                put("obj", listParam.toArray());
                put("getter", GetMZ.this);
                put("type", "2");
            }
        };
        wind = (Window) Executions.createComponents("/pages/pagesMZ/selectParamsWindow.zul", null, arg );

        wind.doModal();
    }

    @Command
    public void onAddOldLimitByList(@ContextParam(ContextType.COMPONENT) Component comp)
    {
        final List<Parametrs> listParam = new ArrayList<Parametrs>();

        for (Object value : (Object []) new WorkWithParametrs().getListRows().toArray())
        {
            for (int i=0; i<((Object [])limitsMZ).length; i++)
                if (((Parametrs)value).equals(((LimitsMZ) ((Object[]) limitsMZ)[i]).getParametr()))
                {
                    listParam.add((Parametrs)value);
                }
        }

        Map arg = new HashMap<String, Object>(){
            {
                put("obj", listParam.toArray());
                put("getter", GetMZ.this);
                put("type", "2");
            }
        };
        wind = (Window) Executions.createComponents("/pages/pagesMZ/selectParamsWindow.zul", null, arg );

        wind.doModal();
    }

    @Command
    public void onAddNewParam(@ContextParam(ContextType.COMPONENT) Component comp)
    {
        final Window wind1 = (Window) Executions.createComponents("/pages/window/windowAddParam.zul", null, null);

        wind1.addEventListener("onClose", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                String nameParam = ((Textbox)((Row)((Grid)wind1.getChildren().get(0)).getRows().getChildren().get(0)).getChildren().get(1)).getValue();
                int numTypeParam = ((Combobox)((Row)((Grid)wind1.getChildren().get(0)).getRows().getChildren().get(1)).getChildren().get(1)).getSelectedIndex();

                Parametrs parametrs = new Parametrs();
                parametrs.setName(nameParam);

                if (numTypeParam != -1)
                    if (numTypeParam == 0)
                        parametrs.setType('s');
                    else
                        parametrs.setType('d');

                new WorkWithParametrs().addEntity(parametrs);
            }
        });

        wind1.doModal();
    }

    @Command
    public void onShowChartParam()
    {
        Map arg = new HashMap<String, Object>(){
            {
                put("nameMz", mz.getName());
                put("model", getModelChartParam());
            }
        };

        final Window wind1 = (Window) Executions.createComponents("/pages/window/chartWindow.zul", null,arg);

        wind1.doModal();
    }

    public void saveParam(int index)
    {
		if (index!=-1)
		{
			ObjectListBox listBox = (ObjectListBox) wind.getChildren().get(0).getChildren().get(0);
			Object obj = listBox.getObjs()[index];

			Object [] objs = new Object [((Object [])valuesParametrForMZ).length+1];
			Object [] objVal = (Object [])valuesParametrForMZ;

			for (int i=0; i<objVal.length; i++)
			{
				objs[i] = objVal[i];
			}


			objs[((Object [])valuesParametrForMZ).length] = new WorkWithValuesParametrForMZ().getEmptyEntity();
            ((ValuesParametrForMZ)(objs[((Object [])valuesParametrForMZ).length])).setParametr((Parametrs)obj);
            ((ValuesParametrForMZ)(objs[((Object [])valuesParametrForMZ).length])).setIdParametr(((Parametrs)obj).getId());
            ((ValuesParametrForMZ)(objs[((Object [])valuesParametrForMZ).length])).setValue("");
            ((ValuesParametrForMZ)(objs[((Object [])valuesParametrForMZ).length])).setDateRecValue(Calendar.getInstance().getTime());

            setValuesParametrForMZ(objs);

            setValModelList(new ListModelList<Object>((Object []) valuesParametrForMZ));
            valuesParametrForMZ1.setModel(valModelList);

            try
            {
                mz.getValuesParametrForMZ().add(((ValuesParametrForMZ [])objs)[objs.length-1]);
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
		}

        wind.onClose();
    }

    @Override
    public void saveParam2(int index)
    {
        if (index!=-1)
        {
            ObjectListBox listBox = (ObjectListBox) wind.getChildren().get(0).getChildren().get(0);
            Object obj = listBox.getObjs()[index];

            Object [] objs = new Object [((Object [])limitsMZ).length+1];
            Object [] objVal = (Object [])limitsMZ;

            for (int i=0; i<objVal.length; i++)
            {
                objs[i] = objVal[i];
            }


            objs[((Object [])limitsMZ).length] = new WorkWithLimitsMz().getEmptyEntity();
            ((LimitsMZ)(objs[((Object [])limitsMZ).length])).setParametr((Parametrs) obj);
            ((LimitsMZ)(objs[((Object [])limitsMZ).length])).setIdParametr(((Parametrs) obj).getId());
            ((LimitsMZ)(objs[((Object [])limitsMZ).length])).setValue("");

            setLimitsMZ(objs);
            setLimitModelList(new ListModelList<Object>(limitsMZ));

            limitsMZ1.setModel(limitModelList);

            try
            {
                mz.getLimitsMZ().add(((LimitsMZ [])objs)[objs.length-1]);
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }

        wind.onClose();
    }

    public void cancelParam()
	{
        wind.onClose();
	}

    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
    }

    @Command
    public void showReport(@ContextParam(ContextType.COMPONENT) Component comp)
    {
//        Window win = (Window) Executions.createComponents("/report/report1.zul", null, null);

        Map<String, Object> params = new HashMap<String, Object>();
        // Add parameters used in this report
        params.put("reportTitle", "������������� �������");
        params.put("title2", mz.getName());
        params.put("hand1", "My Food List2");
        params.put("MZSource", new MZDataSource(new WorkWithMZ().getListRows()));

        List<ComandMZ> comList = new ArrayList<ComandMZ>();

        for (ComandMZ com : mz.getComandMZ())
            comList.add(com);

        params.put("comandMZ", new ComandMZDataSource(comList));

        List<LimitsMZ> limList = new ArrayList<LimitsMZ>();

        for (LimitsMZ com : mz.getLimitsMZ())
            limList.add(com);

        params.put("limitsMZ", new LimitsMZDataSource(limList));
//        params.put("seafoodDataSource", new DataSource(seafoods));

        List<ValuesParametrForMZ> valList = new ArrayList<ValuesParametrForMZ>();

        for (ValuesParametrForMZ com : mz.getValuesParametrForMZ())
            valList.add(com);

        params.put("valuesParamMZ", new ValuesMZDataSource(valList));

//        final Jasperreport report = (Jasperreport) win.getFellow("report");
        report.setType("rtf");
        report.setSrc("/jaspreport/report1.jasper");
        report.setParameters(params);

//        org.zkoss.zk.ui.util.Clients.showBusy(report, "���� ������������� ������ ...");

        String h = "onReadyStateChange";

        report.addEventListener(h,new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                org.zkoss.zk.ui.util.Clients.clearBusy(report);
            }
        });
    }

    @Command
    public void showTimeline(@ContextParam(ContextType.COMPONENT) Component comp)
    {
        Executions.sendRedirect("/pages/pagesMZ/timeline.zul?id="+mz.getId());
    }
    
    public void fileUpload()
    {
        org.zkoss.zhtml.Fileupload fileupload = new org.zkoss.zhtml.Fileupload();
        fileupload.get(new EventListener<UploadEvent>() {
            @Override
            public void onEvent(UploadEvent uploadEvent) throws Exception {
                Clients.showBusy("���� ����� �����...");

                Media media = uploadEvent.getMedias()[0];

                FileController.saveMediaToFile(media);

                FileMZ fileMZ = new FileMZ();
                fileMZ.setIdMz(mz.getId());
                fileMZ.setFile(media.getName());

                mz.getFileMZs().add(fileMZ);
                fileModelList.add(fileMZ);

                fileList.setModel(fileModelList);

                Clients.clearBusy();
            }
        });
    }

    public ListModelList getEndCommandMZ()
    {
        ListModelList modelList = new ListModelList();

        for (ComandMZ com : mz.getComandMZ())
        {
            if (!com.isWork())
                modelList.add(com);
        }

        return modelList;
    }

    public void revertPartnerComandInWork(Object obj)
    {
        final ComandMZ com = (ComandMZ) obj;

        for (ComandMZ comandMZ2 : mz.getComandMZ())
        {
            if (comandMZ2.equals(com))
            {
                com.setWork(false);
                comandMZ2.setWork(false);

                try {
                    new WorkWithCommandMz().changeEntity(comandMZ2);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Map arg = new HashMap<String, Object>(){
                    {
                        put("mz", mz);
                        put("clickBut", new ButtonClickInterf() {
                            @Override
                            public void click(Textbox textbox) {
                                NotificationService.revertNotifHtmlForRevertComandWork(com.getPartnerMZ(), "/pages/pagesMZ/MZ.zul", mz, mz.getStatus(), textbox.getText());
                            }
                        });
                    }
                };

                final Window wind1 = (Window) Executions.createComponents("/pages/window/inputTextDialog.zul", null,arg);
                wind1.setTitle("�������� ������� ��������");
                wind1.addEventListener("onClose", new EventListener<Event>() {
                    @Override
                    public void onEvent(Event event) throws Exception {
//                        String text = ((Textbox) wind1.getChildren().get(0)).getText();
//
//                        NotificationService.revertNotifHtmlForRevertComandWork(com.getPartnerMZ(), "/pages/pagesMZ/MZ.zul", mz, mz.getStatus(), text);
                    }
                });

                wind1.doModal();
            }
        }
    }
}
