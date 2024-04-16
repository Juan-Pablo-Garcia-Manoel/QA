package br.com.webtask.aula.domain.repo;

import br.com.webtask.aula.domain.model.UserClient;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class UserRepoTesteMock {

    @MockBean
    private UserRepo userRepo;

    public UserRepoTesteMock(){

    }

    private static UserClient usuario01 = new UserClient(null,"Juan","000.000.000-00","juan@juan","123",true,null);

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Testa busca do nome")
    public void Teste001(){
        UserClient userEsperado = usuario01;

        Mockito.when(userRepo.findByName("Juan")).thenReturn(Optional.of(usuario01));

        UserClient userObtido = userRepo.findByName("Juan").get();
        assertEquals(userEsperado,userObtido);
    }

    @Test
    @DisplayName("Testa busca do CPF")
    public void Teste002(){
        UserClient userEsperado = usuario01;

        Mockito.when(userRepo.findByCpf("000.000.000-00")).thenReturn(Optional.of(usuario01));

        UserClient userObtido = userRepo.findByCpf("000.000.000-00").get();
        assertEquals(userEsperado,userObtido);
    }

}
