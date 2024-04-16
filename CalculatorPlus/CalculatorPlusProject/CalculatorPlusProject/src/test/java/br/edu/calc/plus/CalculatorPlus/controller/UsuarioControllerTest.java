package br.edu.calc.plus.CalculatorPlus.controller;

import br.edu.calc.plus.controller.UsuarioController;
import br.edu.calc.plus.domain.dto.UserDTO;
import br.edu.calc.plus.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para UsuarioController")
public class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private RedirectAttributes redirectAttributes;

    @Mock
    private Authentication authentication;

    @Mock
    private ModelMap modelMap;

    @Test
    @DisplayName("Verifica se salva o usuário com sucesso")
    public void Teste001() {
        UserDTO usuario = new UserDTO();
        usuario.setNome("testuser");
        usuario.setSenha("testpassword");
        when(bindingResult.hasErrors()).thenReturn(false);
        when(passwordEncoder.encode(usuario.getSenha())).thenReturn("encodedPassword");
        String result = usuarioController.saveUser(usuario, bindingResult, redirectAttributes, modelMap, authentication);
        verify(usuarioService).save(any(UserDTO.class));
        verify(redirectAttributes).addFlashAttribute("success", "cadastro criado com sucesso.");
        assertEquals("redirect:/home", result);
    }
}
