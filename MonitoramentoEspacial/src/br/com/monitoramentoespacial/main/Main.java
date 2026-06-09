package br.com.monitoramentoespacial.main;

import br.com.monitoramentoespacial.model.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        //Inicializando os dados da missão
        DadosMissao missao = new DadosMissao("X: 145.2, Y: 890.4, Z: -12.3", 100.0, 5, "2CCPW2026");

        //Inicializando sensores
        SensorTemperatura termometro = new SensorTemperatura("ST-01", "Termômetro do Reator", 45.0);
        SensorPressao barometro = new SensorPressao("SP-01", "Barômetro da Cabine", 1.2);
        SensorRadiacao contadorGeiger = new SensorRadiacao("SR-01", "Sensor de Escudo Cósmico", 3.0);

        //Inicializando sistema de propulsão
        PropulsorIonico motorIonico = new PropulsorIonico("PROP-01", "Motor de Impulso de Xenon");

        // garantindo que os componentes começam ligados
        termometro.setStatus(true);
        barometro.setStatus(true);
        contadorGeiger.setStatus(true);
        motorIonico.setStatus(true);

        int opcao = 0;

        System.out.println("SISTEMA DE MONITORAMENTO ESPACIAL INICIALIZADO");


        do {
            System.out.println("\n PAINEL PRINCIPAL DE BORDO ");
            System.out.println("1. Telemetria Geral (Ler todos os sensores)");
            System.out.println("2. Diagnóstico Físico (Sensores e Motores)");
            System.out.println("3. Consultar Dados Confidenciais da Missão");
            System.out.println("4. Alterar Dados da Missão (Requer Senha)");
            System.out.println("5. Simular/Forçar Alerta em um Sensor");
            System.out.println("6. Gerir Motores (Acelerar Nave)");
            System.out.println("7. Desligar/Ligar Componentes (Manutenção)");
            System.out.println("8. Encerrar Sistema");
            System.out.print("Escolha uma opção: ");

            opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\n TELEMETRIA: COLETANDO LEITURAS ATUAIS");
                    System.out.printf("> %s: %.2f °C\n", termometro.getNome(), termometro.lerValor());
                    System.out.printf("> %s: %.2f atm\n", barometro.getNome(), barometro.lerValor());
                    System.out.printf("> %s: %.2f mSv\n", contadorGeiger.getNome(), contadorGeiger.lerValor());
                    System.out.printf("> %s: Potência atual em %.1f%%\n", motorIonico.getNome(), motorIonico.getPotenciaAtual());
                    break;

                case 2:
                    System.out.println("\nDIAGNÓSTICO: AVALIANDO SISTEMAS FÍSICOS");
                    termometro.avaliarIntegridade();
                    barometro.avaliarIntegridade();
                    contadorGeiger.avaliarIntegridade();
                    motorIonico.avaliarIntegridade();
                    break;

                case 3:
                    System.out.print("Digite a senha de Comandante para ver as coordenadas: ");
                    String senhaLeitura = leitor.nextLine();
                    System.out.println("\n DADOS DA MISSÃO");
                    System.out.println("Coordenadas GPS: " + missao.getCoordenadas(senhaLeitura));
                    System.out.println("Nível do Tanque: " + missao.getCombustivel() + "%");
                    System.out.println("Tripulantes a Bordo: " + missao.getNumeroTripulantes());
                    break;

                case 4:
                    System.out.print("Digite a senha de Comandante para alterações: ");
                    String senhaEscrita = leitor.nextLine();

                    if (missao.validarSenha(senhaEscrita)) {
                        System.out.println("\nO que deseja alterar?");
                        System.out.println("1. Atualizar Coordenadas/Rota");
                        System.out.println("2. Consumo/Ajuste de Combustível");
                        System.out.print("Opção: ");
                        int subOpcao = leitor.nextInt();
                        leitor.nextLine();

                        if (subOpcao == 1) {
                            System.out.print("Digite as novas coordenadas espaciais: ");
                            String novasCoord = leitor.nextLine();
                            missao.setCoordenadas(novasCoord, senhaEscrita);
                        } else if (subOpcao == 2) {
                            System.out.print("Digite o novo nível de combustível (%): ");
                            double novoComb = leitor.nextDouble();
                            missao.setCombustivel(novoComb, senhaEscrita);
                        } else {
                            System.out.println("Opção inválida.");
                        }
                    } else {
                        // Se errar a senha, é expulso do menu imediatamente!
                        System.out.println("ACESSO NEGADO: Senha de comandante incorreta");
                    }
                    break;

                case 5:
                    System.out.println("\nSIMULADOR: SELECIONE O SENSOR");
                    System.out.println("1. " + termometro.getNome() + " (°C)");
                    System.out.println("2. " + barometro.getNome() + " (atm)");
                    System.out.println("3. " + contadorGeiger.getNome() + " (mSv)");
                    System.out.print("Escolha: ");
                    int escolhaSensor = leitor.nextInt();

                    System.out.print("Digite o valor crítico que deseja testar: ");
                    double valorForcado = leitor.nextDouble();

                    if (escolhaSensor == 1) termometro.ativarLeituraManual(valorForcado);
                    else if (escolhaSensor == 2) barometro.ativarLeituraManual(valorForcado);
                    else if (escolhaSensor == 3) contadorGeiger.ativarLeituraManual(valorForcado);
                    else System.out.println("Sensor inválido.");
                    break;

                case 6:
                    System.out.println("\nCONTROLE DE PROPULSÃO:");
                    if (!motorIonico.isStatus()) {
                        System.out.println("AVISO: O motor está desligado no painel de manutenção. Impossível acelerar.");
                        break;
                    }
                    System.out.print("Digite a nova potência de aceleração desejada (0 a 100%): ");
                    double novaPotencia = leitor.nextDouble();
                    motorIonico.setPotenciaAtual(novaPotencia);

                    // Simula o desgaste do motor proporcional ao tempo de aceleração (exemplo: 10 segundos)
                    motorIonico.aplicarDesgaste(10.0);
                    break;

                case 7:
                    System.out.println("\nMANUTENÇÃO: ALTERAR STATUS DE HARDWARE");
                    System.out.println("1. Alternar status do Termômetro (Atual: " + (termometro.isStatus() ? "LIGADO" : "DESLIGADO") + ")");
                    System.out.println("2. Alternar status do Barômetro (Atual: " + (barometro.isStatus() ? "LIGADO" : "DESLIGADO") + ")");
                    System.out.println("3. Alternar status do Contador Geiger (Atual: " + (contadorGeiger.isStatus() ? "LIGADO" : "DESLIGADO") + ")");
                    System.out.println("4. Alternar status do Motor Iónico (Atual: " + (motorIonico.isStatus() ? "LIGADO" : "DESLIGADO") + ")");
                    System.out.print("Escolha: ");
                    int opStatus = leitor.nextInt();

                    if (opStatus == 1) termometro.setStatus(!termometro.isStatus());
                    else if (opStatus == 2) barometro.setStatus(!barometro.isStatus());
                    else if (opStatus == 3) contadorGeiger.setStatus(!contadorGeiger.isStatus());
                    else if (opStatus == 4) motorIonico.setStatus(!motorIonico.isStatus());

                    System.out.println("SISTEMA: Status do componente modificado com sucesso.");
                    break;

                case 8:
                    System.out.println("\nDesligando Painel de Bordo.");
                    break;

                default:
                    System.out.println("Opção inválida no painel. Tente novamente.");
            }
        } while (opcao != 8);

        leitor.close();
    }
}