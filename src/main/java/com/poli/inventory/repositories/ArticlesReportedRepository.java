package com.poli.inventory.repositories;


import com.poli.inventory.domain.ArticlesReported;
import com.poli.inventory.domain.MonitorModel;

import java.util.List;

public interface ArticlesReportedRepository {

    ArticlesReported create(ArticlesReported articlesReported);
    List<ArticlesReported> consulArticlesReported(String idReport);
    ArticlesReported checkArticlesReported(ArticlesReported articlesReported);
}
