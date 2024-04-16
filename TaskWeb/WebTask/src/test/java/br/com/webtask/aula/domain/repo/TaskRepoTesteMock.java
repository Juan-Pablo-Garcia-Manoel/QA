package br.com.webtask.aula.domain.repo;

import br.com.webtask.aula.domain.model.Task;
import br.com.webtask.aula.domain.model.UserClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class TaskRepoTesteMock {

    @MockBean
    private TaskRepo taskRepo;
    @MockBean
    private UserRepo userRepo;

    public TaskRepoTesteMock(){}

    private static UserClient usuario01 = new UserClient(null,
            "Juan",
            "000.000.000-00",
            "juan@juan",
            "123",
            true,
            null);

    private static Task task01 =  new Task(
            null,
            "Teste Mock",
            LocalDate.of(2023,10,9),
            LocalDate.of(2023,10,15),
            usuario01
    );

    private static Task task02 =  new Task(
            null,
            "Teste Mock",
            LocalDate.of(2023,10,9),
            LocalDate.of(2023,10,31),
            usuario01
    );

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Testa busca de uma tarefa")
    public void Teste001(){
        List<Task> tarefasRetornadas = Arrays.asList(task01, task02);
        Task taskEsperada = task01;

        Mockito.when(taskRepo.findByTaskDescription("Teste Mock")).thenReturn(Collections.singletonList(task01));

        Task taskObtida = taskRepo.findByTaskDescription("Teste Mock").get(0);
        assertEquals(taskEsperada,taskObtida);
    }

    @Test
    @DisplayName("Testa busca de uma segunda tarefa")
    public void Teste002(){
        List<Task> tarefasRetornadas = Arrays.asList(task01, task02);
        Task taskEsperada = task02;

        Mockito.when(taskRepo.findByTaskDescription("Teste Mock")).thenReturn(tarefasRetornadas);

        Task taskObtida = taskRepo.findByTaskDescription("Teste Mock").get(1);
        assertEquals(taskEsperada, taskObtida);
    }


}
