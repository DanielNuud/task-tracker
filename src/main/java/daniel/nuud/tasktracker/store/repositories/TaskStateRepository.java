package daniel.nuud.tasktracker.store.repositories;

import daniel.nuud.tasktracker.store.entities.TaskStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStateRepository extends JpaRepository<TaskStateEntity, Long> {
}
