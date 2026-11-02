# 🌍 Automação de Testes - Plataforma ESG (Gestão de Resíduos)

Este repositório contém o projeto de Automação de Testes para a plataforma focada em **ESG (Sustentabilidade, Responsabilidade Social e Governança)**. O objetivo principal desta camada de testes é garantir a qualidade, estabilidade e confiabilidade das APIs de Gestão de Resíduos, Pontos de Coleta e Usuários.

## 🌱 Integração com os Pilares ESG

Os testes automatizados implementados não garantem apenas a qualidade técnica (reduzindo retrabalho e otimizando recursos computacionais), mas também asseguram que o sistema cumpra seu papel social e ambiental:

* **Ambiental (Gestão de Resíduos e Reciclagem):** Validação rígida das APIs de `PontoColeta` e `Residuo`, garantindo que o registro e roteamento para descarte funcionem sem falhas.

* **Governança e Compliance:** Testes de validação de dados e contratos de API (JSON Schema) que asseguram a integridade, rastreabilidade e conformidade das informações trafegadas, prevenindo falhas de segurança e corrupção de dados.

## 🛠 Tecnologias Utilizadas

O projeto foi desenvolvido em **Java** e utiliza as seguintes ferramentas para garantir as melhores práticas de testes automatizados:

* **Java 17+** (Linguagem de programação)
* **Maven** (Gerenciamento de dependências e build)
* **Cucumber / Gherkin** (Implementação de BDD - Behavior Driven Development)
* **RestAssured** (Automação e validação de requisições HTTP/REST)
* **JUnit** (Motor de execução de testes)
* **JSON Schema Validator** (Validação de contratos de API)

## 🧪 Estrutura de Testes

Os testes cobrem tanto cenários positivos (Caminho Feliz) quanto negativos (Falhas) e validação de contratos.

Foram criados e automatizados cenários em BDD cobrindo:

1. **APIs de Ponto de Coleta:** Cadastro, Deleção (sucesso e falha) e Validação de Contrato.
2. **APIs de Resíduos:** Cadastro, Deleção (sucesso e falha) e Validação de Contrato.
3. **APIs de Usuários:** Cadastro, Deleção (sucesso e falha) e Validação de Contrato.

### Validações Realizadas nas APIs:

* ✅ Validação de **Status Code** (ex: 200, 201, 400, 404).
* ✅ Validação do **Corpo da Resposta (JSON)**.
* ✅ Testes de **Contrato (JSON Schema)** para garantir a estrutura correta dos dados.

## 🚀 Como Configurar e Executar (Localmente)

### Pré-requisitos

* [JDK 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) instalado e configurado nas variáveis de ambiente.
* [Maven](https://maven.apache.org/download.cgi) instalado e configurado nas variáveis de ambiente.
* A API principal (backend) deve estar em execução para que os testes possam se comunicar com ela. 

### Execução via Linha de Comando (Terminal)

Abra o terminal na pasta raiz do projeto (onde está o arquivo `pom.xml`) e execute o seguinte comando:

```bash
mvn clean test
```

Este comando fará o download de todas as dependências necessárias, compilará o código e executará a suíte de testes.

### Execução via IDE (IntelliJ IDEA / Eclipse)

1. Importe o projeto como um "Projeto Maven".
2. Aguarde a IDE baixar as dependências.
3. Navegue até a pasta `src/test/java/runner`.
4. Clique com o botão direito no arquivo `TestRunner.java` e selecione **"Run 'TestRunner'"**.

## 🔄 Execução em Pipeline (CI/CD - GitHub Actions)

Este projeto já possui integração contínua (CI) configurada nativamente. A execução dos testes automatizados ocorre de forma automática através do **GitHub Actions**.

O pipeline está definido no arquivo de workflow `.github/workflows/ci.yml`. 

* **Acionamento:** O workflow é engatilhado automaticamente a cada evento de `push` ou `pull request` realizado no repositório.
* **Processo:** O GitHub Actions provisiona um ambiente, configura o JDK, instala as dependências via Maven e executa o comando `mvn clean test`.
* **Resultado:** Desta forma, é garantida a qualidade e a estabilidade da aplicação a cada nova atualização de código, sem a necessidade de intervenção manual para a rodada de testes de regressão.

## 📁 Estrutura de Diretórios Relevantes

```text
.github/
└── workflows/       
    └── ci.yml       # Configuração do Pipeline de CI/CD
src/test/
├── java/
│   ├── model/       # Classes de representação de dados (POJOs)
│   ├── runner/      # Classe executora do Cucumber (TestRunner.java)
│   ├── services/    # Classes responsáveis pelas requisições usando RestAssured
│   └── steps/       # Definição dos passos do Gherkin (Step Definitions)
└── resources/
    ├── features/    # Arquivos .feature escritos em Gherkin com os cenários
    └── schemas/     # Arquivos .json com os esquemas de contrato das APIs
```

*Este projeto foi desenvolvido como requisito de atividade avaliativa da disciplina de Testes Automatizados.*