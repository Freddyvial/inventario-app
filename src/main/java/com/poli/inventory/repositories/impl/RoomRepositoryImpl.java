package com.poli.inventory.repositories.impl;

import com.poli.inventory.domain.Article;
import com.poli.inventory.domain.State;
import com.poli.inventory.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Article> getArticles() {
        String sql = "SELECT a.*, s.name AS nameState FROM articles AS a\n" +
                "INNER JOIN state AS s ON a.idState = s.idState";
        List<Article> articles = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Article newArticle = new Article();
            newArticle.setId((Integer) row.get("idArticle"));
            newArticle.setName((String) row.get("name"));
            newArticle.setSerial((String) row.get("serial"));
            State state = new State();
            state.setId((Integer) row.get("idState"));
            state.setName((String) row.get("nameState"));
            newArticle.setState(state);
            newArticle.setPhoto((byte[]) row.get("photo"));
            articles.add(newArticle);
        }
        return articles;
    }

    @Override
    public Article createArticle(Article article) {

        if (String.valueOf(article.getId()).equals("")||String.valueOf(article.getId()).equals("0")) {

            return create(article);

        } else {
            return update(article);
        }
    }

    private Article create(Article article) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO roominventory.articles (name, serial, idState,photo) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, article.getName());
                ps.setString(2, article.getSerial());
                ps.setInt(3, article.getState().getId());
                ps.setBinaryStream(4, getInputStreamImage(article.getPhoto()));
                return ps;
            }
        }, holder);

        Long key = holder.getKey().longValue();
        article.setId(key.intValue());
        return article;
    }
    private InputStream getInputStreamImage(byte[] image) {
        return new ByteArrayInputStream(image);
    }

    @Override
    public Article checkArticle(String serial) {
        String sql = "select * from roominventory.articles where serial = ?";

        List<Article> articles = jdbcTemplate.query(sql, new Object[]{serial}, new BeanPropertyRowMapper(Article.class));
        return articles.size() > 0 ? articles.get(0) : null;
    }

    @Override
    public Article update(Article article) {
        jdbcTemplate.update(
                "UPDATE articles SET name=?, serial=?, idState=?, photo=? WHERE idArticle=?",
                article.getName(),article.getSerial(),article.getState().getId(),article.getPhoto(),article.getId());
        return article;
    }
}
