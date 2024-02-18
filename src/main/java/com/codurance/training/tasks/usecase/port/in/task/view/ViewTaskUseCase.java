//package com.codurance.training.tasks.usecase.port.in.task.view;
//
//import com.codurance.training.tasks.entity.Task;
//import com.codurance.training.tasks.entity.TaskStatus;
//import com.codurance.training.tasks.usecase.TaskNotFoundException;
//import com.codurance.training.tasks.usecase.port.out.TaskDataMapper;
//import com.codurance.training.tasks.usecase.port.out.repository.ProjectRepository;
//
//import static java.lang.String.format;
//
//public class ViewTaskUseCase {
//    private final ProjectRepository projectRepository;
//
//    public ViewTaskUseCase(ProjectRepository projectRepository) {
//        this.projectRepository = projectRepository;
//    }
//
//    public void execute(String idString) {
//        if (!projectRepository.findTask(idString).isPresent())
//            throw new TaskNotFoundException(format(TaskNotFoundException.TASK_NOT_FOUND, idString));
//
//        Task task = TaskDataMapper.toDomain(projectRepository.findTask(idString).get());
//        task.setStatus(TaskStatus.Unchecked);
//
//        projectRepository.save(TaskDataMapper.toData(task));
//    }
//}
