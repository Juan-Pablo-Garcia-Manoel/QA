package br.edu.calc.plus.CalculatorPlus.repo;

import br.edu.calc.plus.domain.Usuario;
import br.edu.calc.plus.repo.UsuarioRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UsuarioRepoTesteMock {

    @MockBean
    private UsuarioRepo usuarioRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Testa busca de um usuário por login")
    public void Teste001() {
        List<Usuario> usuariosRetornados = Arrays.asList(
                new Usuario(1, "John", "john123", "john@example.com", "password", "City", LocalDate.of(1990, 1, 1)),
                new Usuario(2, "Jane", "jane456", "jane@example.com", "password", "City", LocalDate.of(1995, 5, 5))
        );
        Usuario usuarioEsperado = usuariosRetornados.get(0);

        when(usuarioRepo.findByLogin("john123")).thenReturn(Optional.of(usuarioEsperado));

        Optional<Usuario> usuarioObtido = usuarioRepo.findByLogin("john123");
        assertTrue(usuarioObtido.isPresent());
        assertEquals(usuarioEsperado, usuarioObtido.get());
    }
}
