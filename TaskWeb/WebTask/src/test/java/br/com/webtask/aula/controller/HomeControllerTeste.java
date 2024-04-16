package br.com.webtask.aula.controller;

import br.com.webtask.aula.controller.service.TaskService;
import br.com.webtask.aula.domain.repo.TaskRepo;
import br.com.webtask.aula.domain.repo.UserRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
//@ExtendWith(SpringExtension.class)
@ActiveProfiles({"homolog"})
//@WebMvcTest(controllers = TaskController.class)
@AutoConfigureMockMvc
public class HomeControllerTeste {

    @Autowired
    private MockMvc request; // Status 200
    @MockBean
    private TaskService service;
    @MockBean
    UserRepo userRepo;
    @MockBean
    TaskRepo taskRepo;

    @Test
    @DisplayName("Testar login")
    public void teste001() throws Exception {

        ResultActions resultActions = request.perform(MockMvcRequestBuilders.get("/login")
                .param("username","123").param("passworld","123"));

        resultActions.andExpect(MockMvcResultMatchers.status().is(200))
                .andDo(MockMvcResultHandlers.print());

    }
}
