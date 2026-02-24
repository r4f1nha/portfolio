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

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("/getAllProjects")
    public List<ProjectResponse> list(){
        return projectRepository.findAll().stream()
                .map(p -> new ProjectResponse(p.getId(), p.getTitle(), p.getDescription()))
                .toList();
    }

    @GetMapping("/{id}")
    public Optional<Project> getProjectById(@PathVariable  long id){
        return projectRepository.findById(id);
    }

    @PostMapping("/create")
    public Project create(@RequestBody ProjectRequest request){
            Project project = new Project(
                    request.title(),
                    request.description());
            return projectRepository.save(project);
    }
    @PutMapping("/update")
    public Project update(@RequestBody ProjectResponse request){
        Project project = projectRepository.findById(request.id())
                .orElseThrow(() -> new RuntimeException("Id nao encontrado" + request.id()));

           project.setTitle(request.title());
           project.setDescription(request.description());

        return projectRepository.save(project);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        try {
            projectRepository.deleteById(id);
            System.out.println("Projeto deletado com sucesso.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
