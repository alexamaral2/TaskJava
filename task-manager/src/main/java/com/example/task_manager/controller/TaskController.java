package com.example.task_manager.controller;

import com.example.task_manager.model.Task;
import com.example.task_manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Método para listar tarefas e exibir na página
    @GetMapping
    public String getAllTasks(Model model) {
        model.addAttribute("tasks", taskService.findAllTasks());
        return "task-list";
    }

    // Método para abrir formulário de criação
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new Task());
        return "task-form";
    }

    // Método para salvar nova tarefa
    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task) {
        taskService.saveTask(task);
        return "redirect:/tasks";
    }

    // Método para abrir formulário de edição
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Task task = taskService.findTaskById(id);
        model.addAttribute("task", task);
        return "task-form";
    }

    // Método para atualizar tarefa existente
    @PostMapping("/edit/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task taskDetails) {
        taskService.updateTask(id, taskDetails);
        return "redirect:/tasks";
    }

    // Método para excluir tarefa
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}
