package com.gerenciador.controller;


import com.gerenciador.List.model.Task;
import com.gerenciador.List.service.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class TaskController {


    TaskService taskService;

    @ApiOperation(value = "Criar Tarefa")
    @ApiResponses( value ={
            @ApiResponse(code = 201, message = "Tarefa criada!"),
            @ApiResponse(code = 500, message = "Houve um erro ao criar a tarefa, verifique as informações")

    })

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        log.info("Criando uma nova tarefa com as informações [{}]", task);
        return taskService.createTask(task);
    }



    @ApiOperation(value = "Lista de tarefas")
    @ApiResponses( value ={
            @ApiResponse(code = 200, message = "Tarefas listadas com sucesso"),


    })
    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasks() {
        log.info("Lista de tarefas cadastradas");
        return taskService.listAllTasks();
    }

    @ApiOperation(value = "Buscando uma tarefa")
    @ApiResponses( value ={
            @ApiResponse(code = 200, message = "Tarefa encontrada com sucesso"),


    })
    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> getTaskById(@PathVariable (value = "id") Long id) {
        log.info("Buscando tarefa  [{}]", id);
        return taskService.findTaskById(id);
    }

    @ApiOperation(value = "Atualizar tarefa")
    @ApiResponses( value ={
            @ApiResponse(code = 200, message = "Tarefa atualizada")

    })
    @PutMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updateTaskById(@PathVariable (value = "id") Long id, @RequestBody Task task) {
        log.info("Atualizando a tarefa com id [{}] as novas informações são : [{}]",id, task);

        return taskService.updateTaskById(task,id);
    }


    @ApiOperation(value = "Excluir tarefa")
    @ApiResponses( value ={
            @ApiResponse(code = 204, message = "Tarefa excluida com sucesso"),


    })
    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteTaskById(@PathVariable (value = "id") Long id) {
        log.info("Excluir tarefas com o id [{}]", id);
        return taskService.deleteById(id);
    }


}
