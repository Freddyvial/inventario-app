package com.poli.inventory.repositories.impl;

import com.poli.inventory.domain.Article;
import com.poli.inventory.domain.Room;
import com.poli.inventory.domain.State;
import com.poli.inventory.domain.TypeArticle;
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
    public List<Article> getArticles(String idCampus) {
        String sql = "SELECT a.*,r.name as nameRoom,r.responsible,s.name as nameState,t.name as nameTypeArticle FROM roominventory.articles as a \n" +
                "INNER JOIN state as s on a.idState=s.idState\n" +
                "INNER JOIN typearticle as t on a.idTypeArticle= t.idTypeArticle \n" +
                "INNER JOIN rooms as r on a.idRoom=r.idRoom\n" +
                "WHERE a.idState=3 AND a.idCampus=?";
        List<Article> articles = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,idCampus);
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
            Room newRoom= new Room();
            newRoom.setIdRoom((Integer) row.get("idRoom"));
            newRoom.setName((String) row.get("nameRoom"));
            newRoom.setResponsible((String) row.get("responsible"));
            newArticle.setRoom(newRoom);
            TypeArticle newTypeArticle=new TypeArticle();
            newTypeArticle.setIdTypeArticle((Integer) row.get("idTypeArticle"));
            newTypeArticle.setName((String) row.get("nameTypeArticle"));
            newArticle.setTypeArticle(newTypeArticle);

            articles.add(newArticle);
        }
        return articles;
    }
    @Override
    public List<TypeArticle> getTypeArticle() {
        String sql = "SELECT * FROM roominventory.typearticle";
        List<TypeArticle> typeArticles = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            TypeArticle typeArticle = new TypeArticle();
            typeArticle.setIdTypeArticle((Integer) row.get("idTypeArticle"));
            typeArticle.setName((String) row.get("name"));
            typeArticles.add(typeArticle);
        }
        return typeArticles;
    }
    @Override
    public List<Article> consulArticleByRoom(Integer idRoom) {
        String sql = "SELECT a.*,r.name as nameRoom,r.responsible ,s.name as nameState,t.name as nameTypeArticle FROM roominventory.articles as a \n" +
                "INNER JOIN state as s on a.idState=s.idState\n" +
                "INNER JOIN typearticle as t on a.idTypeArticle= t.idTypeArticle \n" +
                "INNER JOIN rooms as r on a.idRoom=r.idRoom\n" +
                "WHERE a.idRoom=?";
        List<Article> articles = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,idRoom);
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
            Room newRoom= new Room();
            newRoom.setIdRoom((Integer) row.get("idRoom"));
            newRoom.setName((String) row.get("nameRoom"));
            newRoom.setResponsible((String) row.get("responsible"));
            newArticle.setRoom(newRoom);
            TypeArticle newTypeArticle=new TypeArticle();
            newTypeArticle.setIdTypeArticle((Integer) row.get("idTypeArticle"));
            newTypeArticle.setName((String) row.get("nameTypeArticle"));
            newArticle.setTypeArticle(newTypeArticle);
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
        String sql = "INSERT INTO roominventory.articles (name, serial, idState,photo,idTypeArticle,idCampus) VALUES (?, ?, ?, ?,?,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, article.getName());
                ps.setString(2, article.getSerial());
                ps.setInt(3, article.getState().getId());
                ps.setBinaryStream(4, getInputStreamImage(article.getPhoto()));
                ps.setInt(5,article.getTypeArticle().getIdTypeArticle());
                ps.setInt(6,article.getCampus().getIdCampus());
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
    @Override
    public Article changeStateArticle(Article article) {
        jdbcTemplate.update(
                "UPDATE articles SET idState=? WHERE idArticle=?",
                article.getState().getId(),article.getId());
        return article;
    }
    @Override
    public Article changeIdRoomArticle(Article article) {
        jdbcTemplate.update(
                "UPDATE articles SET idRoom=? WHERE idArticle=?",
               article.getRoom().getIdRoom(),article.getId());
        return article;
    }
}
