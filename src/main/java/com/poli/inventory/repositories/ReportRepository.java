package com.poli.inventory.repositories;





import com.poli.inventory.domain.Report;

import java.util.List;

public interface ReportRepository {

    List<Report> consultReport(String idCampus);
    Report createReport(Report report);
    Report checkReport(String name);
    Report update(Report report);
}
