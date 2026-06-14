package com.hospedagem.hospedagem.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.hospedagem.hospedagem.exeptions.DataInvalidaException;

import java.time.LocalDate;

public class ReservaServiceTest {

    @Test
    public void testDataEntradaNulaDeveLancarExcecao() {
        ReservaService reservaService = new ReservaService();
        assertThrows(DataInvalidaException.class, () -> 
            reservaService.validarDatas(null, LocalDate.now().plusDays(1))
        );
    }

    @Test
    public void testDataSaidaNulaDeveLancarExcecao() {
        ReservaService reservaService = new ReservaService();
        assertThrows(DataInvalidaException.class, () -> 
            reservaService.validarDatas(LocalDate.now().plusDays(1), null)
        );
    }

    @Test
    public void testDataEntradaAnteriorHojeDeveLancarExcecao() {
        ReservaService reservaService = new ReservaService();
        assertThrows(DataInvalidaException.class, () -> 
            reservaService.validarDatas(LocalDate.now().minusDays(1), LocalDate.now().plusDays(1))
        );
    }

    @Test
    public void testDataSaidaAnteriorEntradaDeveLancarExcecao() {
        ReservaService reservaService = new ReservaService();
        assertThrows(DataInvalidaException.class, () -> 
            reservaService.validarDatas(LocalDate.now().plusDays(5), LocalDate.now().plusDays(3))
        );
    }

    @Test
    public void testDataSaidaIgualEntradaDeveLancarExcecao() {
        ReservaService reservaService = new ReservaService();
        assertThrows(DataInvalidaException.class, () -> 
            reservaService.validarDatas(LocalDate.now().plusDays(1), LocalDate.now().plusDays(1))
        );
    }
}
