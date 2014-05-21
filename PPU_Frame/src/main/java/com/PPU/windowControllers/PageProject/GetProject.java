package com.PPU.windowControllers.PageProject;

import com.PPU.DB.tables.*;
import com.PPU.DB.workLogic.*;
import com.PPU.composite.DualObjectListBox;
import com.PPU.composite.ObjectListBox;
import com.PPU.funcControl.NotificationService;
import com.PPU.windowControllers.ButtonClickInterf;
import com.PPU.windowControllers.FileController;
import com.PPU.windowControllers.GetListParam;
import com.PPU.windowControllers.report.ComandProjectDataSource;
import com.PPU.windowControllers.report.LimitsProjectDataSource;
import com.PPU.windowControllers.report.ProjectDataSource;
import com.PPU.windowControllers.report.ValuesProjectDataSource;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.chart.model.CategoryModel;
import org.zkoss.chart.model.DefaultCategoryModel;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkex.zul.Jasperreport;
import org.zkoss.zul.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * Created by Alex on 05.05.2014.
 */
public class GetProject implements GetListParam {

    private int id;

	Project project;
    Project oldProject;

    String projectName;

    String program;

    String name;
    Date startDate;
    Date expirationDate;
    String description;
    float budget;

    Object comandProject;
    Object[] program1;
    WorkWithProgramCommerc workerProgr = new WorkWithProgramCommerc();

    String typeServiceProject;

    List listTypeService;

    Object [] limitsProject;
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

    Object valuesParametrForProject;
    Object resourcesProject;
    Object correctionsProject;

    ListModelList<Object> limitModelList;
    ListModelList<Object> valModelList;
    ListModelList<Object> fileModelList;

	Window wind = new Window();

    private int level;

    @Wire
    private Grid valuesParametrForProject1;

    @Wire
    private Grid limitsProject1;

    @Wire
    private ObjectListBox leaderProject1;

    @Wire
    private DualObjectListBox comandProject1;

    @Wire
    private ObjectListBox program2;

    @Wire
    private Combobox typeService1;

    @Wire
    private DualObjectListBox resourcesProject1;

    @Wire
    private Grid fileList;

    @Wire
    private Jasperreport report;

    public GetProject()
    {

    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public Object getComandProject() {
        return comandProject;
    }

    public void setComandProject(Object comandProject) {
        this.comandProject = comandProject;
    }

    public Object[] getProgram1() {
        return program1;
    }

    public void setProgram1(Object[] program1) {
        this.program1 = program1;
    }

    public WorkWithProgramCommerc getWorkerProgr() {
        return workerProgr;
    }

    public void setWorkerProgr(WorkWithProgramCommerc workerProgr) {
        this.workerProgr = workerProgr;
    }

    public String getTypeServiceProject() {
        return typeServiceProject;
    }

    public void setTypeServiceProject(String typeServiceProject) {
        this.typeServiceProject = typeServiceProject;
    }

    public List getListTypeService() {
        return listTypeService;
    }

    public void setListTypeService(List listTypeService) {
        this.listTypeService = listTypeService;
    }

    public Object[] getLimitsProject() {
        return limitsProject;
    }

    public void setLimitsProject(Object[] limitsProject) {
        this.limitsProject = limitsProject;
    }

    public Object[] getParametr() {
        return parametr;
    }

    public void setParametr(Object[] parametr) {
        this.parametr = parametr;
    }

    public Object getValuesParametrForProject() {
        return valuesParametrForProject;
    }

    public void setValuesParametrForProject(Object valuesParametrForProject) {
        this.valuesParametrForProject = valuesParametrForProject;
    }

    public Object getResourcesProject() {
        return resourcesProject;
    }

    public void setResourcesProject(Object resourcesProject) {
        this.resourcesProject = resourcesProject;
    }

    public Object getCorrectionsProject() {
        return correctionsProject;
    }

    public void setCorrectionsProject(Object correctionsProject) {
        this.correctionsProject = correctionsProject;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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
            if (((ValuesParametrForProject) val).getParametr().getType() == 'd')
                numMas[i++] = ((ValuesParametrForProject) val).getId();

        Arrays.sort(numMas);

        for (Integer intObj : numMas)
            for (Object val : valModelList)
            if (((ValuesParametrForProject) val).getId() == intObj)
                if (((ValuesParametrForProject) val).getParametr().getType() == 'd')
                {
                    model.setValue(((ValuesParametrForProject) val).getParametr().getName(), new SimpleDateFormat("dd.MM.yyyy").format(((ValuesParametrForProject) val).getDateRecValue()), new Float(((ValuesParametrForProject) val).getValue()));
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
        Project Project;

        if (id!=0)
		{
            Project = ((com.PPU.DB.tables.Project) new WorkWithProject().getEntity(id));
			oldProject = ((com.PPU.DB.tables.Project) new WorkWithProject().getEntity(id));
		}
        else
		{
            Project = ((com.PPU.DB.tables.Project) new WorkWithProject().getEmptyEntity());
			oldProject = ((com.PPU.DB.tables.Project) new WorkWithProject().getEmptyEntity());
		}

		project = Project;

        if (id!=0)
        {
            projectName = "Проект: " + Project.getName();
            program = "Программа: " + Project.getProgram().getName();

            name = Project.getName();
            startDate = Project.getStartDate();
            expirationDate = Project.getExpirationDate();
            description = Project.getDescription();
            budget = Project.getBudget();

            providers = new Object[project.getResourcesProject().size()];

            int a = 0;
            for (ResourcesProject r : project.getResourcesProject())
                providers[a++] = r.getProviders();

            programs = project.getProgram().getPartnerCommercialMan().getProgramCommercs().toArray();

            allLeader = new WorkWithPartnerCommerc().getListRows().toArray();
            leaders = project.getLeader();

            comandProject = (Object) Project.getComandProject().toArray();
            program1 = new Object[1];
            program1[0] = (Object) Project.getProgram();

//            typeServiceProject = (String) Project.getTypeServiceProject().getName();

//            listTypeService = new WorkWithTypeServiceProject().getListRows();

            limitsProject =  Project.getLimitsProject().toArray();

            List parametrsList = new ArrayList();

            for (Object l : limitsProject)
                parametrsList.add(((LimitsProject)l).getParametr());

            parametr = parametrsList.toArray();
			valuesParametrForProject = (Object) Project.getValuesParametrForProject().toArray();

			List val = new ArrayList();

			for (Object v : (Object []) valuesParametrForProject)
				if (((ValuesParametrForProject)v).getParametr().getId()!=1)
					val.add(v);
			valModelList = new ListModelList<Object>((Object []) val.toArray());

            resourcesProject = (Object) Project.getResourcesProject().toArray();
            correctionsProject = (Object) Project.getCorrectionsProject().toArray();

			val = new ArrayList();
			for (Object v : limitsProject)
				if (((LimitsProject)v).getParametr().getId()!=1)
					val.add(v);

            limitModelList = new ListModelList<Object>(val.toArray());
            fileModelList = new ListModelList<Object>(project.getFileProjects());

            List user = new WorkWithUser().findAndGetAllRow("login", (String) Sessions.getCurrent().getAttribute("login"));
            setLevel(new WorkWithProject().getUserRole(project, (UsersComMan) user.get(0)));
//            setLevel(4);

            String levelName = new String();
            namePrevisLevel = "";

            switch (project.getStatus()+1)
            {
                case 1 : levelName = "Создан";
                    nameLevel = "Статус проекта: Создается";
                    break;
                case 2 : levelName = "Планирование";
                    nameLevel = "Статус проекта: Создан";
                    break;
                case 3 : levelName = "На согласовании";
                    nameLevel = "Статус проекта: Планирование";
                    break;
                case 4 : levelName = "Выполняется";
                    nameLevel = "Статус проекта: На согласовании";
                    namePrevisLevel = "Откатить на статус \"Планирование\"";
                    break;
                case 5 : levelName = "";
                    namePrevisLevel = "Откатить на статус \"Планирование\"";
                    nameLevel = "Статус проекта: Выполняется";
                    break;
            }

            nameNextLevel = "";
            if (!levelName.equals(""))
                nameNextLevel = "Сохранить и перевести на статус \"" + levelName + "\"";

            for (NotificationCom not : ((UsersComMan)user.get(0)).getPartnerProject().getNotificationComs())
            {
                Pattern pattern = Pattern.compile("\\Q"+Executions.getCurrent().getDesktop().getRequestPath()+"?id="+Executions.getCurrent().getParameter("id")+"\\E");

                if (pattern.matcher(not.getText()).find())
                {
                    not.setRead(true);

                    try {
                        new WorkWithNotificationCom().changeEntity(not);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            statusName = "";

            int levelUser = new WorkWithProject().getUserRole(project, ((UsersComMan)user.get(0)));
            if (levelUser != 0)
                if (levelUser == 1)
                    statusName = "Вы являетесь заказчиком";
                else
                if (levelUser == 2)
                    statusName = "Вы являетесь руководителем";
                else
                if (levelUser == 3)
                    statusName = "Вы являетесь заказчиком и руководителем";
                else
                if (levelUser == 4)
                    statusName = "Вы являетесь исполнителем";
                else
                if (levelUser == 6)
                    statusName = "Вы являетесь заказчиком, руководиетелм и исполнителем";
                else
                    statusName = "Вы не работате по этому проекту";

            if (getEndCommandProject().size() != 0)
                notWorkString = "Имеются исполнители, закончившие работу";
        }
        else
        {
            projectName = "Создание проекта";
            program = "";

            name = "";
            startDate = Calendar.getInstance().getTime();
            expirationDate = new Date();
            description = "";
            budget = 0;

            project.setName(name);
            project.setStartDate(startDate);
            project.setExpirationDate(expirationDate);
            project.setDescription(description);
            project.setBudget(budget);

            providers = new Object[]{new Providers()};
            programs = ((UsersComMan)new WorkWithUser().findAndGetAllRow("login", (String) Sessions.getCurrent().getAttribute("login")).get(0)).getPartnerProject().getProgramCommercs().toArray();

            allLeader = new WorkWithPartnerCommerc().getListRows().toArray();
            leaders = new PartnerCommercialMan();

            comandProject = (Object) new Object []{new ComandProject()};
            program1 = new Object[1];

            String progr = Executions.getCurrent().getParameter("progr");
            if (progr == null)
                program1[0] = new Object();
            else {
                program1[0] = new WorkWithProgramCommerc().getEntity(new Integer(progr));

                program = "Программа: "+((ProgramCommerc) program1[0]).getName();
            }

            typeServiceProject = "";

//            listTypeService = new WorkWithTypeServiceProject().getListRows();

            limitsProject =  new Object [0];

            List parametrsList = new ArrayList();

            for (Object l : limitsProject)
                parametrsList.add(((LimitsProject)l).getParametr());

            parametr = parametrsList.toArray();

            valuesParametrForProject = (Object) new Object [0];
            resourcesProject = (Object) new Object []{new ResourcesProject()};
            correctionsProject = (Object) new Object [0];

            limitModelList = new ListModelList<Object>(limitsProject);
            fileModelList = new ListModelList<Object>(new ArrayList<FileProject>());
            valModelList = new ListModelList<Object>((Object []) valuesParametrForProject);

            namePrevisLevel = "";
            nameNextLevel = "Сохранить и перевести на статус \"Создан\"";
            statusName = "";
        }



//        setLevel(3);
    }

    private boolean checkAllFields()
    {
        boolean success = true;

        String name = project.getName();
        Date startDate = project.getStartDate();
        Date expirationDate = project.getExpirationDate();
        String description = project.getDescription();
        float budget = project.getBudget();

        if (name.equals(""))
        {
            Messagebox.show("Не введено название!", "Error", Messagebox.OK, Messagebox.ERROR);
            success = false;
        }

        if (description.equals(""))
        {
            Messagebox.show("Не введено описание!", "Error", Messagebox.OK, Messagebox.ERROR);
            success = false;
        }

        if (budget == 0)
        {
            Messagebox.show("Бюджет не должен быть равен 0!", "Error", Messagebox.OK, Messagebox.ERROR);
            success = false;
        }

        if (leaderProject1.getSelectedIndex() == -1)
        {
            Messagebox.show("Не выбран руководитель проекта!", "Error", Messagebox.OK, Messagebox.ERROR);
            success = false;
        }

        if (comandProject1.getRightListbox().getObjs().length == 0)
        {
            Messagebox.show("Не выбрана комманда проекта!", "Error", Messagebox.OK, Messagebox.ERROR);
            success = false;
        }

//        if (typeService1.getSelectedIndex() == -1)
//        {
//            Messagebox.show("не выбаран тип задания!", "Error", Messagebox.OK, Messagebox.ERROR);
//            success = false;
//        }

        if (resourcesProject1.getRightListbox().getObjs().length == 0)
        {
            Messagebox.show("Не выбраны ресурся!", "Error", Messagebox.OK, Messagebox.ERROR);
            success = false;
        }

        for (Object obj : new WorkWithParametrs().getListRows())
            if (((Parametrs)obj).getType() == 'd')
            {
                Float res = 0f;
                for (Object o : (Object[]) valuesParametrForProject)
                {
                    if (((ValuesParametrForProject)o).getParametr().equals(obj))
                    {
                        res+=new Float(((ValuesParametrForProject)o).getValue());
                    }
                }

                for (Object lim : (Object[]) limitsProject)
                {
                    if (((LimitsProject)lim).getParametr().equals(obj))
                    {
                        Float limVal = new Float(((LimitsProject)lim).getValue());
                        if (limVal<=res)
                        {
                            Messagebox.show("Сумма показателей параметра \""+((Parametrs) obj).getName()+"\" превышает ограничение ("+limVal+")", "Error", Messagebox.OK, Messagebox.ERROR);
                            break;
                        }
                    }
                }
            }

        return success;
    }

	private void compareAndSaveCorrection()
	{
		WorkWithCorrectionProject worker = new WorkWithCorrectionProject();

		if (oldProject.getStatus() != project.getStatus())
		{
			try {
				CorrectionsProject val = new CorrectionsProject();
				val.setIdParametr(((Parametrs) new WorkWithParametrs().getEntity(1)).getId());
				String levelName = "";

				switch (project.getStatus())
				{
					case 1 : levelName = "Создается";
						break;
					case 2 : levelName = "Создан";
						break;
					case 3 : levelName = "Планирование";
						break;
					case 4 : levelName = "На согласовании";
						break;
					case 5 : levelName = "Выполняется";
						break;
				}

				val.setValueAfter(levelName);
				val.setLimits(false);
                val.setIdProject(project.getId());

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

			for (Object o : limitsProject)
            if (((LimitsProject)listO).getId() == (((LimitsProject)o).getId())) {
                isFind = true;
                if (((LimitsProject) listO).getParametr().equals((((LimitsProject) o)).getParametr())) {
                    if (!((LimitsProject) listO).getValue().equals(((LimitsProject) o).getValue())) {
                        try {
                            CorrectionsProject val = new CorrectionsProject();
                            val.setIdParametr(((LimitsProject) listO).getParametr().getId());
                            val.setValueBefore(((LimitsProject) listO).getValue());
                            val.setIdProject(project.getId());
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
					CorrectionsProject val = new CorrectionsProject();
					val.setIdParametr(((LimitsProject) listO).getParametr().getId());
					val.setValueBefore(((LimitsProject)listO).getValue());
                    val.setIdProject(project.getId());
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

            for (Object o : (Object [])valuesParametrForProject)

                if (((ValuesParametrForProject)listO).getId() == (((ValuesParametrForProject)o).getId())) {
                    isFind = true;
                    if (((ValuesParametrForProject) listO).getParametr().equals((((ValuesParametrForProject) o)).getParametr())) {
                        if (!((ValuesParametrForProject) listO).getValue().equals(((ValuesParametrForProject) o).getValue())) {
                            try {
                                CorrectionsProject val = new CorrectionsProject();
                                val.setIdParametr(((ValuesParametrForProject) listO).getParametr().getId());
                                val.setValueBefore(((ValuesParametrForProject) listO).getValue());
                                val.setIdProject(project.getId());
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
                    CorrectionsProject val = new CorrectionsProject();
                    val.setIdParametr(((ValuesParametrForProject) listO).getParametr().getId());
                    val.setValueBefore(((ValuesParametrForProject)listO).getValue());
                    val.setIdProject(project.getId());
					val.setLimits(false);

                    worker.addEntity(val);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

		}
	}

    @Command
    public void onClickBtn1(@ContextParam(ContextType.COMPONENT) Component comp)
    {
        if (!checkAllFields()) return;

        Clients.showBusy("Идет сохранение...");

        if (level == 1 || level == 0 || level == 5 || level == 6)
        {
            PartnerCommercialMan leaderLocal = (PartnerCommercialMan) leaderProject1.getObjs()[leaderProject1.getSelectedIndex()];
            project.setLeader(leaderLocal);
        }

        Object[] objs = comandProject1.getRightListbox().getObjs();
        ComandProject [] comLocal = new ComandProject [objs.length] ;

        for (int i=0; i<objs.length; i++)
            comLocal[i] = (ComandProject) objs[i];

        for (ComandProject com : oldProject.getComandProject())
        {
            boolean isFind = false;

            for (ComandProject com2 : comLocal)
                if (com2.equals(com))
                {
                    isFind = true;
                    break;
                }

            if (!isFind)
                try {
                    new WorkWithCommandMz().deleteEntity(com);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

        project.setComandProject(new LinkedHashSet<ComandProject>(Arrays.asList(comLocal)));

        project.setValuesParametrForProject(new LinkedHashSet<ValuesParametrForProject>((List) valModelList.getInnerList()));

        if (program2.getSelectedIndex()!=-1 && (level==1 || level == 0 || level == 5 || level == 6))
            project.setProgram((ProgramCommerc) program2.getObjs()[program2.getSelectedIndex()]);

        project.setLimitsProject(new LinkedHashSet<LimitsProject>((List) limitModelList.getInnerList()));

//        if (typeService1.getSelectedIndex()!=-1)
//        {
//            TypeServiceProject typeServiceProjectLocal = (TypeServiceProject) new WorkWithTypeServiceProject().getListRows().get(typeService1.getSelectedIndex());
//            project.setTypeServiceProject(typeServiceProjectLocal);
//        }

        objs = resourcesProject1.getRightListbox().getObjs();
        ResourcesProject [] res = new ResourcesProject[objs.length];

        for (int i=0; i<objs.length; i++)
        {
            res[i] = new ResourcesProject();
            List list = new WorkWithResourcesProject().findAndGetAllRow("id_project;id_provider_resources", ""+project.getId()+";"+((Providers) objs[i]).getId());

            if (list.size() != 0)
            {
                res[i].setId(((ResourcesProject)list.get(0)).getId());
            }

            res[i].setProviders((Providers) objs[i]);
            res[i].setIdProviderResources(((Providers) objs[i]).getId());
            res[i].setIdProject(project.getId());
        }

        for (ResourcesProject com : oldProject.getResourcesProject())
        {
            boolean isFind = false;

            for (ResourcesProject com2 : res)
                if (com2.equals(com))
                {
                    isFind = true;
                    break;
                }

            if (!isFind)
                try {
                    new WorkWithResourcesProject().deleteEntity(com);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

        project.setResourcesProject(new LinkedHashSet<ResourcesProject>(Arrays.asList(res)));

        if (id == 0)
            project.setStatus(1);

        if (id !=0)
        {
            try {
                new WorkWithProject().changeEntity(project);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            try {
                new WorkWithProject().addEntity(project);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

		compareAndSaveCorrection();

        Clients.clearBusy();
    }

    @Command
    public void onClickBtn2(@ContextParam(ContextType.COMPONENT) Component comp)
    {
        if (!checkAllFields()) return;

        project.setStatus(project.getStatus() + 1);
        onClickBtn1(comp);
        PartnerCommercialMan partnersProject = new PartnerCommercialMan();

        switch (project.getStatus())
        {
            case 1 :
                NotificationService.addNotifHtmlForAddCom(project.getProgram().getPartnerCommercialMan(), "/pages/pagesProject/Project.zul", project, project.getStatus());
                break;
            case 2 :
                NotificationService.addNotifHtmlForAddCom(project.getLeader(), "/pages/pagesProject/Project.zul", project, project.getStatus());
                break;
            case 3 :
                for (ComandProject com : project.getComandProject())
                {
                    NotificationService.addNotifHtmlForAddCom(com.getPartnerProject(), "/pages/pagesProject/Project.zul", project, project.getStatus());
                }
                break;
            case 4 :
                for (ComandProject com : project.getComandProject())
                {
                    NotificationService.addNotifHtmlForAddCom(com.getPartnerProject(), "/pages/pagesProject/Project.zul", project, project.getStatus());
                }
        }

//        compareAndSaveCorrection();
    }

    @Command
    public void onClickBtn3(@ContextParam(ContextType.COMPONENT) Component comp)
    {
        if (!checkAllFields()) return;

        onClickBtn1(comp);
        project.setStatus(2);

		Map arg = new HashMap<String, Object>(){
			{
				put("project", project);
                put("clickBut", new ButtonClickInterf() {
                    @Override
                    public void click(Textbox textbox) {
                        NotificationService.revertNotifHtmlForAddCom(project.getLeader(), "/pages/pagesProject/Project.zul", project, project.getStatus(), textbox.getText());
                    }
                });
			}
		};

        final Window wind1 = (Window) Executions.createComponents("/pages/window/inputTextDialog.zul", null,arg);
		wind1.setTitle("Нипишите причину отказа");
        wind1.addEventListener("onClose", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
//                String text = ((Textbox) wind1.getChildren().get(0)).getText();
//
//                NotificationService.revertNotifHtmlForAddCom(project.getLeader(), "/pages/pagesProject/Project.zul", project, project.getStatus(), text);
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
                put("project", project);
                put("clickBut", new ButtonClickInterf() {
                    @Override
                    public void click(Textbox textbox) {
                        NotificationService.addNotifHtmlForRevertComandWorkCom(project.getLeader(), "/pages/pagesProject/Project.zul", project, project.getStatus(), textbox.getText());
                    }
                });
            }
        };

        final Window wind1 = (Window) Executions.createComponents("/pages/window/inputTextDialog.zul", null,arg);
        wind1.setTitle("Прчичина завершения");
        wind1.addEventListener("onClose", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
//                String text = ((Textbox) wind1.getChildren().get(0)).getText();
//
//                NotificationService.addNotifHtmlForRevertComandWorkCom(project.getLeader(), "/pages/pagesProject/Project.zul", project, project.getStatus(), text);
            }
        });
        wind1.doModal();
    }

    @Command
    public void onClickBtn5(@ContextParam(ContextType.COMPONENT) Component comp)
    {

    }

    @Command
    public void onAddNewParamByList(@ContextParam(ContextType.COMPONENT) Component comp)
    {
        final List<Parametrs> listParam = new ArrayList<Parametrs>();

        for (Object value : (Object []) new WorkWithParametrs().getListRows().toArray())
        {
            for (int i=0; i<((Object [])valuesParametrForProject).length; i++)
                if (!((Parametrs)value).equals(((ValuesParametrForProject)((Object [])valuesParametrForProject)[i]).getParametr()))
                {
                    listParam.add((Parametrs)value);
                }
        }

        Map arg = new HashMap<String, Object>(){
            {
                put("obj", listParam.toArray());
                put("getter", GetProject.this);
                put("type", "1");
            }
        };
        wind = (Window) Executions.createComponents("/pages/pagesProject/selectParamsWindow.zul", null, arg );

        wind.doModal();
    }

    @Command
    public void onAddOldParamByList(@ContextParam(ContextType.COMPONENT) Component comp)
    {
        final List<Parametrs> listParam = new ArrayList<Parametrs>();

        for (Object value : (Object []) new WorkWithParametrs().getListRows().toArray())
        {
            for (int i=0; i<((Object [])valuesParametrForProject).length; i++)
                if (((Parametrs)value).equals(((ValuesParametrForProject)((Object [])valuesParametrForProject)[i]).getParametr()))
                {
                    listParam.add((Parametrs)value);
                }
        }

        Map arg = new HashMap<String, Object>(){
            {
                put("obj", listParam.toArray());
                put("getter", GetProject.this);
                put("type", "1");
            }
        };
        wind = (Window) Executions.createComponents("/pages/pagesProject/selectParamsWindow.zul", null, arg );

        wind.doModal();
    }

    @Command
    public void onAddNewLimitByList(@ContextParam(ContextType.COMPONENT) Component comp)
    {
        final List<Parametrs> listParam = new ArrayList<Parametrs>();

        for (Object value : (Object []) new WorkWithParametrs().getListRows().toArray())
        {
            for (int i=0; i<((Object [])limitsProject).length; i++)
                if (!((Parametrs)value).equals(((LimitsProject) ((Object[]) limitsProject)[i]).getParametr()))
                {
                    listParam.add((Parametrs)value);
                }
        }

        Map arg = new HashMap<String, Object>(){
            {
                put("obj", listParam.toArray());
                put("getter", GetProject.this);
                put("type", "2");
            }
        };
        wind = (Window) Executions.createComponents("/pages/pagesProject/selectParamsWindow.zul", null, arg );

        wind.doModal();
    }

    @Command
    public void onAddOldLimitByList(@ContextParam(ContextType.COMPONENT) Component comp)
    {
        final List<Parametrs> listParam = new ArrayList<Parametrs>();

        for (Object value : (Object []) new WorkWithParametrs().getListRows().toArray())
        {
            for (int i=0; i<((Object [])limitsProject).length; i++)
                if (((Parametrs)value).equals(((LimitsProject) ((Object[]) limitsProject)[i]).getParametr()))
                {
                    listParam.add((Parametrs)value);
                }
        }

        Map arg = new HashMap<String, Object>(){
            {
                put("obj", listParam.toArray());
                put("getter", GetProject.this);
                put("type", "2");
            }
        };
        wind = (Window) Executions.createComponents("/pages/pagesProject/selectParamsWindow.zul", null, arg );

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
                put("nameMz", project.getName());
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

			Object [] objs = new Object [((Object [])valuesParametrForProject).length+1];
			Object [] objVal = (Object [])valuesParametrForProject;

			for (int i=0; i<objVal.length; i++)
			{
				objs[i] = objVal[i];
			}


			objs[((Object [])valuesParametrForProject).length] = new WorkWithValuesParametrForProject().getEmptyEntity();
            ((ValuesParametrForProject)(objs[((Object [])valuesParametrForProject).length])).setParametr((Parametrs)obj);
            ((ValuesParametrForProject)(objs[((Object [])valuesParametrForProject).length])).setIdParametr(((Parametrs)obj).getId());
            ((ValuesParametrForProject)(objs[((Object [])valuesParametrForProject).length])).setValue("");
            ((ValuesParametrForProject)(objs[((Object [])valuesParametrForProject).length])).setDateRecValue(Calendar.getInstance().getTime());

            setValuesParametrForProject(objs);

            setValModelList(new ListModelList<Object>((Object []) valuesParametrForProject));
            valuesParametrForProject1.setModel(valModelList);

            try
            {
                project.getValuesParametrForProject().add(((ValuesParametrForProject [])objs)[objs.length-1]);
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

            Object [] objs = new Object [((Object [])limitsProject).length+1];
            Object [] objVal = (Object [])limitsProject;

            for (int i=0; i<objVal.length; i++)
            {
                objs[i] = objVal[i];
            }


            objs[((Object [])limitsProject).length] = new WorkWithLimitsMz().getEmptyEntity();
            ((LimitsProject)(objs[((Object [])limitsProject).length])).setParametr((Parametrs) obj);
            ((LimitsMZ)(objs[((Object [])limitsProject).length])).setIdParametr(((Parametrs) obj).getId());
            ((LimitsProject)(objs[((Object [])limitsProject).length])).setValue("");

            setLimitsProject(objs);
            setLimitModelList(new ListModelList<Object>(limitsProject));

            limitsProject1.setModel(limitModelList);

            try
            {
                project.getLimitsProject().add(((LimitsProject [])objs)[objs.length-1]);
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
        params.put("reportTitle", "Проект");
        params.put("title2", project.getName());
        params.put("hand1", "My Food List2");
        params.put("ProjectSource", new ProjectDataSource(new WorkWithProject().getListRows()));

        List<ComandProject> comList = new ArrayList<ComandProject>();

        for (ComandProject com : project.getComandProject())
            comList.add(com);

        params.put("comandProject", new ComandProjectDataSource(comList));

        List<LimitsProject> limList = new ArrayList<LimitsProject>();

        for (LimitsProject com : project.getLimitsProject())
            limList.add(com);

        params.put("limitsProject", new LimitsProjectDataSource(limList));
//        params.put("seafoodDataSource", new DataSource(seafoods));

        List<ValuesParametrForProject> valList = new ArrayList<ValuesParametrForProject>();

        for (ValuesParametrForProject com : project.getValuesParametrForProject())
            valList.add(com);

        params.put("valuesParamProject", new ValuesProjectDataSource(valList));

//        final Jasperreport report = (Jasperreport) win.getFellow("report");
        report.setType("rtf");
        report.setSrc("/jaspreport/report2.jasper");
        report.setParameters(params);

//        org.zkoss.zk.ui.util.Clients.showBusy(report, "Идет генерирование отчета ...");

        String h = "onReadyStateChange";

        report.addEventListener(h,new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                Clients.clearBusy(report);
            }
        });
    }

    @Command
    public void showTimeline(@ContextParam(ContextType.COMPONENT) Component comp)
    {
        Executions.sendRedirect("/pages/pagesProject/timeline.zul?id="+project.getId());
    }
    
    public void fileUpload()
    {
        org.zkoss.zhtml.Fileupload fileupload = new org.zkoss.zhtml.Fileupload();
        fileupload.get(new EventListener<UploadEvent>() {
            @Override
            public void onEvent(UploadEvent uploadEvent) throws Exception {
                Clients.showBusy("Идет прием файла...");

                Media media = uploadEvent.getMedias()[0];

                FileController.saveMediaToFile(media);

                FileProject fileProject = new FileProject();
                fileProject.setIdProject(project.getId());
                fileProject.setFile(media.getName());

                project.getFileProjects().add(fileProject);
                fileModelList.add(fileProject);

                fileList.setModel(fileModelList);

                Clients.clearBusy();
            }
        });
    }

    public ListModelList getEndCommandProject()
    {
        ListModelList modelList = new ListModelList();

        for (ComandProject com : project.getComandProject())
        {
            if (!com.isWork())
                modelList.add(com);
        }

        return modelList;
    }

    public void revertPartnerComandInWork(Object obj)
    {
        final ComandProject com = (ComandProject) obj;

        for (ComandProject comandProject2 : project.getComandProject())
        {
            if (comandProject2.equals(com))
            {
                com.setWork(true);
                comandProject2.setWork(true);

                try {
                    new WorkWithCommandMz().changeEntity(comandProject2);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Map arg = new HashMap<String, Object>(){
                    {
                        put("project", project);
                        put("clickBut", new ButtonClickInterf() {
                            @Override
                            public void click(Textbox textbox) {
                                NotificationService.revertNotifHtmlForRevertComandWorkCom(com.getPartnerProject(), "/pages/pagesProject/Project.zul", project, project.getStatus(), textbox.getText());
                            }
                        });
                    }
                };

                final Window wind1 = (Window) Executions.createComponents("/pages/window/inputTextDialog.zul", null,arg);
                wind1.setTitle("Напишите причину возврата");
                wind1.addEventListener("onClose", new EventListener<Event>() {
                    @Override
                    public void onEvent(Event event) throws Exception {
//                        String text = ((Textbox) wind1.getChildren().get(0)).getText();
//
//                        NotificationService.revertNotifHtmlForRevertComandWorkCom(com.getPartnerProject(), "/pages/pagesProject/Project.zul", project, project.getStatus(), text);
                    }
                });

                wind1.doModal();
            }
        }
    }
}
