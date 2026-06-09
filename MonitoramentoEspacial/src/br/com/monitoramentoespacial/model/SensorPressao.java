package br.com.monitoramentoespacial.model;
import java.util.Random;

public class SensorPressao extends ComponenteEspacial implements Sensor {
    private double limiteAlertaAtm;
    private boolean modoSimulacao;
    private double valorSimuladoAtm;

    public SensorPressao(String id, String nome, double limiteAlertaAtm) {
        super(id, nome);
        this.setLimiteAlertaAtm(limiteAlertaAtm);
        this.modoSimulacao = false;
        this.valorSimuladoAtm = 1.0; // 1.0 atm é a pressão normal
    }


    // métodos para simulação:

    public void ativarLeituraManual(double valorForcado) {
        this.modoSimulacao = true;
        this.valorSimuladoAtm = valorForcado;
        this.setTemperatura(valorForcado); // reutiliza a variável da classe mãe para registrar a leitura
        System.out.println("SISTEMA: Simulação ATIVADA para " + getNome() + ". Pressão fixada em: " + valorForcado + " atm");
    }

    public void desativarLeituraManual() {
        this.modoSimulacao = false;
        System.out.println("SISTEMA: Simulação DESATIVADA. " + getNome() + " retornou à leitura ambiental.");
    }


    // métodos obrigatórios:
    @Override
    public double lerValor() {
        if (!isStatus()) {
            return 0.0;
        }

        if (modoSimulacao) {
            return valorSimuladoAtm;
        }
        Random gerador = new Random();
        double leituraAtual = 0.8 + (gerador.nextDouble() * 0.7);
        this.setTemperatura(leituraAtual);
        return leituraAtual;
    }

    @Override
    public boolean verificarFuncionamento() {
        return getTemperatura() <= (limiteAlertaAtm * 1.50);
    }

    @Override
    public String retornarTipo() {
        return "Pressão";
    }

    // diagnósticos e alertas


    public String classificarNivelAlerta() {
        double pressaoAtual = getTemperatura();

        if (pressaoAtual <= limiteAlertaAtm) {
            return "NORMAL";
        } else if (pressaoAtual <= (limiteAlertaAtm * 1.15)) {
            return "ATENÇÃO";
        } else if (pressaoAtual <= (limiteAlertaAtm * 1.40)) {
            return "ALERTA";
        } else {
            return "CRÍTICO";
        }
    }

    @Override
    public void avaliarIntegridade() {
        System.out.println(" DIAGNÓSTICO DE COMPONENTE: " + retornarTipo().toUpperCase());
        System.out.println("ID: " + getId() + " | Nome: " + getNome() + " | Status: " + (isStatus() ? "LIGADO" : "DESLIGADO"));
        System.out.println("Pressão Atual Registrada: " + String.format("%.2f", getTemperatura()) + " atm");
        System.out.println("Limite Operacional Seguro: " + limiteAlertaAtm + " atm");
        System.out.println("Nível do Alerta de Pressão: [" + classificarNivelAlerta() + "]");

        if (verificarFuncionamento()) {
            System.out.println("Condição Física: OPERANTE.");
        } else {
            System.out.println("Condição Física: FALHA CRÍTICA!");
        }
    }


    // getters e setters

    public double getLimiteAlertaAtm() {
        return limiteAlertaAtm;
    }

    public void setLimiteAlertaAtm(double limiteAlertaAtm) {
        if (limiteAlertaAtm < 0.0) {
            throw new IllegalArgumentException("Erro de Segurança: A pressão em atm não pode ser negativa.");
        }
        this.limiteAlertaAtm = limiteAlertaAtm;
    }
}