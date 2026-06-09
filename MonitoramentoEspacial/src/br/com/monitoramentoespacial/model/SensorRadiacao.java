package br.com.monitoramentoespacial.model;
import java.util.Random;

public class SensorRadiacao extends ComponenteEspacial implements Sensor {
    private double limiteAlerta;
    private boolean modoSimulacao;
    private double valorSimulado;

    public SensorRadiacao(String id, String nome, double limiteAlerta) {
        super(id, nome);
        this.setLimiteAlerta(limiteAlerta);
        this.modoSimulacao = false;
        this.valorSimulado = 0.15; // Radiação basal de fundo no espaço (mSv)
    }


    // métodos para simulação:

    public void ativarLeituraManual(double valorForcado) {
        this.modoSimulacao = true;
        this.valorSimulado = valorForcado;
        this.setTemperatura(valorForcado); // atualiza o componente
        System.out.println("SISTEMA: Simulação ATIVADA para " + getNome() + ". Radiação fixada em: " + valorForcado + " mSv");
    }

    public void desativarLeituraManual() {
        this.modoSimulacao = false;
        System.out.println("SISTEMA: Simulação DESATIVADA. " + getNome() + " retornou para leitura ambiental.");
    }


    // métodos obrigatórios da interface do sensor
    @Override
    public double lerValor() {
        if (!isStatus()) return 0.0;
        if (modoSimulacao) return valorSimulado;

        Random gerador = new Random();
        // Gera picos de radiação entre 0.15 e 5.15 mSv
        double leituraAtual = 0.15 + (5.0 * gerador.nextDouble());
        this.setTemperatura(leituraAtual);
        return leituraAtual;
    }

    @Override
    public boolean verificarFuncionamento() {
        //radiação alta = 5.0 acima do limite crítico
        return getTemperatura() <= (limiteAlerta + 5.0);
    }

    @Override
    public String retornarTipo() {
        return "Radiação";
    }


    // diagnósticos e alertas:

    public String classificarNivelAlerta() {
        double radAtual = getTemperatura();

        if (radAtual <= limiteAlerta) return "NORMAL";
        else if (radAtual <= (limiteAlerta * 1.15)) return "ATENÇÃO";
        else if (radAtual <= (limiteAlerta * 1.40)) return "ALERTA";
        else return "CRÍTICO";
    }

    @Override
    public void avaliarIntegridade() {
        System.out.println(" DIAGNÓSTICO DE COMPONENTE: " + retornarTipo().toUpperCase());
        System.out.println("ID: " + getId() + " | Nome: " + getNome() + " | Status: " + (isStatus() ? "LIGADO" : "DESLIGADO"));
        System.out.println("Nível Radiação Atual: " + String.format("%.2f", getTemperatura()) + " mSv");
        System.out.println("Limite de Exposição Máxima: " + limiteAlerta + " mSv");
        System.out.println("Classificação de Risco: [" + classificarNivelAlerta() + "]");

        if (verificarFuncionamento()) {
            System.out.println("Condição Física: OPERANTE.");
        } else {
            System.out.println("Condição Física: FALHA CRÍTICA!");
        }
    }

    // getters e setters

    public double getLimiteAlerta() { return limiteAlerta; }

    public void setLimiteAlerta(double limiteAlerta) {
        if (limiteAlerta < 0) {
            throw new IllegalArgumentException("Erro de Segurança: A radiação não pode ser um valor negativo.");
        }
        this.limiteAlerta = limiteAlerta;
    }
}