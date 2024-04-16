package br.com.webtask.aula.controller.service;

import br.com.webtask.aula.domain.model.Task;
import br.com.webtask.aula.domain.repo.TaskRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("homolog")
public class TaskServiceTeste {

    @MockBean
    TaskRepo taskRepo;

    @InjectMocks
    TaskService taskService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Testa ordem de ID do usuário por data planejada Descrição")
    public void Teste001(){
        ArrayList<Task> lista = new ArrayList<>();
        lista.add(new Task(1l,
                "Teste Mock",
                LocalDate.of(2023,10,9),
                LocalDate.of(2023,10,15),
                null));
        Mockito.when(taskRepo.findByUserIdOrderByPlannedDateDesc(1)).thenReturn(lista);

        List<Task> lista1 = taskService.minhaLista(1);

        assertEquals(lista,lista1);
    }
//
}
