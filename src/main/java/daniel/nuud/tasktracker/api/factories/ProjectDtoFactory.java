package daniel.nuud.tasktracker.api.factories;

import daniel.nuud.tasktracker.api.dto.ProjectDto;
import daniel.nuud.tasktracker.store.entities.ProjectEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectDtoFactory {

    public ProjectDto makeProjectDto(ProjectEntity entity) {
        return ProjectDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
