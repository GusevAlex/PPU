package com.PPU.XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Alex on 13.05.2014.
 */
public class XMLReportService {

    private String fileName;

    public XMLReportService(String fileName)
    {
        this.fileName = fileName;
    }

    private void saveToFile(String fileName, String content)
    {
        try {
            OutputStream fileStream = new FileOutputStream(fileName);
            fileStream.write(content.getBytes());
            fileStream.flush();

            fileStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String loadFromFile(String fileName)
    {
        String result = new String();
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
            String line = new String();

            while ((line = fileReader.readLine()) != null)
            {
                result+=line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public void saveReportToXML(List<ReportEntity> reportEntityList)
    {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("data");
            doc.appendChild(rootElement);

            for (ReportEntity entity : reportEntityList) {
                Element event = doc.createElement("event");
                event.setAttribute("start", entity.getStartDate().toString());
                event.setAttribute("end", entity.getEndDate().toString());
                event.setAttribute("title", entity.getTitle().toString());
                event.appendChild(doc.createTextNode(entity.getDescription()));

                rootElement.appendChild(event);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result =  new StreamResult(new File(fileName));
            transformer.transform(source, result);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        XMLReportService xmlReportService = new XMLReportService("D:\\\\1.xml");
        List<ReportEntity> list = new ArrayList<ReportEntity>();
        ReportEntity rep = new ReportEntity();
        rep.setDescription("Описание");
        rep.setStartDate(Calendar.getInstance().getTime());
        rep.setEndDate(Calendar.getInstance().getTime());
        rep.setTitle("Титулка");

        list.add(rep);

        xmlReportService.saveReportToXML(list);
    }
}
