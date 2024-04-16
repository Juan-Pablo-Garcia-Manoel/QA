package br.com.webtask.aula.domain.model.controller;

import br.com.webtask.aula.controller.UserController;
import br.com.webtask.aula.controller.service.UserService;
import br.com.webtask.aula.domain.model.UserClient;
import br.com.webtask.aula.util.LogadoUtil;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserControllerTest{
    @InjectMocks
    private UserController userController;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserService userService;
    @Mock
    private LogadoUtil logadoUtil;
    @BeforeEach
    public void setup(){ MockitoAnnotations.initMocks(this); }

    @Test
    public void testaEditar() {
        UserClient userClient = new UserClient();
        userClient.setId(1L);
        userClient.setName("admin");
        userClient.setSenha("123");

        Authentication authentication = Mockito.mock(Authentication.class);

        when(logadoUtil.getIdUserLogado(authentication)).thenReturn(1L);
        when(userService.buscarUsuario(1L)).thenReturn(userClient);

        ModelMap model = new ModelMap();

        String view = userController.editar(authentication, model);

        UserClient usuario = (UserClient) model.get("usuario");
        assertEquals(userClient, usuario);

        assertEquals("edit", view);
    }
}