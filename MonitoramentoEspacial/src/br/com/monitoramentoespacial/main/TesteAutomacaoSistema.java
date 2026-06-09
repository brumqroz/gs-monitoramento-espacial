package br.com.monitoramentoespacial.main;
import br.com.monitoramentoespacial.model.*;

public class TesteAutomacaoSistema {
    public static void main(String[] args) {
        System.out.println(" INICIANDO BATERIA DE TESTES AUTOMATIZADOS DA NAVE");


        // preparação do ambiente
        DadosMissao missaoTeste = new DadosMissao("Terra -> Marte", 100.0, 3, "SENHA123");
        SensorTemperatura termometro = new SensorTemperatura("ST-TESTE", "Termômetro Externo", 50.0);
        SensorPressao barometro = new SensorPressao("SP-TESTE", "Barômetro Interno", 1.0);
        PropulsorIonico motor = new PropulsorIonico("PROP-TESTE", "Motor Iônico de Teste");

        termometro.setStatus(true);
        barometro.setStatus(true);
        motor.setStatus(true);


        // TESTE 1:Teste de segurança e encapsulamento.

        System.out.println(">>> [TESTE 1] Verificando segurança de acesso e limites lógicos");

        System.out.println("- Tentando acessar rota com senha errada:");
        System.out.println(missaoTeste.getCoordenadas("SENHA_FALSA")); // deve bloquear.
        System.out.println("- Tentando colocar combustível impossível (-50%):");
        missaoTeste.setCombustivel(-50.0, "SENHA123"); // deve avisar erro de limite.



        //teste 2: validações e exceções.

        System.out.println("\n>>> [TESTE 2] Forçando limite físico abaixo do Zero Absoluto (-273.15 °C)");
        try {
            termometro.setLimiteAlerta(-300.0);
            System.out.println("Falha: O sistema aceitou uma temperatura impossível.");
        } catch (IllegalArgumentException e) {
            System.out.println("Sucesso: Tudo sob controle.");
            System.out.println("Erro do Sistema -> " + e.getMessage());
        }


        // teste 3: polimorfismo com interface do sensor

        System.out.println("\n>>> TESTE 3: Testando todos os sensores via Polimorfismo");
        // Colocamos sensores de tipos diferentes na mesma lista.
        Sensor[] redeDeSensores = {termometro, barometro};

        for (Sensor s : redeDeSensores) {
            System.out.println("- Lendo dado do sensor tipo [" + s.retornarTipo() + "]: " + s.lerValor());
            System.out.println("- Funcionamento normal? " + s.verificarFuncionamento());
        }



        // teste 4: degaste do motor de propulsão.

        System.out.println("\n>>> TESTE 4: Simulando desgaste crítico do Motor Iônico");
        motor.setPotenciaAtual(100.0); // Aceleração máxima

        System.out.println("Acelerando o motor por horas.");
        motor.aplicarDesgaste(500.0); // desgaste extremo
        motor.aplicarDesgaste(500.0);

        // motor desgastado:
        motor.avaliarIntegridade();


        System.out.println(" Bateria de testes concluída com sucesso!!");

    }
}