package br.com.monitoramentoespacial.model;

public interface Sensor {

    double lerValor();
    boolean verificarFuncionamento();
    String retornarTipo();

}

/// A interface define as regras básicas para o funcionamento dos sensores.
///Com isso, não importa o que o sensor mede na prática, ele sempre vai reagir às mesmas requisições.