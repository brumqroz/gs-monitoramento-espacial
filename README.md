# Global Solution - Monitoramento Espacial

O objetivo principal deste software é demonstrar a aplicação prática e rigorosa dos **Pilares da Programação Orientada a Objetos (POO)** em um cenário de missão crítica. O sistema lida com dados físicos (como temperatura e pressão), desgaste de materiais espaciais e autenticação de usuários para ações restritas.

## Conceitos Utilizados: 

* **Linguagem:** Java
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

<img width="930" height="227" alt="Captura de tela 2026-06-09 224553" src="https://github.com/user-attachments/assets/e55ce2db-7167-4ece-84ac-2324442351d2" />


2.  **Diagnóstico Físico:** Avaliação da integridade de todos os componentes da nave e cálculo de fadiga do motor iônico.

<img width="603" height="683" alt="Captura de tela 2026-06-09 224837" src="https://github.com/user-attachments/assets/5e31c9fd-330a-4c8f-a2d6-88eccd9d7c40" />

3.  **Segurança de Dados:** Consulta e alteração de rotas e combustível estritamente protegidas por autenticação.

<img width="722" height="207" alt="Captura de tela 2026-06-09 224945" src="https://github.com/user-attachments/assets/546a6b90-3afc-4a5e-9802-8a3e6a2e95f1" />

4.  **Simulador:** Capacidade de injetar leituras manuais críticas nos sensores para testar os alertas automáticos da nave.

<img width="637" height="268" alt="Captura de tela 2026-06-09 225150" src="https://github.com/user-attachments/assets/e2e99532-1d7d-45be-a3b9-2d88ea9d98f4" />

5.  **Controle de Propulsão:** Aceleração do Motor Iônico com cálculo de desgaste baseado no uso.

<img width="660" height="143" alt="Captura de tela 2026-06-09 225319" src="https://github.com/user-attachments/assets/95becd88-5ef2-4005-9cde-276e48bb3805" />

6.  **Manutenção:** Ligar e desligar módulos físicos individualmente.

<img width="660" height="143" alt="Captura de tela 2026-06-09 225319" src="https://github.com/user-attachments/assets/7a4bb12e-f63d-411f-bc38-6841905b8d2c" />

**SISTEMA COMPLETO**

<img width="870" height="528" alt="Captura de tela 2026-06-09 224346" src="https://github.com/user-attachments/assets/af5d9f88-ac61-49ed-ab97-13ef47ca734e" />

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
