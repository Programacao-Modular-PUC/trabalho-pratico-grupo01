package com.hospedagem.hospedagem.model;

/**
 * Factory para criação de pacotes pré-definidos.
 * Segue SRP separando a criação de pacotes fixos do builder personalizado.
 */
public class PacotePredefinidoFactory {
    
    /**
     * Cria um Pacote Econômico - hospedagem básica com café da manhã
     */
    public static Servico criarPacoteEconomico(Quarto quarto, int numeroDiarias, int numeroPessoas) {
        Servico base = new HospedagemBase(quarto, numeroDiarias);
        return new CafeDaManhaDecorator(base, numeroPessoas);
    }
    
    /**
     * Cria um Pacote Família - hospedagem com café da manhã e passeio turístico
     */
    public static Servico criarPacoteFamilia(Quarto quarto, int numeroDiarias, int numeroPessoas, String tipoPasseio) {
        Servico base = new HospedagemBase(quarto, numeroDiarias);
        Servico comCafe = new CafeDaManhaDecorator(base, numeroPessoas);
        return new PasseioTuristicoDecorator(comCafe, numeroPessoas, tipoPasseio);
    }
    
    /**
     * Cria um Pacote Premium - hospedagem com todos os serviços
     */
    public static Servico criarPacotePremium(Quarto quarto, int numeroDiarias, int numeroPessoas, 
                                             String tipoPasseio, int diasTransporte, 
                                             double quilosLavanderia, boolean trasladoIdaEVolta) {
                                                
        Servico base = new HospedagemBase(quarto, numeroDiarias);
        Servico comCafe = new CafeDaManhaDecorator(base, numeroPessoas);
        Servico comPasseio = new PasseioTuristicoDecorator(comCafe, numeroPessoas, tipoPasseio);
        Servico comTransporte = new TransporteDecorator(comPasseio, diasTransporte);
        Servico comLavanderia = new LavanderiaDecorator(comTransporte, quilosLavanderia);
        return new TrasladoDecorator(comLavanderia, trasladoIdaEVolta);
    }
}
