package com.poli.inventory.repositories.impl;

import com.poli.inventory.domain.Article;
import com.poli.inventory.domain.ArticlesReported;
import com.poli.inventory.domain.Report;
import com.poli.inventory.repositories.ArticlesReportedRepository;
import com.poli.inventory.repositories.MonitorModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ArticlesReportedRepositoryImpl implements ArticlesReportedRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ArticlesReported> consulArticlesReported(String idReport) {
        String sql = "SELECT * FROM roominventory.articlesreported WHERE idReport=?;";
        List<ArticlesReported> articlesReportedList = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,idReport);
        for (Map row : rows) {
            ArticlesReported articlesReported=new ArticlesReported();
            Report report= new Report();
            Article article= new Article();
            report.setIdReport((int) row.get("idReport"));
            article.setId((int) row.get("idArticle"));
            articlesReported.setReport(report);
            articlesReported.setArticle(article);
            articlesReportedList.add(articlesReported);        }
        return articlesReportedList;
    }
    @Override
    public ArticlesReported create(ArticlesReported articlesReported) {
        String sql = "INSERT INTO roominventory.articlesreported (idReport,idArticle) VALUES (?, ?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, articlesReported.getReport().getIdReport());
                ps.setInt(2, articlesReported.getArticle().getId());
                return ps;
            }
        });
        return  articlesReported;
    }
    @Override
    public ArticlesReported checkArticlesReported(ArticlesReported articlesReported) {
        String sql = "select * from roominventory.articlesreported where idArticle = ? AND idReport=?";

        List<ArticlesReported> articlesReportedList = jdbcTemplate.query(sql, new Object[]{articlesReported.getReport().getIdReport(),articlesReported.getArticle().getId()}, new BeanPropertyRowMapper(ArticlesReported.class));
        return articlesReportedList.size() > 0 ? articlesReportedList.get(0) : null;
    }






}
