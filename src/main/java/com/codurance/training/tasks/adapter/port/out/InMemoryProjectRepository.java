package com.codurance.training.tasks.adapter.port.out;

import com.codurance.training.tasks.entity.Project;
import com.codurance.training.tasks.entity.Task;
import com.codurance.training.tasks.entity.TaskId;
import com.codurance.training.tasks.usecase.port.out.ProjectRepository;

import java.util.*;

public class InMemoryProjectRepository implements ProjectRepository {
    List<Project> projectList = new ArrayList<>();

    public void save(Project data) {
        Iterator it = projectList.iterator();
        while(it.hasNext()) {
            Project project = (Project) it.next();
            if(project.getProjectName().equals(data.getProjectName()))
                it.remove();
        }

        projectList.add(data);
    }

    @Override
    public void save(Task task) {
        for(Project project : projectList) {
            if(project.getProjectTasks().stream().anyMatch(t -> t.getId() == task.getId())){
                project.add(task);
                return;
            }
        }
    }

    @Override
    public Optional<Task> find(int id){
        return getAllTasks().stream().filter(task -> Objects.equals(task.getId(), TaskId.of(id))).findFirst();
    }

    @Override
    public Optional<Project> find(String projectName) {
        return projectList.stream().filter(project -> project.getProjectName().getName().equals(projectName)).findFirst();
    }

    @Override
    public List<Project> findAll() {
        return projectList;
    }

    private List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        for(Project project : projectList) {
            tasks.addAll(project.getProjectTasks());
        }
        return tasks;
    }
}
