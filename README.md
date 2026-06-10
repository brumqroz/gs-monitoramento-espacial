# Global Solution - Monitoramento Espacial

O objetivo principal deste software é demonstrar a aplicação prática e rigorosa dos **Pilares da Programação Orientada a Objetos (POO)** em um cenário de missão crítica. O sistema lida com dados físicos (como temperatura e pressão), desgaste de materiais espaciais e autenticação de usuários para ações restritas.

## Conceitos Utilizados: 

* **Linguagem:** Java (JDK 17+)
* **Paradigmas Aplicados:**
    * **Abstração:** Classes base (`ComponenteEspacial`, `SistemaPropulsao`) e Interfaces (`Sensor`) definindo o comportamento do hardware.
    * **Herança:** Reaproveitamento de código estrutural de componentes físicos para sensores específicos e motores.
    * **Polimorfismo:** Tratamento unificado de diferentes tipos de sensores através de uma interface comum.
    * **Encapsulamento:** Proteção estrita de dados sensíveis da missão (como rotas e níveis de combustível) através de validação por senha de Comandante.
    * **Tratamento de Exceções:** Bloqueio de valores impossíveis (como temperaturas abaixo do zero absoluto) utilizando `try/catch` e `IllegalArgumentException`.

---

## ️Funcionalidades do Painel de Bordo

O sistema conta com um menu interativo executado via terminal, oferecendo as seguintes funcionalidades:

1.  **Telemetria Geral:** Leitura em tempo real dos sensores de Temperatura, Pressão e Radiação, além da potência atual dos motores.
2.  **Diagnóstico Físico:** Avaliação da integridade de todos os componentes da nave e cálculo de fadiga do motor iônico.
3.  **Segurança de Dados:** Consulta e alteração de rotas e combustível estritamente protegidas por autenticação.
4.  **Simulador:** Capacidade de injetar leituras manuais críticas nos sensores para testar os alertas automáticos da nave.
5.  **Controle de Propulsão:** Aceleração do Motor Iônico com cálculo de desgaste baseado no uso.
6.  **Manutenção:** Ligar e desligar módulos físicos individualmente.

---

##  Estrutura de Pastas

O repositório está organizado de forma limpa e profissional:

* `/src/br/com/monitoramentoespacial/model/`: Contém todo o ecossistema de classes estruturais (Sensores, Propulsores, Dados da Missão e Interfaces).
* `/src/br/com/monitoramentoespacial/main/`: Contém os executáveis do projeto.
    * `Main.java`: O painel interativo do usuário final.
    * `TesteAutomacaoSistema.java`: Uma suíte de testes automatizados focada em provar a segurança e o tratamento de erros lógicos do sistema.

---

## Como Executar

1.  Clone este repositório para a sua máquina local.
2.  Abra o projeto na sua IDE de preferência (recomendado: **IntelliJ IDEA**).
3.  Para usar o modo interativo, navegue até `src/.../main/Main.java` e execute o arquivo.
4.  A senha padrão configurada para o Comandante realizar alterações nos dados da missão é: **`FIAP2026`** (pode ser alterada no código fonte da Main).
5.  Para rodar os testes de carga e segurança sem interação manual, execute o arquivo `TesteAutomacaoSistema.java`.