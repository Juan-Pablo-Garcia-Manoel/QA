package br.com.webtask.aula.domain.repo;

import br.com.webtask.aula.domain.model.UserClient;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;



@DataJpaTest
//@SpringBootTest
@ActiveProfiles({"homolog"})
public class UserRepoTesteIntegracao {

    @Autowired
    private UserRepo userRepo;

    public UserRepoTesteIntegracao(){

    }

    private static UserClient usuario01 = new UserClient(null,"Juan","321","juan@juan","123",true,null);

    @BeforeAll
    public static void setUpClass(){

    }

    @AfterAll
    public static void tearDownClass(){

    }

    @BeforeEach
    @Transactional
    public void setUp(){
        userRepo.save(usuario01);
    }

    @AfterEach
    public void tearDown(){
        userRepo.deleteAll();
    }

    @Test
    @DisplayName("Testa busca do nome")
    public void Teste001(){
        //assertTrue(true);
        UserClient userEsperado = usuario01;
        UserClient userObtido = userRepo.findByName("Juan").get();
        assertEquals(userEsperado,userObtido);
    }

    @Test
    @DisplayName("Testa busca da quantidade total de usuários em banco")
    public void Teste002(){
        long qtdEsperado = 3;
        long qtdObtido = userRepo.count();
        assertEquals(qtdEsperado,qtdObtido);
    }

}
