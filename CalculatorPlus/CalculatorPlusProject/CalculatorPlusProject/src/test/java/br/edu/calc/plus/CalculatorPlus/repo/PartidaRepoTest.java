package br.edu.calc.plus.CalculatorPlus.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import br.edu.calc.plus.domain.dto.RankingDTO;
import br.edu.calc.plus.repo.UsuarioRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import br.edu.calc.plus.domain.Partida;
import br.edu.calc.plus.domain.Usuario;
import br.edu.calc.plus.repo.PartidaRepo;

@DataJpaTest
@ActiveProfiles("test")
public class PartidaRepoTest {

    @Autowired
    private PartidaRepo partidaRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;

    private Usuario usuario01, usuario02;
    private Partida partida1;
    private Partida partida2;

    @BeforeEach
    @Transactional
    public void setUp() {

        /*Salvar o usuário*/
        usuario01 = new Usuario(null, "Test User", "testuser", "testuser@test.com", "password", "City", LocalDate.now().minusYears(25));
        usuario01 = usuarioRepo.save(usuario01);

        usuario02 = new Usuario(null, "Arthur Fernades", "arthur", "arthur@teste","password", "JF", LocalDate.now().minusYears(30));
        usuario02 = usuarioRepo.save(usuario02);

        /*Criar as partidas e vincula-lá elas ao usuário*/
        partida1 = new Partida(null, LocalDateTime.now(), 100, 30);
        partida1.setUsuario(usuario01);

        partida2 = new Partida(null, LocalDateTime.now().minusDays(1), 150, 30);
        partida2.setUsuario(usuario01);

        /*Salva a partida*/
        partidaRepo.save(partida1);
        partidaRepo.save(partida2);
    }

    @AfterEach
    public void tearDown() {
        partidaRepo.deleteAll();
    }

    @Test
    @DisplayName("Verifica a soma de bonificações de todas as partidas")
    public void Teste001() {
        Double bonusEsperado = 250.0;
        Double bonusObtido = partidaRepo.getAllBonus();
        assertEquals(bonusEsperado, bonusObtido);
    }

    @Test
    @DisplayName("Obtém a lista de partidas por ID de usuário")
    public void Teste002() {
        List<Partida> partidas = partidaRepo.findByUsuarioId(usuario01.getId());
        assertEquals(2, partidas.size());
    }

    @Test
    @DisplayName("Obtém a lista de partidas por ID de usuário")
    public void Teste003() {
        List<Partida> partidas = partidaRepo.findByUsuarioId(usuario01.getId());
        assertEquals(2, partidas.size());
    }

    @Test
    @DisplayName("Obtém o número de partidas de um usuário em um intervalo de datas")
    public void Teste004() {
        LocalDateTime dataInicio = LocalDateTime.now().minusDays(2);
        LocalDateTime dataFim = LocalDateTime.now();

        long quantidadeEsperada = 2;
        long quantidadeObtida = partidaRepo.getUsuarioCompetil(usuario01.getId(), dataInicio, dataFim);
        assertEquals(quantidadeEsperada, quantidadeObtida);
    }

    @Test
    @DisplayName("Obtém o número de partidas de um usuário em um intervalo de datas")
    public void Teste005() {
        LocalDateTime dataInicio = LocalDateTime.now().minusDays(2);
        LocalDateTime dataFim = LocalDateTime.now();

        long quantidadeEsperada = 2;
        long quantidadeObtida = partidaRepo.getUsuarioCompetil(usuario01.getId(), dataInicio, dataFim);
        assertEquals(quantidadeEsperada, quantidadeObtida);
    }

    @Test
    @Transactional
    @DisplayName("Verifica se a Id do usuário e da partida associada é igual")
    public void Teste006() {
        Partida partidaEncontrada = partidaRepo.findByIdAndUsuarioId(partida1.getId(), usuario01.getId());
        assertNotNull(partidaEncontrada);
        assertEquals(usuario01.getId(), partidaEncontrada.getUsuario().getId());
        assertEquals(partida1.getId(), partidaEncontrada.getId());
    }

    @Test
    @DisplayName("Obtém o ranking de usuários")
    public void Teste007() {
        List<RankingDTO> ranking = partidaRepo.getRanking();
        assertEquals(1, ranking.size());
        assertEquals(usuario01.getId(), ranking.get(0).getIdUser());
    }

    @Test
    @DisplayName("Obtém o ranking de usuários")
    public void Teste008() {

        partida1 = new Partida(null, LocalDateTime.now(), 200, 30);
        partida1.setUsuario(usuario02);
        partidaRepo.save(partida1);

        partida2 = new Partida(null, LocalDateTime.now().minusDays(1), 150, 30);
        partida2.setUsuario(usuario02);
        partidaRepo.save(partida2);

        List<RankingDTO> ranking = partidaRepo.getRanking();
        assertEquals(2, ranking.size());
        /*A possição do usuário02 deve ser 1° colocação devida a sua bonificação ser maior do que a do usuário01*/
        assertEquals(usuario02.getId(), ranking.get(0).getIdUser());
        /*A possição do usuário01 deve ser 2° colocação devida a sua bonificação ser menor do que a do usuário01*/
        assertEquals(usuario01.getId(), ranking.get(1).getIdUser());
    }

}
