package com.PPU.composite;

import com.PPU.composite.helper.AnnotHelper;
import com.PPU.windowControllers.ListCellContant;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Window;

/**
 * Created by user on 28.04.14.
 */
public class AddObjectWindow extends Window implements IdSpace {
	private static final long serialVersionUID = -4653481165297843651L;

	private int countPage;
	private int numPage;

	private String header;
	private ListCellContant listCellContant;
	private boolean load;

	private String workerName;

	private Object [] obj;

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

	public void setLoad(boolean load) {
		this.load = load;

		Executions.createComponents("/pages/composite/addObjectWindow.zul", this, null);
		Selectors.wireComponents(this, this, false);
	}

	@Override
	public void doModal() {
		if (!workerName.equals(""))
			obj = (Object []) AnnotHelper.callWorkerMethod(workerName, listCellContant.getMethodList());

		super.doModal();
	}
}
