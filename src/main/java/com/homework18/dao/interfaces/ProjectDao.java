package com.homework18.dao.interfaces;

import com.homework18.model.Project;

import java.util.Optional;

public interface ProjectDao {
    void createProject(Project project);

    void deleteProject(int id);

    void updateProjectByName(int balance, String name);

    Optional<Project> getProjectById(int id);
}
