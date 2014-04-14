package com.PPU.DB.workLogic;

import com.PPU.DB.DAO.PpuDaoInterface;
import com.PPU.DB.tables.MZ;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Alex on 14.04.2014.
 */
public class WorkWithMZ {

//    public static

    private List<MZ> mzs;
    private MZ mz;
    private PpuDaoInterface ppuDao;

    public WorkWithMZ()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        ppuDao = (PpuDaoInterface) context.getBean("dataDao");

        mz = new MZ();
    }

    public WorkWithMZ(int idMZ)
    {
        this();

        mz = ppuDao.getMz(idMZ);
    }

    public WorkWithMZ(String fields, String fieldValue)
    {
        this();

        mzs = ppuDao.findMz(fields, fieldValue);
    }

//    public String getColumnFromList(String columnName)
//    {
//
//    }



}
