package br.edu.calc.plus.CalculatorPlus.user;

import br.edu.calc.plus.config.security.user.UserDetailsServiceImpl;
import br.edu.calc.plus.domain.Usuario;
import br.edu.calc.plus.repo.UsuarioRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserDetailServiceImplTest {

    @Mock
    private UsuarioRepo userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Testa loadUserByUsername para usuário admin")
    public void Teste001() {
        String adminUsername = "admin";
        String adminPassword = "adminPassword";
        Usuario adminUser = new Usuario(null, "Admin", adminUsername, "admin@teste", adminPassword, "JF", null);
        Set<GrantedAuthority> adminAuthorities = new HashSet<>();
        adminAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        Mockito.when(userRepo.findByLogin(adminUsername)).thenReturn(Optional.of(adminUser));

        UserDetails userDetails = userDetailsService.loadUserByUsername(adminUsername);
        assertEquals(adminUsername, userDetails.getUsername());
        assertEquals(adminPassword, userDetails.getPassword());
        assertEquals(adminAuthorities, userDetails.getAuthorities());
    }

    @Test
    @DisplayName("Testa loadUserByUsername para usuário cliente")
    public void Teste002() {
        String clienteUsername = "cliente";
        String clientePassword = "clientePassword";
        Usuario clienteUser = new Usuario(null, "Cliente", clienteUsername, "cliente@teste", clientePassword, "JF", null);
        Set<GrantedAuthority> clienteAuthorities = new HashSet<>();
        clienteAuthorities.add(new SimpleGrantedAuthority("ROLE_CLIENTE"));
        Mockito.when(userRepo.findByLogin(clienteUsername)).thenReturn(Optional.of(clienteUser));
        Mockito.when(passwordEncoder.encode(clientePassword)).thenReturn(clientePassword);
        UserDetails userDetails = userDetailsService.loadUserByUsername(clienteUsername);
        assertEquals(clienteUsername, userDetails.getUsername());
        assertEquals(clientePassword, userDetails.getPassword());
        assertEquals(clienteAuthorities, userDetails.getAuthorities());
    }

    @Test
    @DisplayName("Testa loadUserByUsername para usuário não encontrado")
    public void Teste003() {
        String usernameNaoExistente = "usuarioNaoExistente";
        Mockito.when(userRepo.findByLogin(usernameNaoExistente)).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(usernameNaoExistente));
    }

}
