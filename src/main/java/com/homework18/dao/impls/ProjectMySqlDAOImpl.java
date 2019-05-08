package com.homework18.dao.impls;

import com.homework18.dao.interfaces.ProjectDao;
import com.homework18.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository("mySqlDAO")
public class ProjectMySqlDAOImpl implements ProjectDao {
    private static final String CREATE = "INSERT INTO projects (name, balance) VALUES (?, ?);";
    private static final String READ = "SELECT * FROM projects WHERE id = ?;";
    private static final String UPDATE = "UPDATE projects SET balance = ? WHERE name = ?;";
    private static final String DELETE = "DELETE FROM projects WHERE id = ?;";
    private static final String CLEAN = "TRUNCATE projects;";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void createProject(Project project) {
        jdbcTemplate.update(CREATE, new Object[]{project.getName(), project.getBalance()});
    }

    @Override
    public void deleteProject(int id) {
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

    @Override
    public void updateProjectByName(int balance, String name) {
        jdbcTemplate.update(UPDATE, new Object[]{balance, name});
    }

    @Override
    public Optional<Project> getProjectById(int id) {
        return Optional.of(jdbcTemplate.queryForObject(READ, new Object[]{id}, new BeanPropertyRowMapper<>(Project.class)));
    }

    public void cleanTable() {
        jdbcTemplate.execute(CLEAN);
    }
}
