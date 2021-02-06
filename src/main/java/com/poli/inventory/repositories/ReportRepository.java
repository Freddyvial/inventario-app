package com.poli.inventory.repositories;





import com.poli.inventory.domain.GeneralReport;
import com.poli.inventory.domain.Report;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.FileNotFoundException;
import java.util.List;

public interface ReportRepository {

    List<Report> consultReport(String idCampus);
    Report createReport(Report report);
    Report checkReport(String name);
    Report update(Report report);
    List<GeneralReport> consultGeneralReport(String idCampus);
    JasperPrint exportReport(String idCampus) throws FileNotFoundException, JRException;
}
