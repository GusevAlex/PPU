package com.PPU.XML;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		bandinfoMonth.setEventSourceUrl("/report/timeline_data.xml");
		bandinfoYear.setEventSourceUrl("/report/timeline_data.xml");
	}
}
