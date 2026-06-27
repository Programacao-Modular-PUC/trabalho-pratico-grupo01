package com.hospedagem.hospedagem.model;

/**
 * Decorator para adicionar serviço de traslado aeroporto-hospedagem.
 */
public class TrasladoDecorator extends ServicoDecorator {
    
    private static final double CUSTO_UNICO = 120.00;
    private boolean idaEVolta;
    
    public TrasladoDecorator(Servico servicoDecorado, boolean idaEVolta) {
        super(servicoDecorado);
        this.idaEVolta = idaEVolta;
    }
    
    @Override
    public String getDescricao() {
        String tipo = idaEVolta ? "ida e volta" : "somente ida";
        return servicoDecorado.getDescricao() + " + Traslado aeroporto-hospedagem Tipo:" + tipo;
    }
    
    @Override
    public double getCusto() {
        double custo = CUSTO_UNICO;
        if (idaEVolta) {
            custo *= 2;
        }
        return servicoDecorado.getCusto() + custo;
    }
    
    @Override
    public String getNome() {
        return servicoDecorado.getNome() + " com Traslado";
    }
    
    public boolean isIdaEVolta() {
        return idaEVolta;
    }
}
