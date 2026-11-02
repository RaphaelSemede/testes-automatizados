# 🌍 Automação de Testes de API - Plataforma ESG

Este repositório contém a suíte de testes automatizados para a plataforma de **Gestão de Resíduos (ESG)**. O projeto foi desenvolvido para garantir a estabilidade, a conformidade de dados e a confiabilidade dos microsserviços da aplicação, cobrindo as APIs de **Usuários, Pontos de Coleta e Resíduos**.

---

## 🎯 Escopo das Validações

Atendendo aos requisitos de qualidade, este projeto implementa:

1. **Cenários BDD (Gherkin):** Cobertura abrangente com testes de caminho feliz (sucessos) e caminhos alternativos/negativos (falhas controladas, deleções inválidas, etc).
2. **Testes de Integração de API:**
   * Validação rigorosa de **Status Code** (200, 201, 400, 404, etc).
   * Verificação de integridade do **Corpo de Resposta (JSON)**.
3. **Testes de Contrato:** Implementação de validações baseadas em **JSON Schema** para assegurar que a estrutura dos dados trafegados entre os serviços permaneça inviolada.

---

## 🛠 Stack Tecnológica

* **Java 21+** 
* **Maven** (Gerenciamento de dependências e automação de build)
* **RestAssured** (Framework para testes de APIs RESTful)
* **Cucumber / Gherkin** (Motor BDD para escrita e execução de cenários)
* **JUnit** (Orquestrador de execução)
* **JSON Schema Validator** (Garantia de contrato de APIs)
* **Docker** (Containerização do ambiente)
* **GitHub Actions** (Pipeline de CI/CD)

---

## 📁 Estrutura do Projeto

O código-fonte segue as melhores práticas de organização de automação:

```text
.github/workflows/ci.yml       # Configuração da pipeline de integração contínua
src/test/
├── java/
│   ├── model/                 # Classes POJO para serialização/desserialização dos payloads
│   ├── runner/                # Classe executora (TestRunner.java)
│   ├── services/              # Classes de requisições REST (RestAssured)
│   └── steps/                 # Step definitions (Mapeamento do Gherkin para o Java)
└── resources/
    ├── features/              # Cenários BDD cobrindo Sucesso, Falhas e Contratos
    └── schemas/               # Arquivos .json para os testes de contrato das APIs
Dockerfile                     # Script de configuração do ambiente containerizado
```

---

## 🚀 Como Configurar e Executar

A suíte de testes foi desenhada para ser executada com facilidade em qualquer ambiente, seja localmente ou via pipeline.

### Pré-requisitos Locais
* **JDK 21 ou superior** configurado nas variáveis de ambiente.
* **Maven** (ou utilize os scripts `mvnw` / `mvnw.cmd` inclusos no projeto).
* Backend da aplicação em execução.

### Opção 1: Execução via Linha de Comando (Recomendado)
Na raiz do projeto (onde está localizado o `pom.xml`), execute:

```bash
mvn clean test
```

O Maven irá baixar as dependências, compilar o código e gerar os relatórios do Cucumber no diretório `target/`.

### Opção 2: Execução via IDE (IntelliJ / Eclipse)
1. Importe o projeto como um **Maven Project**.
2. Navegue até o pacote `src/test/java/runner`.
3. Execute a classe `TestRunner.java` diretamente (Run as > JUnit Test).

### Opção 3: Execução via Docker
Utilize o `Dockerfile` incluso na raiz do projeto para criar uma imagem conteinerizada do ambiente de testes e rodá-la isoladamente:

```bash
docker build -t test-suite-esg .
docker run --rm test-suite-esg
```

---

## 🔄 Pipeline de CI/CD (GitHub Actions)

A execução dos testes está automatizada na nuvem. O arquivo `.github/workflows/ci.yml` garante que a cada evento de `push` ou `pull request`, a suíte do Cucumber seja acionada no GitHub Actions, providenciando o *build* limpo e atestando que nenhuma API sofreu regressão.