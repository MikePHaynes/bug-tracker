package com.mph.bugtracker.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with ID: " + id));
    }

    public Project updateProjectById(Long id, Project updatedProject) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with ID: " + id));

        existingProject.setProjectName(updatedProject.getProjectName());
        existingProject.setProjectDescription(updatedProject.getProjectDescription());

        projectRepository.save(existingProject);
        return existingProject;
    }

    public void deleteProjectById(Long id) {
        projectRepository.deleteById(id);
    }
}
