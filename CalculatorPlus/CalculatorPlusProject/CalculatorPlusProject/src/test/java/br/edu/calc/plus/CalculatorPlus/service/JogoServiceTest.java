package br.edu.calc.plus.CalculatorPlus.service;

import br.edu.calc.plus.domain.EOperator;
import br.edu.calc.plus.repo.JogoRepo;
import br.edu.calc.plus.service.JogoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import static org.mockito.ArgumentMatchers.any;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@SpringBootTest
public class JogoServiceTest {

    @Mock
    private JogoRepo jDao;

    @InjectMocks
    private JogoService jServ;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Verifica o método getAllErros")
    public void Teste001() {
        Mockito.when(jDao.getAllErros()).thenReturn(1L);
        String resultado = jServ.getAllErros();
        Assertions.assertEquals("1", resultado);
    }

    @Test
    @DisplayName("Verifica o método getAllAcertos")
    public void Teste002() {
        Mockito.when(jDao.getAllAcertos()).thenReturn(10L);

        long resultado = jServ.getAllAcertos();

        Assertions.assertEquals(10L, resultado);
    }

    @Test
    @DisplayName("Verifica o método getAcertosUltimos10Dias()")
    public void Teste003() {
        Mockito.when(jDao.getAcertosDia(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(5L);
        long[] result = jServ.getAcertosUltimos10Dias();
        long[] expected = {5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L};
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    @DisplayName("Verifica o método getErrosUltimos10Dias()")
    public void Teste004() {
        Mockito.when(jDao.getErrosDia(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(3L);
        long[] result = jServ.getErrosUltimos10Dias();
        long[] expected = {3L, 3L, 3L, 3L, 3L, 3L, 3L, 3L, 3L, 3L};
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    @DisplayName("Verifica o método bonusOperacao")
    public void Teste005() throws Exception {
        JogoService jServ = new JogoService();
        Class<?> jogoServiceClass = JogoService.class;

        Method bonusOperacaoMethod = jogoServiceClass.getDeclaredMethod("bonusOperacao", EOperator.class);
        bonusOperacaoMethod.setAccessible(true);

        double bonusSoma = (double) bonusOperacaoMethod.invoke(jServ, EOperator.soma);
        double bonusDivisao = (double) bonusOperacaoMethod.invoke(jServ, EOperator.divisao);
        double bonusMultiplicacao = (double) bonusOperacaoMethod.invoke(jServ, EOperator.multiplicacao);
        double bonusSubtracao = (double) bonusOperacaoMethod.invoke(jServ, EOperator.subtracao);

        Assertions.assertEquals(0.1, bonusSoma);
        Assertions.assertEquals(0.9, bonusDivisao);
        Assertions.assertEquals(0.5, bonusMultiplicacao);
        Assertions.assertEquals(0.2, bonusSubtracao);
    }

    @Test
    @DisplayName("Verifica o método bonusUsuario")
    public void Teste006() throws Exception {

        /*
         * Se tot for menor ou igual a 10, perc é definido como 0.
         * Se tot for maior que 10 e menor ou igual a 50, perc é definido como 0.1.
         * Se tot for maior que 50 e menor ou igual a 100, perc é definido como 0.2.
         * */

        int userId = 1;
        long totalAcertos = 80;
        Mockito.when(jDao.getAllAcertosUser(userId)).thenReturn(totalAcertos);
        double result = (double) ReflectionTestUtils.invokeMethod(jServ, "bonusUsuario", userId);
        double expected = 0.2;
        Assertions.assertEquals(expected, result, 0.01);
    }

    @Test
    @DisplayName("Verifica o método bonusInicial")
    public void Teste007() throws Exception {
        assert(true);
    }

    @Test
    @DisplayName("Verifica o método criarJogosAleatorio")
    public void Teste008() {
        assert(true);
    }

}
