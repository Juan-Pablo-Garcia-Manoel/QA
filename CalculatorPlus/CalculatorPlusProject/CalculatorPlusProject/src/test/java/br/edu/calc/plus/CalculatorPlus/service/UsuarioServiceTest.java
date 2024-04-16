package br.edu.calc.plus.CalculatorPlus.service;

import br.edu.calc.plus.domain.Usuario;
import br.edu.calc.plus.domain.dto.UserDTO;
import br.edu.calc.plus.repo.UsuarioRepo;
import br.edu.calc.plus.service.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;

@SpringBootTest
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepo uDao;

    @InjectMocks
    private UsuarioService uServ;

    private Validator validator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("testa getCountUsers")
    public void Teste001() {
        Mockito.when(uDao.count()).thenReturn(5L);

        long resultado = uServ.getCountUsers();

        Assertions.assertEquals(5L, resultado);
    }

    @Test
    @DisplayName("testa save")
    public void Teste002() {
        /*Dados do usuário que desejo testar*/
        UserDTO userDTO = new UserDTO( "Juan Pablo", "juan", "juan@teste", "123456" , "JF",
                LocalDate.now().minusYears(30));

        Usuario usuario = userDTO.ConvertUsuario();

        uServ.save(userDTO);

        Mockito.verify(uDao, Mockito.times(1)).save(Mockito.eq(usuario));
    }
}
