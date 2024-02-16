package com.codurance.training.tasks.usecase.port.in;

import com.codurance.training.tasks.entity.Task;
import com.codurance.training.tasks.usecase.port.out.ProjectRepository;


public class UncheckTaskUseCase {
    private final ProjectRepository projectRepository;

    public UncheckTaskUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    public void execute(String idString) {
        int id = Integer.parseInt(idString);
        Task task = projectRepository.find(id).get();
        task.setDone(false);
        projectRepository.save(task);
    }
}