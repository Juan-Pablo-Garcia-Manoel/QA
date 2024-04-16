package br.edu.calc.plus.CalculatorPlus.domain;

import br.edu.calc.plus.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class UsuarioTest {

    @Test
    @DisplayName("Verifica se a senha é válida")
    public void Teste001() {
        Usuario usuario = new Usuario();
        usuario.setSenha("Senha@123");
        assertTrue(usuario.senhaValida());
    }

    @Test
    @DisplayName("Verifica se a senha é inválida")
    public void Teste002() {
        Usuario usuario = new Usuario();
        usuario.setSenha("senha123");
        assertFalse(usuario.senhaValida());
    }

    @Test
    @DisplayName("Verifica se o login é válido")
    public void Teste003() {
        Usuario usuario = new Usuario();
        usuario.setLogin("usuario123");
        assertTrue(usuario.loginValida());
    }

    @Test
    @DisplayName("Verifica se o login é inválido")
    public void Teste004() {
        Usuario usuario = new Usuario();
        usuario.setLogin("user name");
        assertFalse(usuario.loginValida());
    }
}
