package com.codurance.training.tasks.usecase;

import com.codurance.training.tasks.adapter.port.out.InMemoryProjectRepository;
import com.codurance.training.tasks.adapter.port.out.ProjectStore;
import com.codurance.training.tasks.adapter.port.out.TaskStore;
import com.codurance.training.tasks.framework.persistant.ProjectMemoryStore;
import com.codurance.training.tasks.framework.persistant.TaskMemoryStore;
import com.codurance.training.tasks.usecase.port.in.project.add.AddProjectUseCase;
import com.codurance.training.tasks.usecase.port.in.task.add.AddTaskUseCase;
import com.codurance.training.tasks.usecase.port.in.task.check.CheckTaskUseCase;
import com.codurance.training.tasks.usecase.port.in.task.due.DueTaskUseCase;
import com.codurance.training.tasks.usecase.port.out.TaskStatusData;
import com.codurance.training.tasks.usecase.port.out.repository.ProjectRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DueTaskUseCaseTest {
    private ProjectRepository repository;
    @Before
    public void setUp() {
        ProjectStore projectStore = new ProjectMemoryStore(new HashMap<>());
        TaskStore taskStore = new TaskMemoryStore(new HashMap<>());
        repository = new InMemoryProjectRepository(projectStore, taskStore);
    }

    @Test
    public void check_task_in_project_test() {
        AddProjectUseCase addProjectUseCase = new AddProjectUseCase(repository);
        addProjectUseCase.execute("test");
        AddTaskUseCase addTaskUseCase = new AddTaskUseCase(repository);
        addTaskUseCase.execute("test", "First task", "T01");

        DueTaskUseCase dueTaskUseCase = new DueTaskUseCase(repository);
        dueTaskUseCase.execute("T01", "1999-02-21");

        assertTrue(repository.findTask("T01").isPresent());
        assertEquals("1999-02-21", repository.findTask("T01").get().getDueDate().value());
    }
}
