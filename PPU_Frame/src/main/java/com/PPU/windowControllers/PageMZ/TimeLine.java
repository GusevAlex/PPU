package com.PPU.windowControllers.PageMZ;

import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.MZ;
import com.PPU.DB.workLogic.WorkWithComandProject;
import com.PPU.DB.workLogic.WorkWithMZ;
import com.PPU.XML.ParseCorrection;
import org.zkoss.zk.ui.Executions;

/**
 * Created by user on 15.05.14.
 */
public class TimeLine {
	private String handName;
	private String discrName;

	public TimeLine()
	{
		String id = Executions.getCurrent().getParameter("id");

		if (id != null)
		{
			MZ mz = (MZ) new WorkWithMZ().getEntity(new Integer(id));

			ParseCorrection parseCorrection = new ParseCorrection();

			parseCorrection.saveReportToXML((CorrectionsMZ[]) mz.getCorrectionsMZ().toArray());
		}
	}
}
