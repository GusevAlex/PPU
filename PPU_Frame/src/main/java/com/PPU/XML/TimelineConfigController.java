package com.PPU.XML;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.zkforge.timeline.Bandinfo;
import org.zkforge.timeline.Timeline;
import org.zkforge.timeline.decorator.SpanHighlightDecorator;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CheckEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;


public class TimelineConfigController extends SelectorComposer<Component> {

	private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Wire
	private Timeline timeline;

	@Wire
	private Bandinfo bandinfoMonth, bandinfoYear;

	@Wire
	private Textbox filter, highlight1, highlight2;

	private SpanHighlightDecorator spanHighlightDecorator;
	private Date midDate;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		spanHighlightDecorator = new SpanHighlightDecorator(dateFormat.parse("2010-06-29"),
				dateFormat.parse("2010-07-24"));
		spanHighlightDecorator.setStartLabel("start");
		spanHighlightDecorator.setEndLabel("end");
		spanHighlightDecorator.setColor("blue");
		spanHighlightDecorator.setOpacity(25);

		midDate = dateFormat.parse("2010-07-10");
	}

	@Listen("onClick=#applyFilter")
	public void applyFilter() {
		timeline.performFiltering(filter.getValue());
	}

	@Listen("onClick=#clearFilter")
	public void clearFilter() {
		timeline.clearFilter();
	}

	@Listen("onClick=#applyHighlights")
	public void applyHighlight() {
		String[] highlights = {highlight1.getValue(), highlight2.getValue()};
		timeline.performHighlitht(highlights);
	}

	@Listen("onClick=#clearHighlights")
	public void clearHighlight() {
		timeline.clearHighlight();
	}

	@Listen("onCheck=#enableOverview")
	public void toggleOverview(CheckEvent event) {
		bandinfoYear.setOverview(event.isChecked());
	}

	@Listen("onCheck=#enableDecorator")
	public void toggleDecorator(CheckEvent event) {
		if(event.isChecked()) {
			bandinfoMonth.addHighlightDecorator(spanHighlightDecorator);
			bandinfoYear.addHighlightDecorator(spanHighlightDecorator);
			bandinfoMonth.scrollToCenter(midDate);
		} else {
			bandinfoMonth.removeHighlightDecorator(spanHighlightDecorator);
			bandinfoYear.removeHighlightDecorator(spanHighlightDecorator);
			bandinfoMonth.scrollToCenter(midDate);
		}
	}

	@Listen("onCheck=#eventSource")
	public void eventSourceChanged(CheckEvent event) throws ParseException {
		Radio radio = (Radio) event.getTarget();

		if("zk_development".equals(radio.getValue())) {
			bandinfoMonth.setEventSourceUrl("/report/timeline_data.xml");
			bandinfoMonth.scrollToCenter(dateFormat.parse("2010-06-29"));
		} else {
			bandinfoMonth.setEventSourceUrl("/report/timeline_data.xml");
			bandinfoMonth.scrollToCenter(dateFormat.parse("2008-10-15"));
		}
	}

}