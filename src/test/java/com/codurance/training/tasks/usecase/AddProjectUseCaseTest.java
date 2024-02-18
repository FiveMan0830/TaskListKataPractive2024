package com.codurance.training.tasks.usecase;

import com.codurance.training.tasks.adapter.port.out.InMemoryProjectRepository;
import com.codurance.training.tasks.framework.ProjectStore;
import com.codurance.training.tasks.framework.TaskStore;
import com.codurance.training.tasks.usecase.port.in.project.add.AddProjectUseCase;
import com.codurance.training.tasks.usecase.port.out.repository.ProjectRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddProjectUseCaseTest {
    private ProjectRepository repository;
    @Before
    public void setUp() {
        ProjectStore projectStore = new ProjectStore(new HashMap<>());
        TaskStore taskStore = new TaskStore(new HashMap<>());
        repository = new InMemoryProjectRepository(projectStore, taskStore);
    }

    @Test
    public void add_project_test() {
        AddProjectUseCase addProjectUseCase = new AddProjectUseCase(repository);

        addProjectUseCase.execute("test");

        assertEquals(1, repository.findAll().size());
        assertTrue(repository.find("test").isPresent());
    }
}