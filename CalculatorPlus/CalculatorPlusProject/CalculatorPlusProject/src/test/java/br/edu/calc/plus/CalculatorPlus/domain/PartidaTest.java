package br.edu.calc.plus.CalculatorPlus.domain;

import br.edu.calc.plus.domain.Jogo;
import br.edu.calc.plus.domain.Partida;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class PartidaTest {

    @Test
    @DisplayName("Verifica acertos e erros")
    public void Teste001() {

        Partida partida = new Partida();

        partida.setId(1);
        partida.setData(LocalDateTime.now());
        partida.setBonificacao(50.0);
        partida.setTempo(60);

        List<Jogo> jogos = new ArrayList<>();

        Jogo jogo1 = new Jogo();
        jogo1.setResposta(1);
        jogos.add(jogo1);

        Jogo jogo2 = new Jogo();
        jogo2.setResposta(0);
        jogos.add(jogo2);
        partida.setJogoList(jogos);

        int acertos = partida.getAcertos();
        int erros = partida.getErros();

        assertEquals(1, acertos);
        assertEquals(1, erros);
    }

    @Test
    @DisplayName("Verifica bônus")
    public void Teste002() {
        Partida partida = new Partida();
        partida.setId(2);
        partida.setData(LocalDateTime.now());
        partida.setBonificacao(100.0);
        partida.setTempo(120);
        partida.addBonus(20.0);
        double bonificacaoAtualizada = partida.getBonificacao();
        assertEquals(120.0, bonificacaoAtualizada);
    }
}
