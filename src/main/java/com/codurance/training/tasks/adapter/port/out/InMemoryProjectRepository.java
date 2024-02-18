package com.codurance.training.tasks.adapter.port.out;

import com.codurance.training.tasks.usecase.port.out.*;
import com.codurance.training.tasks.usecase.port.out.repository.ProjectRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryProjectRepository implements ProjectRepository {
    private final ProjectStore projectStore;
    private final TaskStore taskStore;

    public InMemoryProjectRepository(ProjectStore projectStore, TaskStore taskStore) {
        this.projectStore = projectStore;
        this.taskStore = taskStore;
    }

    public void save(ProjectData data) {
        projectStore.save(new ProjectPO(
                data.getProjectName().value(),
                data.getProjectTasks().stream().map(taskData -> taskData.getId().value()).collect(Collectors.toList())
        ));

        for(TaskData taskData: data.getProjectTasks()) {
            taskStore.save(new TaskPO(
                    taskData.getId().value(),
                    taskData.getDescription().value(),
                    taskData.getStatus().equals(TaskStatusData.Checked), taskData.getDueDate().value()));
        }
    }

    public void save(TaskData task) {
        taskStore.save(new TaskPO(
                task.getId().value(),
                task.getDescription().value(),
                task.getStatus().equals(TaskStatusData.Checked),
                task.getDueDate().value()));
    }

    public Optional<TaskData> findTask(String id){
        Optional<TaskPO> taskPO = taskStore.find(id);
        if(!taskPO.isPresent()) return Optional.empty();
        TaskPO task = taskPO.get();
        return Optional.of(new TaskData(
                TaskIdData.of(task.getId()),
                TaskDescriptionData.of(task.getDescription()),
                DueDateData.of(task.getDueDate()), task.isCheck() ? TaskStatusData.Checked : TaskStatusData.Unchecked
        ));
    }

    public Optional<ProjectData> findProject(String projectName) {
        Optional<ProjectPO> projectPO = projectStore.find(projectName);
        if(!projectPO.isPresent()) return Optional.empty();
        ProjectPO project = projectPO.get();

        return Optional.of(new ProjectData(
                ProjectNameData.of(projectName),
                getTasks(project.getTaskIdList())
        ));
    }

    public List<ProjectData> findAll() {
        List<ProjectData> result = new ArrayList<>();
        for(ProjectPO po : projectStore.findAll()) {
            result.add(new ProjectData(
                    ProjectNameData.of(po.getName()),
                    getTasks(po.getTaskIdList())
            ));
        }
        return result;
    }

    private Set<TaskData> getTasks(List<String> ids) {
        Set<TaskData> projectTasks = new HashSet<>();

        for(String id : ids) {
            findTask(id).ifPresent(projectTasks::add);
        }

        return projectTasks;
    }
}
