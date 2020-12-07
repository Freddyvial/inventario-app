package com.poli.inventory.services.impl;

import com.poli.inventory.domain.ArticlesReported;
import com.poli.inventory.repositories.ArticlesReportedRepository;
import com.poli.inventory.services.ArticlesReportedService;
import com.poli.inventory.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticlesReportedServiceImpl implements ArticlesReportedService {

    @Autowired
    private ArticlesReportedRepository articlesReportedRepository;

    @Override
    public List<ArticlesReported> consultReport(String idReport) {

        return articlesReportedRepository.consulArticlesReported(idReport);
    }

    @Override
    public ArticlesReported createArticlesReport(ArticlesReported articlesReported) throws Exception {
        ArticlesReported articleReportExist = articlesReportedRepository.checkArticlesReported(articlesReported);

        if (articleReportExist == null) {
            return articlesReportedRepository.create(articlesReported);
        } else {
            return null;


        }
    }

}
