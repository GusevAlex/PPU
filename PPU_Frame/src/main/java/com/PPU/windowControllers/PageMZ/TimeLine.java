package com.PPU.windowControllers.PageMZ;

import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.MZ;
import com.PPU.DB.workLogic.WorkWithComandProject;
import com.PPU.DB.workLogic.WorkWithMZ;
import com.PPU.XML.ParseCorrection;
import org.zkforge.timeline.Bandinfo;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 15.05.14.
 */
public class TimeLine {
	private String handName;
	private String discrName;
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Wire
    private Bandinfo bandinfoMonth, bandinfoYear;

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

    public String getHandName() {
        return handName;
    }

    public void setHandName(String handName) {
        this.handName = handName;
    }

    public String getDiscrName() {
        return discrName;
    }

    public void setDiscrName(String discrName) {
        this.discrName = discrName;
    }

    @AfterCompose
    public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view) throws Exception {

        Selectors.wireComponents(view, this, false);
        Date currentDate = dateFormat.parse("2014-06-29");
        bandinfoMonth.setDate(currentDate);
        bandinfoYear.setDate(currentDate);
//		bandinfoMonth.setEventSourceUrl("D://report/timeline_data.xml");
//		bandinfoYear.setEventSourceUrl("D://report/timeline_data.xml");

        MZ mz = (MZ) new WorkWithMZ().getEntity(new Integer(1));

        ParseCorrection parseCorrection = new ParseCorrection();
        Object [] o = mz.getCorrectionsMZ().toArray();

        CorrectionsMZ[] cor = new CorrectionsMZ[o.length];

        for (int i=0; i<o.length; i++)
            cor[i] = (CorrectionsMZ) o[i];

        parseCorrection.getModelByCorrection(cor);

        bandinfoMonth.setModel(parseCorrection.getModelByCorrection(cor));
        bandinfoYear.setModel(parseCorrection.getModelByCorrection(cor));
    }
}
