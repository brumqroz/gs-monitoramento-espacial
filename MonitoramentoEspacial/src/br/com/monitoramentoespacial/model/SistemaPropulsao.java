package br.com.monitoramentoespacial.model;
public abstract class SistemaPropulsao extends ComponenteEspacial {

    private double potenciaAtual; // aceleração em porcentagem, de 0 até 100

    public SistemaPropulsao(String id, String nome) {
        super(id, nome);
        this.potenciaAtual = 0.0; // motores começam desligados
    }


    // getter e setters

    public double getPotenciaAtual() {
        return potenciaAtual;
    }

    public void setPotenciaAtual(double potenciaAtual) {
        if (potenciaAtual >= 0.0 && potenciaAtual <= 100.0) {
            this.potenciaAtual = potenciaAtual;
            System.out.println("MOTOR: Potência do " + getNome() + " ajustada para " + potenciaAtual + "%");
        } else {
            System.out.println("ERRO: A potência do motor deve estar entre 0% e 100%.");
        }
    }

    //método - funcionamento do propulsor
    public abstract String obterTipoPropulsao();
}