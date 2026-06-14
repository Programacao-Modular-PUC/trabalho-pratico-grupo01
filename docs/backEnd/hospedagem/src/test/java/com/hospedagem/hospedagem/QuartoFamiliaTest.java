package com.hospedagem.hospedagem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.hospedagem.hospedagem.exeptions.CapacidadeExcedidaException;
import com.hospedagem.hospedagem.exeptions.QuartoIndisponivelException;
import com.hospedagem.hospedagem.model.QuartoFamilia;
import com.hospedagem.hospedagem.model.StatusQuarto;
import com.hospedagem.hospedagem.model.TipoCama;

public class QuartoFamiliaTest {
    
    QuartoFamilia quartoFamilia = new QuartoFamilia("Familia", 100, true, true, StatusQuarto.ATIVO, 3, 2, TipoCama.COMUM, 2, 150);
    QuartoFamilia quartoFamiliaKing = new QuartoFamilia("Familia", 100, true, true, StatusQuarto.ATIVO, 3, 2, TipoCama.KING, 3, 200);
    
    @Test
    public void testCalcularValor(){
        double valorTotal = quartoFamilia.calcularValor();
        double valorTotalKing = quartoFamiliaKing.calcularValor();

        assertEquals(400, valorTotal);
        assertEquals(700, valorTotalKing);
    }

    @Test
    public void testCapacidadeExcedidaNoConstrutor(){
        assertThrows(CapacidadeExcedidaException.class, () -> 
            new QuartoFamilia("Familia", 100, true, true, StatusQuarto.ATIVO, 3, 2, TipoCama.COMUM, 5, 150)
        );
    }

    @Test
    public void testCapacidadeExcedidaNoSetter(){
        assertThrows(CapacidadeExcedidaException.class, () -> 
            quartoFamilia.setNumeroDePessoas(4)
        );
    }

    @Test
    public void testQuartoInativoDeveLancarExcecao(){
        assertThrows(QuartoIndisponivelException.class, () -> 
            new QuartoFamilia("Familia", 100, true, true, StatusQuarto.INATIVO, 3, 2, TipoCama.COMUM, 2, 150)
        );
    }

    @Test
    public void testSetStatusInativoDeveLancarExcecao(){
        assertThrows(QuartoIndisponivelException.class, () -> 
            quartoFamilia.setStatus(StatusQuarto.INATIVO)
        );
    }
}
