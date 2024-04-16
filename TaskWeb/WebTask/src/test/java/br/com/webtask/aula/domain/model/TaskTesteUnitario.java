package br.com.webtask.aula.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTesteUnitario {
    @Test
    @DisplayName("Testa descrição vazia")
    public void Teste001(){
        Task task = new Task();
        task.setTaskDescription(" ");
        boolean esperado = false;

        boolean resultado = task.isDescriptionValid();
        assertEquals(esperado,resultado);
    }

    @Test
    @DisplayName("Testa data final nula")
    public void Teste002(){
        Task task = new Task();
        task.setFinishedDate(null);
        boolean esperado = false;

        boolean resultado = task.isFinish();
        assertEquals(esperado,resultado);
    }
}
