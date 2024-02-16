package com.codurance.training.tasks.usecase.port.in;


import com.codurance.training.tasks.entity.Task;
import com.codurance.training.tasks.usecase.port.out.ProjectRepository;

import java.util.Optional;

public class CheckTaskUseCase {
    private final ProjectRepository projectRepository;

    public CheckTaskUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    public void execute(String idString) {
        int id = Integer.parseInt(idString);
        Optional<Task> optTask = projectRepository.find(id);
        if (!optTask.isPresent()){
            return;
        }
        Task task = optTask.get();
        task.setDone(true);
        projectRepository.save(task);
    }
}