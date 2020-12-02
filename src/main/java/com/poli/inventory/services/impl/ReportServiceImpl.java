package com.poli.inventory.services.impl;

import com.poli.inventory.domain.Report;
import com.poli.inventory.repositories.ReportRepository;
import com.poli.inventory.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            Report campusExist = reportRepository.checkReport(report.getNameReport());

            if (campusExist != null) {
                return reportRepository.createReport(report);
            } else {
                return null;

            }
        }
    }

}
