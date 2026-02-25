package com.botaro.portifolio.service;

import com.botaro.portifolio.api.dto.ProjectRequest;
import com.botaro.portifolio.api.dto.ProjectResponse;
import com.botaro.portifolio.domain.Project;
import com.botaro.portifolio.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository   projectRepository;

    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public List<ProjectResponse> list(){
        return projectRepository.findAll().stream()
                .map(p -> new ProjectResponse(p.getId(), p.getTitle(), p.getDescription()))
                .toList();
    }

    public Optional<Project> getProjectById(@PathVariable long id) {
        return projectRepository.findById(id);
    }
    public Project create(@RequestBody ProjectRequest request){
        Project project = new Project(
                request.title(),
                request.description());
        return projectRepository.save(project);
    }
    public Project update(@RequestBody ProjectResponse request){
        Project project = projectRepository.findById(request.id())
                .orElseThrow(() -> new RuntimeException("Id nao encontrado" + request.id()));

        project.setTitle(request.title());
        project.setDescription(request.description());

        return projectRepository.save(project);
    }
    public void delete(@PathVariable long id){
        projectRepository.deleteById(id);
    }
}
