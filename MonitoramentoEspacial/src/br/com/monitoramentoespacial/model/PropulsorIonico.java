package br.com.monitoramentoespacial.model;

public class PropulsorIonico extends SistemaPropulsao {

    private double nivelDesgaste; // desgaste físico

    public PropulsorIonico(String id, String nome) {
        super(id, nome);
        this.nivelDesgaste = 0.0; // inicia novo
    }

    //regra da clase mãe sendo seguida
    @Override
    public String obterTipoPropulsao() {
        return "Iónica (Gás Xenon)";
    }

    // regra de heranã (componente espacial)
    @Override
    public void avaliarIntegridade() {
        System.out.println(" DIAGNÓSTICO DE MOTORES: " + getNome().toUpperCase());
        System.out.println("ID: " + getId() + " | Tecnologia: " + obterTipoPropulsao());
        System.out.println("Status do Motor: " + (isStatus() ? "LIGADO" : "DESLIGADO"));
        System.out.println("Potência de Impulso: " + getPotenciaAtual() + "%");

        if (nivelDesgaste < 85.0) {
            System.out.println("Condição Física: OPERANTE. Desgaste em " + String.format("%.1f", nivelDesgaste) + "%.");
        } else {
            System.out.println("Condição Física: Perigo! Desgaste crítico dos motores detetado.");
        }
    }

    // Método para simular desgaste quando a nave acelera.
    public void aplicarDesgaste(double tempoUso) {
        if (isStatus() && getPotenciaAtual() > 0) {
            this.nivelDesgaste += (getPotenciaAtual() * 0.02 * tempoUso);
        }
    }
}
}
