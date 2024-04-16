package br.com.webtask.aula.domain.model.controller;

import br.com.webtask.aula.controller.HomeController;
import br.com.webtask.aula.util.LogadoUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomeControllerTest{
    @InjectMocks
    private HomeController homeController;
    @Mock
    private LogadoUtil logadoUtil;
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testaHome(){
        Authentication authentication = Mockito.mock(Authentication.class);
        ModelMap modelMap = new ModelMap();
        String usuario = "admin";

        Mockito.when(logadoUtil.getNomeUserLogado(authentication)).thenReturn(usuario);

        String nome = homeController.home(authentication, modelMap);

        assertEquals("home", nome);

        assertEquals(usuario, modelMap.get("nome"));
    }

    @Test
    public void testLogin(){
        String nome = homeController.login();
        assertEquals("login", nome);
    }

    @Test
    public void testaLoginError() {
        Model model = Mockito.mock(Model.class);
        String nome = homeController.loginError(model);

        assertEquals("login", nome);

        Mockito.verify(model).addAttribute("loginError", true);
    }
}