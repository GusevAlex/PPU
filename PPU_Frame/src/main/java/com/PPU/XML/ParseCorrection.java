package com.PPU.XML;

import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.CorrectionsProject;
import org.zkforge.timeline.data.OccurEvent;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 13.05.2014.
 */
public class ParseCorrection {
    private String fileName = "/report/timeline_data.xml";

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void saveReportToXML(CorrectionsMZ[] corrections)
    {
        XMLReportService xmlReportService = new XMLReportService(fileName);

        List<ReportEntity> list = new ArrayList<ReportEntity>();

        for (CorrectionsMZ cor : corrections) {
            ReportEntity rep = new ReportEntity();
            rep.setStartDate(cor.getCorrectionDate());
            rep.setEndDate(cor.getCorrectionDate());

            if (cor.getParametr().getId() == 1) {
                String descr = "������������� ������� ������� �� ������ " + cor.getValueAfter();
                rep.setTitle(descr);
                rep.setDescription(descr);
            }
            else {
                String descr = "������ �������� \"" + cor.getParametr().getName() + "\" �� ��������� " + cor.getValueAfter();
                rep.setTitle(descr);
                rep.setDescription(descr);
            }

            list.add(rep);
        }

        xmlReportService.saveReportToXML(list);
    }

    public ListModelList getModelByCorrection(CorrectionsMZ[] corrections)
    {
        ListModelList list = new ListModelList();

        for (CorrectionsMZ cor : corrections) {
            ReportEntity rep = new ReportEntity();
            rep.setStartDate(cor.getCorrectionDate());
            rep.setEndDate(cor.getCorrectionDate());

            if (cor.getParametr().getId() == 1) {
                String descr = "������������� ������� \""+cor.getMZ().getName()+"\" ������� �� ������ " + cor.getValueAfter();
                rep.setTitle(descr);
                rep.setDescription(descr);
            }
            else {
                String descr = "������ �������� \"" + cor.getParametr().getName() + "\" �� ��������� " + cor.getValueAfter();
                rep.setTitle(descr);
                rep.setDescription(descr);
            }

            OccurEvent event = new OccurEvent();
            event.setStart(rep.getStartDate());
            event.setEnd(rep.getEndDate());
            event.setDuration(true);
            event.setDescription(rep.getDescription());
            event.setText(rep.getTitle());

            list.add(event);
        }

        return list;
    }

    public ListModelList getModelByCorrection(CorrectionsProject[] corrections)
    {
        ListModelList list = new ListModelList();

        for (CorrectionsProject cor : corrections) {
            ReportEntity rep = new ReportEntity();
            rep.setStartDate(cor.getCorrectionDate());
            rep.setEndDate(cor.getCorrectionDate());

            if (cor.getParametr().getId() == 1) {
                String descr = "������ \""+cor.getProject().getName()+"\" ������� �� ������ " + cor.getValueAfter();
                rep.setTitle(descr);
                rep.setDescription(descr);
            }
            else {
                String descr = "������ �������� \"" + cor.getParametr().getName() + "\" �� ��������� " + cor.getValueAfter();
                rep.setTitle(descr);
                rep.setDescription(descr);
            }

            OccurEvent event = new OccurEvent();
            event.setStart(rep.getStartDate());
            event.setEnd(rep.getEndDate());
            event.setDuration(true);
            event.setDescription(rep.getDescription());
            event.setText(rep.getTitle());

            list.add(event);
        }

        return list;
    }
}
