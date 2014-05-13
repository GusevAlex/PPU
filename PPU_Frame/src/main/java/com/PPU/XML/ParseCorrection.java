package com.PPU.XML;

import com.PPU.DB.tables.CorrectionsMZ;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Alex on 13.05.2014.
 */
public class ParseCorrection {
    private String fileName = "/rep.xml";

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
                String descr = "ћуниципальное задание перешло на статус " + cor.getValueAfter();
                rep.setTitle(descr);
                rep.setDescription(descr);
            }
            else {
                String descr = "¬веден параметр \"" + cor.getParametr().getName() + "\" со значением " + cor.getValueAfter();
                rep.setTitle(descr);
                rep.setDescription(descr);
            }

            list.add(rep);
        }

        xmlReportService.saveReportToXML(list);
    }
}
