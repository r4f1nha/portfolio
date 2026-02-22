package com.botaro.portifolio.api;

import com.botaro.portifolio.api.dto.ProjectRequest;
import com.botaro.portifolio.api.dto.ProjectResponse;
import com.botaro.portifolio.domain.Project;
import org.springframework.web.bind.annotation.*;
import com.botaro.portifolio.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    Project project;

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public List<ProjectResponse> list(){
        return projectRepository.findAll().stream()
                .map(p -> new ProjectResponse(p.getId(), p.getTitle(), p.getDescription()))
                .toList();
    }

    @GetMapping("/{id}")
    public Optional<Project> getProjectById(@PathVariable  long id){
        return projectRepository.findById(id);
    }

    @PostMapping
    public Project create(@RequestBody ProjectRequest request){
            Project project = new Project(
                    request.title(),
                    request.description());
            return projectRepository.save(project);
    }
}
