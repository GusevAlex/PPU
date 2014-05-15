package com.PPU.XML;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.MZ;
import com.PPU.DB.workLogic.WorkWithMZ;
import org.zkforge.timeline.Bandinfo;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;


public class TimelineController extends SelectorComposer<Component> {

	private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Wire
	private Bandinfo bandinfoMonth, bandinfoYear;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
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
