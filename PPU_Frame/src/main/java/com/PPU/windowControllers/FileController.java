package com.PPU.windowControllers;

import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.zul.Filedownload;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 18.05.14
 * Time: 19:26
 * To change this template use File | Settings | File Templates.
 */
public class FileController {

    public static void saveMediaToFile(Media media)
    {
        try
        {
            File tmpFile = new File("/tmpFile");
//            if (!tmpFile.isDirectory())
                tmpFile.mkdirs();

            if (media.isBinary())
            {
                InputStream inputStream = media.getStreamData();
                FileOutputStream outputStream = new FileOutputStream("/tmpFile/"+media.getName());

                int y;

                while ((y = inputStream.read()) != -1)
                {
                    outputStream.write(y);
                }

                outputStream.flush();
                outputStream.close();
            }
            else
            {
                Reader reader = media.getReaderData();

                Writer writer = new FileWriter("/tmpFile/"+media.getName());

                int y;
                String f = new String();

                while ((y = reader.read()) != -1)
                {
                    writer.write(y);
                    f+=(char)y;
                }

                writer.flush();
                writer.close();
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static void downloadFile(String fileName)
    {
        try {
            Filedownload.save(new File("/tmpFile/"+fileName), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
