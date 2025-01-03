package daniel.nuud.tasktracker.store.repositories;

import daniel.nuud.tasktracker.store.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
