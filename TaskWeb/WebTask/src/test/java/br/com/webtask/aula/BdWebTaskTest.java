package br.com.webtask.aula.domain.model;

import br.com.webtask.aula.domain.repo.TaskRepo;
import br.com.webtask.aula.domain.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.webtask.aula")
@ActiveProfiles({"test"})
public class BdWebTaskTest implements CommandLineRunner {

    public static void main(String[] args) { SpringApplication.run(BdWebTaskTest.class, args); }

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    UserRepo userRepo;
    @Value("${spring.profiles.active:test}")
    String profile;
    public void run(String... args){

        System.out.println(profile);

        if (userRepo.count() == 0) {
            UserClient uc1 = UserClient.builder()
                    .ativo(true).cpf("123").email("admin@admin")
                    .name("admin").senha(passwordEncoder().encode("123")).build();
            userRepo.save(uc1);

            UserClient uc2 = UserClient.builder()
                    .ativo(true).cpf("111").email("ze@ze")
                    .name("Zezin da Silva").senha(passwordEncoder().encode("111")).build();
            userRepo.save(uc2);

            Task t1 = Task.builder().plannedDate(LocalDate.now().plusDays(5)).user(uc2).taskDescription("Estudar Teste").build();
            Task t2 = Task.builder().plannedDate(LocalDate.now().minusDays(3)).user(uc2).taskDescription("Estudar Teste").build();
            Task t3 = Task.builder().plannedDate(LocalDate.now().minusDays(5)).finishedDate(LocalDate.now().minusDays(6)).user(uc2).taskDescription("Estudar Teste").build();
            Task t4 = Task.builder().plannedDate(LocalDate.now().minusDays(7)).finishedDate(LocalDate.now()).user(uc2).taskDescription("Estudar Teste").build();
            taskRepo.save(t1);
            taskRepo.save(t2);
            taskRepo.save(t3);
            taskRepo.save(t4);
        }
    }
    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}