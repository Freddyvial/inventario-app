package com.poli.inventory.services.impl;

import com.poli.inventory.domain.Report;
import com.poli.inventory.repositories.ReportRepository;
import com.poli.inventory.services.ReportService;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;


@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;


    @Override
    public List<Report> consultReport(String idCampus) {

        return reportRepository.consultReport(idCampus);
    }
    @Override
    public Report createReport(Report report) throws Exception {
        if (report.getIdReport() == 0) {
            return reportRepository.createReport(report);
        } else {
            Report campusExist = reportRepository.checkReport(report.getDate().toString());

            if (campusExist != null) {
                return reportRepository.createReport(report);
            } else {
                return null;

            }
        }
    }
    @Override
    public void exportReport(String idCampus, OutputStream outputStream) throws JRException, IOException, SQLException{
        JasperPrint jasperPrint = reportRepository.exportReport(idCampus);
        JasperExportManager.exportReportToPdfStream(jasperPrint,outputStream);
    }





}
