package com.poli.inventory.services;


import com.poli.inventory.domain.Report;

import java.util.List;

public interface ReportService {

    List<Report> consultReport(String idCampus);
    Report createReport(Report report) throws Exception;

}
