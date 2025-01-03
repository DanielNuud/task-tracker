package daniel.nuud.tasktracker.api.factories;

import daniel.nuud.tasktracker.api.dto.TaskDto;
import daniel.nuud.tasktracker.store.entities.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskDtoFactory {

    public TaskDto makeTaskDto(TaskEntity entity) {
        return TaskDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
