package br.edu.calc.plus.CalculatorPlus.domain;

import br.edu.calc.plus.domain.EOperator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class EOperatorTest {

    @Test
    @DisplayName("Testa a operação de soma")
    public void Teste001() {
        EOperator operator = EOperator.soma;
        int v1 = 10;
        int v2 = 5;
        double esperado = 15;

        double resultado = operator.calcular(v1, v2);
        assertEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Testa a operação de subtração")
    public void Teste002() {
        EOperator operator = EOperator.subtracao;
        int v1 = 10;
        int v2 = 5;
        double esperado = 5;

        double resultado = operator.calcular(v1, v2);
        assertEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Testa a operação de subtração negativo")
    public void Teste003() {
        EOperator operator = EOperator.subtracao;
        int v1 = -5;
        int v2 = 10;
        double esperado = -5;

        double resultado = operator.calcular(v1, v2);
        assertEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Testa a operação de multiplicação")
    public void Teste004() {
        EOperator operator = EOperator.multiplicacao;
        int v1 = 10;
        int v2 = 5;
        double esperado = 50;

        double resultado = operator.calcular(v1, v2);
        assertEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Testa a operação de divisão")
    public void Teste005() {
        EOperator operator = EOperator.divisao;
        int v1 = 10;
        int v2 = 5;
        double esperado = 2;

        double resultado = operator.calcular(v1, v2);
        assertEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Testa a operação de divisão com resultado em decimais")
    public void Teste006() {
        EOperator operator = EOperator.divisao;
        int v1 = 9;
        int v2 = 2;
        double esperado = 0.18;

        double resultado = operator.calcular(v1, v2);
        assertEquals(esperado, resultado);
    }
}
