package br.com.monitoramentoespacial.model;

public class DadosMissao {

    private String coordenadas;
    private double combustivel; // Em porcentagem
    private int numeroTripulantes;
    private String senhaAdmin;

    public DadosMissao(String coordenadas, double combustivel, int numeroTripulantes, String senhaAdmin) {
        this.coordenadas = coordenadas;
        this.combustivel = combustivel;
        this.numeroTripulantes = numeroTripulantes;
        this.senhaAdmin = senhaAdmin;
    }

    // método privado de validação

    private boolean validarSenha(String senhaDigitada) {
        return this.senhaAdmin.equals(senhaDigitada);
    }


    // getters

    // leitura protegida
    public String getCoordenadas(String senhaDigitada) {
        if (validarSenha(senhaDigitada)) {
            return this.coordenadas;
        }
        return "[ACESSO NEGADO] Senha incorreta. Coordenadas retidas.";
    }

    // leitura livre
    public double getCombustivel() {
        return this.combustivel;
    }

    public int getNumeroTripulantes() {
        return this.numeroTripulantes;
    }


    // setters

    public void setCoordenadas(String novasCoordenadas, String senhaDigitada) {
        if (validarSenha(senhaDigitada)) {
            this.coordenadas = novasCoordenadas;
            System.out.println("SUCESSO - Rota espacial alterada para: " + novasCoordenadas);
        } else {
            System.out.println("ALERTA DE SEGURANÇA: Senha incorreta! Alteração de rota bloqueada.");
        }
    }

    public void setCombustivel(double novoCombustivel, String senhaDigitada) {
        if (validarSenha(senhaDigitada)) {
            if (novoCombustivel >= 0.0 && novoCombustivel <= 100.0) {
                this.combustivel = novoCombustivel;
                System.out.println("SUCESSO - Nível de combustível ajustado para: " + novoCombustivel + "%");

                // Dispara o alerta automaticamente se o nível ficar crítico.
                verificarAlertaCombustivel();
            } else {
                System.out.println("ERRO: O nível de combustível deve estar entre 0.0% e 100.0%.");
            }
        } else {
            System.out.println("ALERTA DE SEGURANÇA: Senha incorreta! Alteração de combustível bloqueada.");
        }
    }


    // métodos internos de segurança

    private void verificarAlertaCombustivel() {
        if (this.combustivel < 20.0) {
            System.out.println("ALERTA CRÍTICO: Reserva de combustível acionada!");
        }
    }
}