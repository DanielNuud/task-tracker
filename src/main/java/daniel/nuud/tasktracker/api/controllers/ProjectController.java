package daniel.nuud.tasktracker.api.controllers;

import daniel.nuud.tasktracker.api.dto.ProjectDto;
import daniel.nuud.tasktracker.api.exception.BadRequestException;
import daniel.nuud.tasktracker.api.exception.NotFoundException;
import daniel.nuud.tasktracker.api.factories.ProjectDtoFactory;
import daniel.nuud.tasktracker.store.entities.ProjectEntity;
import daniel.nuud.tasktracker.store.repositories.ProjectRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class ProjectController {

    ProjectDtoFactory factory;

    ProjectRepository projectRepository;

    public static final String CREATE_PROJECT = "/api/projects";
    public static final String EDIT_PROJECT = "/api/projects/{project_id}";

    @PostMapping(CREATE_PROJECT)
    public ProjectDto createProject(@RequestParam String name) {

        if (name.trim().isEmpty()) {
            throw new BadRequestException("Project name cannot be empty");
        }

        projectRepository
                .findByName(name)
                .ifPresent(project -> {
                    throw new BadRequestException(String.format("Project \"%s\" already exists ", name));
                });

        ProjectEntity project = projectRepository.saveAndFlush(
            ProjectEntity.builder()
                    .name(name)
                    .build()
        );

        return factory.makeProjectDto(project);
    }

    @PatchMapping(EDIT_PROJECT)
    public ProjectDto editProject(
            @PathVariable("project_id") Long projectId,
            @RequestParam String name) {


        ProjectEntity project = projectRepository
                .findById(projectId)
                .orElseThrow(() -> new NotFoundException(String.format("Project with \"%d\" doesn't exists ", projectId)
                ));

        projectRepository
                .findByName(name)
                .filter(anotherProject -> !Objects.equals(anotherProject.getId(), projectId))
                .ifPresent(anotherProject -> {
                    throw new BadRequestException(String.format("Project \"%s\" already exists ", name));
                });

        project.setName(name);

        project = projectRepository.saveAndFlush(project);

        return factory.makeProjectDto(project);
    }


}
