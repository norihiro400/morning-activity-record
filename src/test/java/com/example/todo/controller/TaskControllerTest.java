package com.example.todo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import com.example.todo.controller.tasks.TasksController;
import com.example.todo.service.login.UserEntity;
import com.example.todo.service.login.UserService;
import com.example.todo.service.tasks.TaskEntity;
import com.example.todo.service.tasks.TaskService;

@AutoConfigureMockMvc 
@WebMvcTest(TasksController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private TaskService taskService;

    @MockBean
    private UserService userService;

    @BeforeEach
    void setUpSecurityContext(){
        // テストユーザー
        UserEntity user = new UserEntity();
        user.setUsername("testuser");
        user.setId(1L);

        when(userService.getusername()).thenReturn("testuser");
        when(userService.findByUsername("testuser")).thenReturn(user);
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testGetView() throws Exception{
        when(taskService.findByDate(any(),eq(1L))).thenReturn(List.of());

        mockMvc.perform(get("/tasks")) // ⑭ GET /tasks にアクセスする（擬似的にHTTPリクエストを送る）
                .andExpect(status().isOk()) // ⑮ ステータスコードが200 OKであることを検証
                .andExpect(view().name("tasks/tasks")) // ⑯ 返ってくるビュー名が "tasks/tasks" であることを検証
                .andExpect(model().attributeExists("taskForm")) // ⑰ modelに "taskForm" という属性があることを確認
                .andExpect(model().attributeExists("tomorror_task")) // ⑱ "tomorror_task" もあることを確認
                .andExpect(model().attribute("username", "testuser")); // ⑲ username属性が "testuser" であることを確認
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testDeleteTask_shouldDeleteTask() throws Exception {
        // 実行
        mockMvc.perform(post("/tasks/delete/1").with(csrf()))
        .andExpect(status().is3xxRedirection()) // 302などのリダイレクトステータス
        .andExpect(redirectedUrl("/tasks")); // リダイレクト先が正しいか

        // 削除が一回呼ばれるか
        verify(taskService,times(1)).deleteById(1L);

    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testInputTask_shouldCreateTaskAndRedirect() throws Exception {
        // モックユーザーの設定
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername("testuser");

        when(userService.findByUsername("testuser")).thenReturn(user);

        // フォームデータの送信
        mockMvc.perform(post("/tasks/input").param("task","ランニング")
                        .param("label","日常系")
                        .with(csrf()))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/tasks"));
        
        // バリデーションチェックエラー時のテスト
        mockMvc.perform(post("/tasks/input").param("task","")
                        .param("label","日常系")
                        .with(csrf()))
                    .andExpect(status().isOk())
                    .andExpect(view().name("tasks/tasks"))
                    .andExpect(model().attributeHasFieldErrors("taskForm", "task"))
                    .andExpect(model().attributeExists("tomorror_task"));

        verify(taskService,times(1)).createtask(any(TaskEntity.class));
    }

    
}
