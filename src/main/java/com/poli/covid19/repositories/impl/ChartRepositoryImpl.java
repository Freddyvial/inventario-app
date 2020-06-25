package com.poli.covid19.repositories.impl;

import com.poli.covid19.domain.ChartResultByBirthDate;
import com.poli.covid19.domain.ChartResultByDepartment;
import com.poli.covid19.domain.ChartResultByDepartmentByState;
import com.poli.covid19.domain.ChartResultByState;
import com.poli.covid19.repositories.ChartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChartRepositoryImpl implements ChartRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ChartResultByDepartment> consultResultByDepartment() {
        String sql = "select d.name as nameDepartment , SUM(CASE WHEN p.result>=50 THEN 1 ELSE 0 END) AS highProbability,SUM(CASE WHEN p.result<50 THEN 1 ELSE 0 END) AS lowProbability\n" +
                "from covid19.patients as p\n" +
                "inner join covid19.town as t on p.idTown=t.id\n" +
                "inner join covid19.departments as d on t.idDepartment= d.id\n" +
                "group by d.name order by highProbability desc";
        List<ChartResultByDepartment> chartResultByDepartments = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ChartResultByDepartment.class));
        return chartResultByDepartments;
    }
    @Override
    public List<ChartResultByState> consultResultByState() {
        String sql = "select s.value as nameState , SUM(CASE WHEN p.idState is null THEN 0 ELSE 1 END) AS quantity\n" +
                "from covid19.patients as p\n" +
                "inner join covid19.state as s on p.idState=s.id\n" +
                "group by s.value order by quantity desc";
        List<ChartResultByState> chartResultByStates = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ChartResultByState.class));
        return chartResultByStates;
    }
    @Override
    public List<ChartResultByDepartmentByState> consultResultByDepartmentByState() {
        String sql = "select d.name as nameDepartment,s.value as nameState , SUM(CASE WHEN p.idState is null THEN 0 ELSE 1 END) AS quantity\n" +
                "from covid19.patients as p\n" +
                "inner join covid19.state as s on p.idState=s.id\n" +
                "inner join covid19.town as t on p.idTown=t.id\n" +
                "inner join covid19.departments as d on t.idDepartment= d.id\n" +
                "group by d.name,s.value order by d.name";
        List<ChartResultByDepartmentByState> chartResultByDepartmentByStates = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ChartResultByDepartmentByState.class));
        return chartResultByDepartmentByStates;
    }
    @Override
    public List<ChartResultByBirthDate> consultResultByBirthDate() {
        String sql = "SELECT TIMESTAMPDIFF (YEAR, p.birthDate, CURDATE()) as age, count( p.birthDate) AS quantity\n" +
                "FROM covid19.patients as p\n" +
                "group by age;";
        List<ChartResultByBirthDate> chartResultByBirthDates = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ChartResultByBirthDate.class));
        return chartResultByBirthDates;
    }

}
