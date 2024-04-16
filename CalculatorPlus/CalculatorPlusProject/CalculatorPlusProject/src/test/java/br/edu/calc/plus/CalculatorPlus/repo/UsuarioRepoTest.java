package br.edu.calc.plus.CalculatorPlus.repo;

import br.edu.calc.plus.domain.Usuario;
import br.edu.calc.plus.repo.UsuarioRepo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class UsuarioRepoTest {

    public UsuarioRepoTest() {
    }

    @Autowired
    private UsuarioRepo usuarioRepo;

    /*Codificação da senha do usuário*/
    private static PasswordEncoder password = new BCryptPasswordEncoder();
    private Usuario usuario01;
    private Usuario usuario02;

    @BeforeEach
    @Transactional
    public void setUp() {
        /*Criando um usuário para insert no banco*/
        usuario01 = new Usuario(null, "Juan Pablo", "juan", "juan@teste", password.encode( "123456" ), "JF",
                LocalDate.now().minusYears(30));

        usuario02 = new Usuario(null, "Arthur Fernades", "arthur", "arthur@teste", password.encode( "123456" ), "JF",
                LocalDate.now().minusYears(30));

        /*Salvando o usuário criado no banco*/
        usuarioRepo.save(usuario01);
        usuarioRepo.save(usuario02);
    }

    @AfterEach
    public void tearDown() {
        /*Deletando o usuário criado no banco*/
        usuarioRepo.deleteAll();
    }

    @Test
    @DisplayName("Verifica se existe o usuário no banco")
    public void Teste001(){
        Usuario usuarioEsperado = usuario01;
        Usuario usuarioObtido = usuarioRepo.findByNome("Juan Pablo").get();
        assertEquals(usuarioEsperado,usuarioObtido);
    }

    @Test
    @DisplayName("Verifica se existe o email no banco")
    public void Teste002(){
        Usuario loginEsperado = usuario01;
        Usuario loginObtido = usuarioRepo.findByLogin("juan").get();
        assertEquals(loginEsperado,loginObtido);
    }

    @Test
    @DisplayName("Verifica a quantidade de usuários salvo no banco")
    public void Teste003(){
        long quantidadeEsperada = 2;
        long quantidadeObtida = usuarioRepo.count();
        assertEquals(quantidadeEsperada,quantidadeObtida);
    }

}
