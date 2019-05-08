package com.homework18.dao.impls;

import com.homework18.model.Project;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ProjectMySqlDAOImplTest {
    private ClassPathXmlApplicationContext context;
    private ProjectMySqlDAOImpl projectService;

    @Before
    public void setup() {
        context = new ClassPathXmlApplicationContext("context.xml");
        projectService = context.getBean("mySqlDAO", ProjectMySqlDAOImpl.class);
    }

    @Test
    public void createProject() {
        Project project = new Project();
        project.setName("DC");
        project.setId(2);
        projectService.createProject(project);
        Project projectExpected = projectService.getProjectById(2).get();
        assertEquals(project, projectExpected);
    }

    @Test
    public void deleteProject() {
        Project project = new Project();
        project.setName("Dell");
        project.setId(2);
        projectService.createProject(project);
        projectService.deleteProject(2);
        assertFalse(projectService.getProjectById(2).isPresent());
    }

    @Test
    public void updateProjectByName() {
        Project project = new Project();
        project.setName("DC");
        project.setId(2);
        projectService.updateProjectByName(11111, "DC");
        Project projectExpected = projectService.getProjectById(2).get();
        assertEquals(projectExpected.getBalance(), 11111);
    }

    @Test
    public void getProjectById() {
        Project project = new Project();
        project.setName("DC");
        project.setId(2);
        Project projectExpected = projectService.getProjectById(2).get();
        assertEquals(project, projectExpected);
    }

    @After
    public void end() {
        context.close();
    }
}
