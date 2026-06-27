package com.hospedagem.hospedagem.model;

/**
 * Builder para criação de pacotes personalizados de hospedagem.
 * Segue SRP focando apenas na construção flexível de pacotes.
 * Para pacotes pré-definidos, use PacotePredefinidoFactory.
 */
public class PacoteFactory {
    
    /**
     * Builder para criação de pacotes personalizados.
     * Permite combinação livre de serviços usando o padrão Decorator.
     */
    public static class PacotePersonalizadoBuilder {
        private Servico servicoAtual;
        
        public PacotePersonalizadoBuilder(Quarto quarto, int numeroDiarias) {
            this.servicoAtual = new HospedagemBase(quarto, numeroDiarias);
        }
        
        public PacotePersonalizadoBuilder comCafeDaManha(int numeroPessoas) {
            servicoAtual = new CafeDaManhaDecorator(servicoAtual, numeroPessoas);
            return this;
        }
        
        public PacotePersonalizadoBuilder comPasseioTuristico(int numeroPessoas, String tipoPasseio) {
            servicoAtual = new PasseioTuristicoDecorator(servicoAtual, numeroPessoas, tipoPasseio);
            return this;
        }
        
        public PacotePersonalizadoBuilder comTransporte(int numeroDias) {
            servicoAtual = new TransporteDecorator(servicoAtual, numeroDias);
            return this;
        }
        
        public PacotePersonalizadoBuilder comLavanderia(double quilos) {
            servicoAtual = new LavanderiaDecorator(servicoAtual, quilos);
            return this;
        }
        
        public PacotePersonalizadoBuilder comTraslado(boolean idaEVolta) {
            servicoAtual = new TrasladoDecorator(servicoAtual, idaEVolta);
            return this;
        }
        
        public Servico build() {
            return servicoAtual;
        }
    }
    
    /**
     * Método conveniente para iniciar um pacote personalizado
     */
    public static PacotePersonalizadoBuilder criarPacotePersonalizado(Quarto quarto, int numeroDiarias) {
        return new PacotePersonalizadoBuilder(quarto, numeroDiarias);
    }
}
