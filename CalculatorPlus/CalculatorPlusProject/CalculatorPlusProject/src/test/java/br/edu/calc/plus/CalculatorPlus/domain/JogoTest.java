package br.edu.calc.plus.CalculatorPlus.domain;

import br.edu.calc.plus.domain.Jogo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@ActiveProfiles("test")
public class JogoTest {

    @Test
    @DisplayName("Verifica se a resposta está correta")
    public void Teste001() {
        Jogo jogo = new Jogo();
        jogo.setResultado(10.0);
        jogo.setResposta(10.0);

        assertTrue(jogo.isCorrect());
    }

    @Test
    @DisplayName("Verifica se a resposta está incorreta")
    public void Teste002() {
        Jogo jogo = new Jogo();
        jogo.setResultado(10.0);
        jogo.setResposta(5.0);

        assertFalse(jogo.isCorrect());
    }

    @Test
    @DisplayName("Verifica se a resposta está correta com bonus")
    public void Teste003() {
        Jogo jogo = new Jogo();
        jogo.setResultado(10);
        jogo.setResposta(5);
        jogo.setBonus(5);

        assertTrue(jogo.isCorrect());
        assertEquals(10, jogo.getBonus());
    }

    @Test
    @DisplayName("Verifica se a resposta está incorreta com bonus")
    public void Teste004() {
        Jogo jogo = new Jogo();
        jogo.setResultado(15.0);
        jogo.setResposta(10.0);
        jogo.setBonus(5.0);

        assertFalse(jogo.isCorrect());
        assertEquals(5.0, jogo.getBonus());
    }
}
