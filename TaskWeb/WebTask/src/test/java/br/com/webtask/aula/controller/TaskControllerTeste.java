package br.com.webtask.aula.controller;

import br.com.webtask.aula.controller.service.TaskService;
import br.com.webtask.aula.domain.model.UserClient;
import br.com.webtask.aula.domain.repo.TaskRepo;
import br.com.webtask.aula.domain.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//@ExtendWith(SpringExtension.class)
@ActiveProfiles({"homolog"})
@AutoConfigureMockMvc
@SpringBootTest
//@WebMvcTest(TaskControllerTeste.class)

//https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status

public class TaskControllerTeste {

    @Autowired
    private MockMvc request; // Status 200

    @MockBean
    private TaskService service;
    @MockBean
    TaskRepo taskRepo;

    @Test
    @DisplayName("Testa proteção página")
    public void teste001() throws Exception {
        ResultActions resultActions = request.perform(MockMvcRequestBuilders.get("/task"));
        resultActions.andExpect(MockMvcResultMatchers.status().is(302))
                .andDo(MockMvcResultHandlers.print());
    }

}

