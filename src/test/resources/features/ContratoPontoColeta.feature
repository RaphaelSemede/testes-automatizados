# language: pt

@regressivo
Funcionalidade: Validação de Contrato da API de Pontos de Coleta
  Como cliente da API
  Quero validar as respostas das requisições de pontos de coleta
  Para garantir a conformidade dos payloads com os schemas JSON estabelecidos

  Cenário: Validação de contrato no cadastro bem-sucedido de ponto de coleta
    Dado que eu tenha os seguintes dados do ponto de coleta:
      | campo            | valor                        |
      | endereco         | Rua das Flores, 123 - Centro |
      | capacidadeMaxima | 500.00                       |
      | quantidadeAtual  | 0.00                         |
    Quando eu enviar a requisição para o endpoint "/pontos-coleta" de cadastro de pontos de coleta
    Então o status code da resposta de pontos de coleta deve ser 201
    E a resposta de pontos de coleta deve estar em conformidade com o contrato "pontoColeta-schema.json"
    Então a resposta da requisição de ponto de coleta deve estar em conformidade com o contrato selecionado