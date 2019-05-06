package com.homework18.dao.impls;

import com.homework18.model.Project;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ProjectMySqlDAOImplTest {
    ClassPathXmlApplicationContext context;
    ProjectMySqlDAOImpl projectService;

    @Before
    public void setup() {
        context = new ClassPathXmlApplicationContext("context.xml");
        projectService = context.getBean("mySqlDAO", ProjectMySqlDAOImpl.class);
    }

    @Test
    public void createProject() {
        Project project = context.getBean("project", Project.class);
        project.setName("DC");
        projectService.createProject(project);
        Project projectExpected = projectService.getProjectById(2).get();
        assertEquals(project, projectExpected);
    }

    @Test
    public void deleteProject() {
        Project project = context.getBean("project", Project.class);
        project.setName("Dell");
        projectService.createProject(project);
        projectService.deleteProject(2);
        assertFalse(projectService.getProjectById(2).isPresent());
    }

    @Test
    public void updateProjectByName() {
        Project project = context.getBean("project", Project.class);
        project.setName("DC");
        projectService.createProject(project);
        projectService.updateProjectByName(11111, "DC");
        Project projectExpected = projectService.getProjectById(2).get();
        assertEquals(projectExpected.getBalance(), 11111);
    }

    @Test
    public void getProjectById() {
        Project project = context.getBean("project", Project.class);
        project.setName("DC");
        projectService.createProject(project);
        Project projectExpected = projectService.getProjectById(2).get();
        assertEquals(project, projectExpected);
    }

    @After
    public void end() {
        context.close();
    }
}
