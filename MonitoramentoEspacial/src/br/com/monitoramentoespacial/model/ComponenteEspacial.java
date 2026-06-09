package br.com.monitoramentoespacial.model;

public abstract class ComponenteEspacial {
    // Atributos comuns
    private String id;
    private String nome;
    private boolean status;
    private double temperatura;

    // Construtor usando os Setters para reaproveitar as validações!
    public ComponenteEspacial(String id, String nome) {
        this.setId(id);
        this.setNome(nome);
        this.status = false;
        this.temperatura = 20.0; // Pegando a sua ideia de começar com 20 graus!
    }

    // Métodos concretos
    public void ligar() {
        this.status = true;
        System.out.println("[SISTEMA DE CONTROLE] Componente  ["+ nome +"] (" + id +") = ON");
    }

    public void desligar() {
        this.status = false;
        System.out.println("[SISTEMA DE CONTROLE] Componente  ["+ nome +"] (" + id +") = OFF");
    }

    // Método abstrato
    public abstract void avaliarIntegridade();



    // GETTERS E SETTERS + VALIDAÇÕES:

    public String getId() { return id; }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: O ID do componente não pode ser nulo ou vazio.");
        }
        this.id = id;
    }

    public String getNome() { return nome; }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: O nome do componente não pode ser nulo ou vazio.");
        }
        this.nome = nome;
    }

    public boolean isStatus() { return status; }

    public double getTemperatura() { return temperatura; }

    protected void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }


    public void setStatus(boolean status) {
        this.status = status;
    }
}
