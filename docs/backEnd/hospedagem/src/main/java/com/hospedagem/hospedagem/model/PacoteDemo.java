package com.hospedagem.hospedagem.model;

/**
 * Classe de demonstração do uso do padrão Decorator para pacotes de hospedagem.
 * Mostra como combinar serviços flexivelmente sem criar inúmeras combinações fixas.
 */
public class PacoteDemo {
    
    public static void main(String[] args) {
        // Criar um quarto de exemplo (usando QuartoDuplo como quarto de casal)
        Quarto quarto = new QuartoDuplo("Quarto Casal Vista Mar", 850.00, true, false, 
            StatusQuarto.ATIVO, TipoCama.KING, false, 0, 0, 0);
        
        System.out.println("=== SISTEMA DE PACOTES DE HOSPEDAGEM ===\n");
        
        // Exemplo 1: Pacote Econômico
        System.out.println("--- PACOTE ECONÔMICO ---");
        Servico pacoteEconomico = PacotePredefinidoFactory.criarPacoteEconomico(quarto, 3, 2);
        exibirDetalhesPacote(pacoteEconomico);
        
        // Exemplo 2: Pacote Família
        System.out.println("\n--- PACOTE FAMÍLIA ---");
        Servico pacoteFamilia = PacotePredefinidoFactory.criarPacoteFamilia(quarto, 5, 4, "Passeio pelas praias");
        exibirDetalhesPacote(pacoteFamilia);
        
        // Exemplo 3: Pacote Premium
        System.out.println("\n--- PACOTE PREMIUM ---");
        Servico pacotePremium = PacotePredefinidoFactory.criarPacotePremium(
            quarto, 7, 2, "Tour completo", 5, 10.0, true
        );
        exibirDetalhesPacote(pacotePremium);
        
        // Exemplo 4: Pacote Personalizado (demonstrando flexibilidade)
        System.out.println("\n--- PACOTE PERSONALIZADO ---");
        Servico pacotePersonalizado = PacoteFactory.criarPacotePersonalizado(quarto, 4)
            .comCafeDaManha(2)
            .comTransporte(3)
            .comTraslado(true)
            .build();
        exibirDetalhesPacote(pacotePersonalizado);
        
        // Exemplo 5: Outra combinação personalizada
        System.out.println("\n--- OUTRO PACOTE PERSONALIZADO ---");
        Servico pacoteCustom = PacoteFactory.criarPacotePersonalizado(quarto, 2)
            .comPasseioTuristico(2, "Visitação aos manguezais")
            .comLavanderia(5.0)
            .build();
        exibirDetalhesPacote(pacoteCustom);
        
        System.out.println("\n=== VANTAGENS DO PADRÃO DECORATOR ===");
        System.out.println("✓ Combinação flexível de serviços");
        System.out.println("✓ Sem necessidade de criar classes para cada combinação");
        System.out.println("✓ Fácil adicionar novos serviços no futuro");
        System.out.println("✓ Código limpo e manutenível");
    }
    
    private static void exibirDetalhesPacote(Servico servico) {
        System.out.println("Nome: " + servico.getNome());
        System.out.println("Descrição: " + servico.getDescricao());
        System.out.println("Custo total: R$ " + String.format("%.2f", servico.getCusto()));
    }
}
