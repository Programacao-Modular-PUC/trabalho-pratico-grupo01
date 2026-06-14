package com.hospedagem.hospedagem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.hospedagem.hospedagem.model.QuartoDuplo;
import com.hospedagem.hospedagem.model.StatusQuarto;
import com.hospedagem.hospedagem.model.TipoCama;


public class QuartoDuploTest {

    QuartoDuplo quartoComum = new QuartoDuplo("Duplo", 100, true, true, StatusQuarto.ATIVO, TipoCama.COMUM, false, 100, 200, 100);
    QuartoDuplo quartoKing = new QuartoDuplo("Duplo", 100, true, true, StatusQuarto.ATIVO, TipoCama.KING, false, 100, 200, 100);
    QuartoDuplo quartoBerco = new QuartoDuplo("Duplo", 100, true, true, StatusQuarto.ATIVO, TipoCama.COMUM, true, 100, 200, 100);

    @Test
    public void testQuartoDuploCalcularDiaria(){

        double valorTotalComum = quartoComum.calcularValor();
        double valorTotalKing = quartoKing.calcularValor();
        double valorTotalBerco = quartoBerco.calcularValor();
        
        assertEquals(200, valorTotalComum);
        assertEquals(300, valorTotalKing);
        assertEquals(300, valorTotalBerco);
    }

    

}
