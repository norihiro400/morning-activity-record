package com.example.todo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.todo.repository.TaskDetailRepository;
import com.example.todo.repository.TaskRepository;
import com.example.todo.service.tasks.TaskEntity;
import com.example.todo.service.tasks.TaskService;

public class TaskServicetest {
    private TaskRepository taskRepository;
    private TaskDetailRepository taskDetailRepository;
    private TaskService taskService;

    @BeforeEach
    void setUp(){
        taskRepository = Mockito.mock(TaskRepository.class);
        taskDetailRepository = Mockito.mock(TaskDetailRepository.class);
        taskService = new TaskService(taskRepository, taskDetailRepository);
    }

    // createtaskメソッドのテスト
    @Test
    void testCreateTask_shouldSaveTaskEntity(){
        TaskEntity mockEntity = new TaskEntity();

        taskService.createtask(mockEntity);
        verify(taskRepository,times(1)).save(mockEntity);
        
    }

    @Test
    void testFindAll_shouldReturnAllTasks(){
        TaskEntity task1 = new TaskEntity();
        task1.setTask("ランニング");
        TaskEntity task2 = new TaskEntity();
        task2.setTask("読書");

        List<TaskEntity> dummyList = Arrays.asList(task1,task2);

        // リポジトリのモック設定
        when(taskRepository.findAll()).thenReturn(dummyList);        

        List<TaskEntity> result = taskService.findAll();


        // 結果をアサート
        assertEquals(2, result.size());
        assertEquals("ランニング", result.get(0).getTask());
        assertEquals("読書", result.get(1).getTask());

        // モックが呼ばれたか確認
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void testFindById_shouldReturnTaskEntityIfExists(){
        // 準備
        TaskEntity task = new TaskEntity();
        task.setId(1L);
        task.setTask("筋トレ");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        // 実行
        Optional<TaskEntity> result = taskService.findById(1L);

        // 結果の検証
        assertTrue(result.isPresent());
        assertEquals("筋トレ", result.get().getTask());
        verify(taskRepository,times(1)).findById(1L);
    }

    @Test
    void testFindByDone_shouldReturnTaskEntitysByDoneFlag(){
        // 準備
        TaskEntity task1 = new TaskEntity();
        task1.setDone(false);
        TaskEntity task2 = new TaskEntity();
        task2.setDone(true);

        when(taskRepository.findByIsDone(false)).thenReturn(Arrays.asList(task1));
        when(taskRepository.findByIsDone(true)).thenReturn(Arrays.asList(task2));

        // 実行
        List<TaskEntity> result1 = taskService.findByDone(false);
        List<TaskEntity> result2 = taskService.findByDone(true);

        // 結果の検証
        assertEquals(1, result1.size());
        assertEquals(false, result1.get(0).isDone());

        assertEquals(1, result2.size());
        assertEquals(true, result2.get(0).isDone());

        verify(taskRepository,times(1)).findByIsDone(false);
        verify(taskRepository,times(1)).findByIsDone(true);
    
    }

    @Test
    void testDeleteById_shouldDeleteTaskEntityById(){
        // 準備
        Long taskId = 1L;

        // 実行
        taskService.deleteById(taskId);

        // 結果の検証
        verify(taskRepository,times(1)).deleteById(taskId);
    }

    @Test
    void testSetIdDone_shouldUpdateTaskDoneStatus(){
        // 準備
        Long taskId = 1L;

        // 実行
        taskService.setidDone(taskId);

        // 結果の検証
        verify(taskRepository,times(1)).setisDoneById(taskId);
    }
}
