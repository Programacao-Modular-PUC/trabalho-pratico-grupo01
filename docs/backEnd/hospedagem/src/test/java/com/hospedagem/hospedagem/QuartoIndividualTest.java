package com.hospedagem.hospedagem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.hospedagem.hospedagem.model.QuartoIndividual;


public class QuartoIndividualTest {
    
    QuartoIndividual quartoIndividual = new QuartoIndividual("Individual", 100, true, true, 5, 100);
    
    @Test
    public void testCalcularValor(){
        double valorTotal = quartoIndividual.calcularValor();
        assertEquals(600, valorTotal);
    }
}
