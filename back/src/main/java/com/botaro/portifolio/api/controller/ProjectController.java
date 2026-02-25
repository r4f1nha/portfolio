package com.botaro.portifolio.api.controller;

import com.botaro.portifolio.api.dto.ProjectRequest;
import com.botaro.portifolio.api.dto.ProjectResponse;
import com.botaro.portifolio.domain.Project;
import com.botaro.portifolio.service.ProjectService;
import org.springframework.web.bind.annotation.*;
import com.botaro.portifolio.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/getAllProjects")
    public List<ProjectResponse> list(){
        return projectService.list();
    }

    @GetMapping("/{id}")
    public Optional<Project> getProjectById(@PathVariable  long id){
        return projectService.getProjectById(id);
    }

    @PostMapping("/create")
    public Project create(@RequestBody ProjectRequest request){
            return projectService.create(request);
    }
    @PutMapping("/update")
    public Project update(@RequestBody ProjectResponse request){
        return projectService.update(request);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        projectService.delete(id);
    }
}
