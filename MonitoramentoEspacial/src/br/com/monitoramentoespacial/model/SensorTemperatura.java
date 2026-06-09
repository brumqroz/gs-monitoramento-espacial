package br.com.monitoramentoespacial.model;
import java.util.Random;

public class SensorTemperatura extends ComponenteEspacial implements Sensor {
    private double limiteAlerta;
    private boolean modoSimulacao;
    private double valorSimulado;

    public SensorTemperatura(String id, String nome, double limiteAlerta) {
        super(id, nome);
        this.setLimiteAlerta(limiteAlerta);
        this.modoSimulacao = false;
        this.valorSimulado = 0.0;
    }


    //métodos para simulação:

    public void ativarLeituraManual(double valorForcado) {
        this.modoSimulacao = true;
        this.valorSimulado = valorForcado;
        this.setTemperatura(valorForcado); // Atualiza o componente físico
        System.out.println("SISTEMA: Modo de simulação ATIVADO para " + getNome() + ". Valor fixado: " + valorForcado + " °C");
    }

    public void desativarLeituraManual() {
        this.modoSimulacao = false;
        System.out.println("SISTEMA: Modo de simulação DESATIVADO. Sensor retornou para leitura automática.");
    }


    //métodos da interface do sensor:

    @Override
    public double lerValor() {
        if (!isStatus()) {
            return 0.0;
        }

        // ligada = pula gerador aleatório e volta para o fixo
        if (modoSimulacao) {
            return valorSimulado;
        }

        // desligado = lê
        Random gerador = new Random();
        double leituraAtual = 10 + (50 * gerador.nextDouble());
        this.setTemperatura(leituraAtual);
        return leituraAtual;
    }
    @Override
    public boolean verificarFuncionamento() {
        return getTemperatura() <= (limiteAlerta + 20.0);
    }
    @Override
    public String retornarTipo() {
        return "Temperatura";
    }

    //sistema de diagnóstico e alertas:

    public String classificarNivelAlerta() {
        double tempAtual = getTemperatura();

        if (tempAtual <= limiteAlerta) {
            return "NORMAL";
        } else if (tempAtual <= (limiteAlerta * 1.15)) { // Até 15% acima
            return "ATENÇÃO";
        } else if (tempAtual <= (limiteAlerta * 1.40)) { // Até 40% acima
            return "ALERTA";
        } else { // + de 40% acima
            return "CRÍTICO";
        }
    }
    @Override
    public void avaliarIntegridade() {
        System.out.println(" DIAGNÓSTICO DE COMPONENTE: " + retornarTipo().toUpperCase());
        System.out.println("ID: " + getId() + " | Nome: " + getNome() + " | Status: " + (isStatus() ? "LIGADO" : "DESLIGADO"));
        System.out.println("Leitura Atual: " + String.format("%.2f", getTemperatura()) + " °C");
        System.out.println("Limite de Segurança: " + limiteAlerta + " °C");
        System.out.println("Classificação de Risco: [" + classificarNivelAlerta() + "]");

        if (verificarFuncionamento()) {
            System.out.println("Condição Física: OPERANTE.");
        } else {
            System.out.println("Condição Física: FALHA CRÍTICA!");
        }
    }

    //getter e setters

    public double getLimiteAlerta() {
        return limiteAlerta;
    }
    public void setLimiteAlerta(double limiteAlerta) {
        if (limiteAlerta < -273.15) {
            throw new IllegalArgumentException("Erro: Limite de temperatura não pode ser inferior a -273.15 °C.");
        }
        this.limiteAlerta = limiteAlerta;
    }
}








