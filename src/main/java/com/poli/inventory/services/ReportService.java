package com.poli.inventory.services;


import com.poli.inventory.domain.Report;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

public interface ReportService {

    List<Report> consultReport(String idCampus);
    Report createReport(Report report) throws Exception;
    void exportReport(String idCampus, OutputStream outputStream) throws JRException, IOException, SQLException;
}
