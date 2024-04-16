package br.edu.calc.plus.CalculatorPlus.repo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import br.edu.calc.plus.domain.Usuario;
import br.edu.calc.plus.repo.JogoRepo;
import br.edu.calc.plus.repo.PartidaRepo;
import br.edu.calc.plus.repo.UsuarioRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import br.edu.calc.plus.domain.EOperator;
import br.edu.calc.plus.domain.Jogo;
import br.edu.calc.plus.domain.Partida;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@ActiveProfiles("test")
public class JogoRepoTest {
    @Autowired
    private JogoRepo jogoRepo;


    @Autowired
    private PartidaRepo partidaRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;
    private Usuario usuario;
    private Partida partida;
    private Jogo jogo;

    @BeforeEach
    @Transactional
    public void setUp() {

        /*Salvar o usuário*/
        usuario = new Usuario(null, "Test User", "testuser", "testuser@test.com", "password", "City", LocalDate.now().minusYears(25));
        usuario = usuarioRepo.save(usuario);

        /*Criar as partidas e vincula-lá elas ao usuário*/
        partida = new Partida(null, LocalDateTime.now(), 100, 30);
        partida.setUsuario(usuario);

        /*Salva a partida*/
        partidaRepo.save(partida);
    }

    @AfterEach
    public void tearDown() {
        partidaRepo.deleteAll();
    }


    @Test
    @DisplayName("Verifica resposta incorreta no jogo")
    public void Teste001() {

        jogo = new Jogo(null, 10, 10, EOperator.soma, 20, 21, 1);
        jogo.setPartida(partida);
        jogoRepo.save(jogo);

        long erros = jogoRepo.getAllErros();
        assertEquals(1, erros);
    }

    @Test
    @DisplayName("Verifica resposta certa no jogo")
    public void Teste002() {
        jogo = new Jogo(null, 10, 10, EOperator.soma, 20, 20, 1);
        jogo.setPartida(partida);
        jogoRepo.save(jogo);

        long acertos = jogoRepo.getAllAcertos();
        assertEquals(1, acertos);
    }

    @Test
    @DisplayName("Verifica total de acertos no jogo do dia")
    public void Teste003() {
        jogo = new Jogo(null, 10, 10, EOperator.soma, 20, 20, 1);
        jogo.setPartida(partida);
        jogoRepo.save(jogo);

        LocalDateTime dataInicio = LocalDateTime.now().minusDays(2);
        LocalDateTime dataFim = LocalDateTime.now();
        long acertosDia = jogoRepo.getAcertosDia(dataInicio, dataFim);
        assertEquals(1, acertosDia);
    }

    @Test
    @DisplayName("Verifica total de erros no jogo do dia")
    public void Teste004() {
        jogo = new Jogo(null, 10, 10, EOperator.soma, 20, 21, 1);
        jogo.setPartida(partida);
        jogoRepo.save(jogo);

        LocalDateTime dataInicio = LocalDateTime.now().minusDays(2);
        LocalDateTime dataFim = LocalDateTime.now();
        long errosDia = jogoRepo.getErrosDia(dataInicio, dataFim);
        assertEquals(1, errosDia);
    }

    @Test
    @DisplayName("Verifica total de acertos no jogo do usuário")
    public void Teste005() {
        int userId = usuario.getId();

        jogo = new Jogo(null, 10, 10, EOperator.soma, 20, 20, 1);
        jogo.setPartida(partida);
        jogoRepo.save(jogo);

        long acertosUser = jogoRepo.getAllAcertosUser(userId);
        assertEquals(1, acertosUser);
    }

}
