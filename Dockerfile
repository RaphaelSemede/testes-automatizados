# Imagem base oficial com Java 17 e Maven pré-instalado
FROM maven:3.9.6-eclipse-temurin-21

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo de configuração do Maven para otimizar o cache de camadas
COPY pom.xml .

# Baixa as dependências do projeto para o cache da imagem
RUN mvn dependency:go-offline -B

# Copia o código-fonte dos testes para o container
COPY src ./src

# Comando executado ao iniciar o container para rodar a suíte de testes BDD
CMD ["mvn", "clean", "test"]