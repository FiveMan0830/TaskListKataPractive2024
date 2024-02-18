package com.codurance.training.tasks.usecase;

import com.codurance.training.tasks.adapter.port.out.InMemoryProjectRepository;
import com.codurance.training.tasks.framework.persistant.ProjectStore;
import com.codurance.training.tasks.framework.persistant.TaskStore;
import com.codurance.training.tasks.usecase.port.in.project.add.AddProjectUseCase;
import com.codurance.training.tasks.usecase.port.in.task.add.AddTaskUseCase;
import com.codurance.training.tasks.usecase.port.in.task.check.CheckTaskUseCase;
import com.codurance.training.tasks.usecase.port.out.TaskStatusData;
import com.codurance.training.tasks.usecase.port.out.repository.ProjectRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckTaskUseCaseTest {
    private ProjectRepository repository;
    @Before
    public void setUp() {
        ProjectStore projectStore = new ProjectStore(new HashMap<>());
        TaskStore taskStore = new TaskStore(new HashMap<>());
        repository = new InMemoryProjectRepository(projectStore, taskStore);
    }

    @Test
    public void check_task_in_project_test() {
        AddProjectUseCase addProjectUseCase = new AddProjectUseCase(repository);
        addProjectUseCase.execute("test");
        AddTaskUseCase addTaskUseCase = new AddTaskUseCase(repository);
        addTaskUseCase.execute("test", "First task", 1);

        CheckTaskUseCase checkTaskUseCase = new CheckTaskUseCase(repository);
        checkTaskUseCase.execute(String.valueOf(1));

        assertTrue(repository.find(1).isPresent());
        assertEquals( TaskStatusData.Checked, repository.find(1).get().getStatus());
    }
}
