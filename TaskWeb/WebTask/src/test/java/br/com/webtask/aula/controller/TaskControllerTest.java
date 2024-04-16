package br.com.webtask.aula.domain.model.controller;

import br.com.webtask.aula.controller.TaskController;
import br.com.webtask.aula.controller.service.TaskService;
import br.com.webtask.aula.domain.model.Task;
import br.com.webtask.aula.util.LogadoUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDate;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class TaskControllerTest {
    @InjectMocks
    private TaskController taskController;
    @Mock
    private TaskService taskService;
    //
    @Mock
    private Task task;
    @Mock BindingResult bindingResult;
    @Mock
    RedirectAttributes redirectAttributes;
    @Mock
    private ModelMap modelMap;
    @Mock
    private Authentication authentication;
    //
    @Mock
    private LogadoUtil logadoUtil;
    private MockMvc mockMvc;
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
    @BeforeEach
    public void setUp() {
        taskService = mock(TaskService.class);
        logadoUtil = mock(LogadoUtil.class);
        taskController = new TaskController();
        mockMvc = standaloneSetup(taskController).build();
    }

    @Test
    public void TestaTarefa(){
        Task tarefa = new Task();
        ModelMap modelMap = new ModelMap();

        String nome = taskController.task(tarefa, modelMap);

        assertEquals("task/newTask", nome);
    }
    @Test
    public void testaListaTarefas(){
        Authentication authentication = Mockito.mock(Authentication.class);
        ModelMap modelMap = new ModelMap();
        long id = 123L;

        Mockito.when(logadoUtil.getIdUserLogado(authentication)).thenReturn(id);

        String nome = taskController.listaTask(authentication, modelMap);

        assertEquals("task/listTask", nome);
    }
    @Test
    public void testaFechamentoTarefa() {
        Authentication authentication = Mockito.mock(Authentication.class);
        ModelMap modelMap = new ModelMap();
        long idTarefa = 456L;
        Task tarefa = new Task();

        Mockito.when(taskService.getTask(idTarefa)).thenReturn(tarefa);

        String nome = taskController.closeTask(idTarefa, authentication, modelMap);

        assertEquals("redirect:/task/list", nome);

        verify(taskService).getTask(idTarefa);

        assertEquals(LocalDate.now(), tarefa.getFinishedDate());
    }
    @Test
    public void testSaveTask() {
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.when(task.isDescriptionValid()).thenReturn(true);

        String resultado = taskController.saveTask(task, bindingResult, redirectAttributes, modelMap, authentication);

        verify(taskService, Mockito.times(1)).salvar(task);

        assertEquals("redirect:/home", resultado);

        verify(redirectAttributes, times(1)).addFlashAttribute(eq("success"), any(String.class));
    }
//@Test
//public void testSaveTask_success() throws Exception {
//    Task task = new Task();
//    task.setTaskDescription("Valid Description");
//    task.setPlannedDate(LocalDate.now());
//
//    when(logadoUtil.getIdUserLogado(authentication)).thenReturn(1L);
//
//    mockMvc.perform(post("/task")
//                    .flashAttr("task", task))
//            .andExpect(status().is3xxRedirection())
//            .andExpect(redirectedUrl("/home"))
//            .andExpect(flash().attribute("success", "Dados alterados com sucesso."));
//
//    verify(taskService, times(1)).salvar(task);
//}
}
