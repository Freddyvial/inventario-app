package com.poli.inventory.services;


import com.poli.inventory.domain.ArticlesReported;
import com.poli.inventory.domain.Report;

import java.util.List;

public interface ArticlesReportedService {

    List<ArticlesReported> consultReport(String idReport);
    ArticlesReported createArticlesReport(ArticlesReported articlesReported) throws Exception;
}
