# language: pt

Funcionalidade: Cadastro de Pontos de Coleta
  Como administrador da API
  Quero cadastrar um novo ponto de coleta no sistema
  Para disponibilizar locais adequados para o descarte de resíduos

  Cenário: Cadastro bem-sucedido de ponto de coleta
    Dado que eu tenha os seguintes dados do ponto de coleta:
      | campo            | valor                        |
      | endereco         | Rua das Flores, 123 - Centro |
      | capacidadeMaxima | 500.00                       |
      | quantidadeAtual  | 0.00                         |
    Quando eu enviar a requisição para o endpoint "/pontos-coleta" de cadastro de pontos de coleta
    Então o status code da resposta de pontos de coleta deve ser 201

  @padrão
  Cenário: Tentativa de cadastro de ponto de coleta sem endereço
    Dado que eu tenha os seguintes dados do ponto de coleta:
      | campo            | valor                        |
      | endereco         |                              |
      | capacidadeMaxima | 300.50                       |
      | quantidadeAtual  | 0.00                         |
    Quando eu enviar a requisição para o endpoint "/pontos-coleta" de cadastro de pontos de coleta
    Então o status code da resposta de pontos de coleta deve ser 400
    E a mensagem de erro de pontos de coleta deve retornar "O endereço é obrigatório"

  Cenário: Tentativa de cadastro com capacidade máxima inválida
    Dado que eu tenha os seguintes dados do ponto de coleta:
      | campo            | valor                        |
      | endereco         | Avenida Brasil, 1500         |
      | capacidadeMaxima | -50.00                       |
      | quantidadeAtual  | 0.00                         |
    Quando eu enviar a requisição para o endpoint "/pontos-coleta" de cadastro de pontos de coleta
    Então o status code da resposta de pontos de coleta deve ser 400
    E a mensagem de erro de pontos de coleta deve retornar "capacidadeMaxima: A capacidade máxima deve ser um valor positivo"