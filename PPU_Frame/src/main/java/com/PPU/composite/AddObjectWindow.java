package com.PPU.composite;

import com.PPU.DB.workLogic.ClassInvokeCall;
import com.PPU.composite.helper.*;
import com.PPU.windowControllers.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 * Created by user on 28.04.14.
 */
public class AddObjectWindow extends Window implements IdSpace {
	private static final long serialVersionUID = -4653481165297843651L;

	@Wire
	Textbox text;

	@Wire
	ObjectListBox listboxV;

	private int countPage;
	private int numPage;

	private AddObject addObject;

	private String header;
	private ListCellContant listCellContant;
	private boolean load;

	private String workerName;

	private Object obj;

	private Object [] objs;

	public AddObjectWindow()
	{
		super();
	}

	public int getCountPage() {
		return countPage;
	}

	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}

	public int getNumPage() {
		return numPage;
	}

	public void setNumPage(int numPage) {
		this.numPage = numPage;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public ListCellContant getListCellContant() {
		return listCellContant;
	}

	public void setListCellContant(ListCellContant listCellContant) {
		this.listCellContant = listCellContant;
	}

	public boolean isLoad() {
		return load;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public void setLoad(boolean load) {
		this.load = load;

		Executions.createComponents("/pages/composite/addObjectWindow.zul", this, null);
		Selectors.wireComponents(this, this, false);
		Selectors.wireEventListeners(this,this);
	}

	@Override
	public void doModal() {
		if (!workerName.equals(""))
			objs = (Object []) AnnotHelper.callWorkerMethod(workerName, listCellContant.getMethodList());

		super.doModal();
	}

	public void doModal(AddObject addObject) {
		this.addObject = addObject;
		if (!workerName.equals(""))
			objs = (Object []) AnnotHelper.callWorkerMethod(workerName, listCellContant.getMethodList());

		getParamInObj();
		super.doModal();
	}

	private void closeWindow()
	{
		this.onClose();
	}

	private void setParamInObj()
	{
		Object [] paramList = new Object[1];

		switch (listCellContant.getColymnType())
		{
			case 1:
				paramList[0] = text.getValue();
		}

		//������ ������������ � ������))
		String methodName = "s"+listCellContant.getMethodList().substring(1);


		ClassInvokeCall.callMethod(obj,methodName,paramList);
	}

	private void getParamInObj()
	{
		String methodName = listCellContant.getMethodList();

		switch (listCellContant.getColymnType())
		{
			case 1:
				if (ClassInvokeCall.callMethod(obj,methodName) != null)
					text.setValue(ClassInvokeCall.callMethod(obj,methodName).toString());
				else
					text.setValue("");
				break;
			case 2:
				int selectedIndex = listboxV.getSelectedIndex();

				if (selectedIndex != -1)
				{
					obj = listboxV.getObjs()[selectedIndex];
				}

		}
	}

	@Listen("onClick = #but2")
	public void butn1Click()
	{
		addObject.nextClick(obj);
		int u = 0;
		setParamInObj();
		closeWindow();
	}

	@Listen("onClick = #but1")
	public void butn2Click()
	{
		addObject.previsClick(obj);
		int u = 0;
		setParamInObj();
		closeWindow();
	}
}
