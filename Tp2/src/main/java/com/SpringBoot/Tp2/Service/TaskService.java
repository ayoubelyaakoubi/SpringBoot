package org.springboot.tp2.Service;

import java.util.List;

import org.springboot.tp2.DAO.TaskRepository;
import org.springboot.tp2.Entity.Task;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    // Task createTask(Task task)`: Méthode qui crée une nouvelle tâche.
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // `List<Task> getAllTasks()`: Méthode qui récupère toutes les tâches.
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // `Task getTaskById(Long id)`: Méthode qui récupère une tâche par ID.
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    // `Task updateTask(Long id, Task updatedTask)`: Méthode qui modifie une tâche
    // déjà existante par ID.
    public Task updateTask(Long id, Task updatedTask) {
        Task task = getTaskById(id);
        if (task != null) {

            task.setDescription(updatedTask.getDescription());
            return taskRepository.save(task);
        }
        return null;
    }

    // `void deleteTask(Long id)`: Méthode qui supprime une tâche par ID.
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);

    }

}
