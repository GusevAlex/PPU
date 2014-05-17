package com.PPU.windowControllers.PageMZ;

import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.MZ;
import com.PPU.DB.tables.ValuesParametrForMZ;
import com.PPU.DB.workLogic.WorkWithComandProject;
import com.PPU.DB.workLogic.WorkWithMZ;
import com.PPU.XML.ParseCorrection;
import org.zkforge.timeline.Bandinfo;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.chart.Chart;
import org.zkoss.chart.Charts;
import org.zkoss.chart.Point;
import org.zkoss.chart.Series;
import org.zkoss.chart.plotOptions.PiePlotOptions;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
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

    @Wire
    Charts chart;

	public TimeLine()
	{
//		String id = Executions.getCurrent().getParameter("id");
//
//		if (id != null)
//		{
//			MZ mz = (MZ) new WorkWithMZ().getEntity(new Integer(id));
//
//			ParseCorrection parseCorrection = new ParseCorrection();
//
//			parseCorrection.saveReportToXML((CorrectionsMZ[]) mz.getCorrectionsMZ().toArray());
//		}

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

        String id = Executions.getCurrent().getParameter("id");

        if (id != null)
        {
            Date currentDate = Calendar.getInstance().getTime();
            bandinfoMonth.setDate(currentDate);
            bandinfoYear.setDate(currentDate);

            MZ mz = (MZ) new WorkWithMZ().getEntity(new Integer(id));

            discrName=mz.getName();

            ParseCorrection parseCorrection = new ParseCorrection();
            Object [] o = mz.getCorrectionsMZ().toArray();

            CorrectionsMZ[] cor = new CorrectionsMZ[o.length];

            for (int i=0; i<o.length; i++)
                cor[i] = (CorrectionsMZ) o[i];

            parseCorrection.getModelByCorrection(cor);

            bandinfoMonth.setModel(parseCorrection.getModelByCorrection(cor));
            bandinfoYear.setModel(parseCorrection.getModelByCorrection(cor));

            setDataInChart();
        }
    }

    private void setDataInChart()
    {
        Chart chartOptional = chart.getChart();
        chartOptional.setPlotBorderWidth(0);
        chartOptional.setBackgroundColor("");
        chartOptional.setPlotShadow(false);

        chart.getTooltip().setPointFormat(
                "{series.name}: <b>{point.percentage:.1f}%</b>");

        PiePlotOptions plotOptions = chart.getPlotOptions().getPie();
        plotOptions.setAllowPointSelect(true);
        plotOptions.setCursor("pointer");
        plotOptions.getDataLabels().setEnabled(false);
        plotOptions.setShowInLegend(true);

        Series series = chart.getSeries();
        series.setType("pie");
        series.setName("Browser share");


        String id = Executions.getCurrent().getParameter("id");

        if (id != null)
        {
            long [] pointMas = new long[5];
            MZ mz = (MZ) new WorkWithMZ().getEntity(new Integer(id));

            Integer [] intMas = new Integer[mz.getCorrectionsMZ().size()];

            int i = 0;
            for (CorrectionsMZ cor : mz.getCorrectionsMZ())
                intMas[i++] = cor.getId();

            Arrays.sort(intMas);

            long timeLong = 0;
            String nameCor = new String();

            for (int num : intMas)
            {
                for (CorrectionsMZ cor : mz.getCorrectionsMZ())
                    if (cor.getId() == num)
                    {
                        int numIndex = 0;

                        if (nameCor.equals("Создается"))
                            numIndex = 0;
                        else
                        if (nameCor.equals("Создан"))
                            numIndex = 1;
                        else
                        if (nameCor.equals("Планирование"))
                            numIndex = 2;
                        else
                        if (nameCor.equals("На согласовании"))
                            numIndex = 3;
                         else
                        if (nameCor.equals("Выполняется"))
                            numIndex = 4;

                        if (timeLong != 0)
                            pointMas[numIndex]+= cor.getCorrectionDate().getTime() - timeLong;

                        timeLong = cor.getCorrectionDate().getTime();
                        nameCor = cor.getValueAfter();
                    }
            }

            {
                long difDate = 0;
                for (CorrectionsMZ cor : mz.getCorrectionsMZ())
                    if (cor.getValueAfter().equals("Выполнено"))
                    {
                        difDate = cor.getCorrectionDate().getTime();
                    }

                if (!mz.getExpirationDate().after(Calendar.getInstance().getTime()))
                    difDate = Calendar.getInstance().getTime().getTime();
                else
                    difDate = mz.getExpirationDate().getTime();

                int numIndex = 0;

                if (nameCor.equals("Создается"))
                    numIndex = 0;
                else
                if (nameCor.equals("Создан"))
                    numIndex = 1;
                else
                if (nameCor.equals("Планирование"))
                    numIndex = 2;
                else
                if (nameCor.equals("На согласовании"))
                    numIndex = 3;
                else
                if (nameCor.equals("Выполняется"))
                    numIndex = 4;

                if (timeLong != 0)
                    pointMas[numIndex]+= difDate - timeLong;
            }

            series.addPoint(new Point("Создается", pointMas[0]));
            series.addPoint(new Point("Создан", pointMas[1]));
            Point point = new Point("Планирование", pointMas[2]);
            point.setSliced(true);
            point.setSelected(true);
            series.addPoint(point);
            series.addPoint(new Point("На согласовании", pointMas[3]));
            series.addPoint(new Point("Выполняется", pointMas[4]));
        }
    }
}
