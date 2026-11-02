# language: pt

@regressivo
Funcionalidade: Validação de Contrato da API de Resíduos
  Como cliente da API
  Quero validar as respostas das requisições de resíduos
  Para garantir a conformidade dos payloads com os schemas JSON estabelecidos

  Cenário: Validação de contrato no cadastro bem-sucedido de resíduo
    Dado que eu tenha os seguintes dados do resíduo:
      | campo        | valor               |
      | tipo         | PLASTICO            |
      | peso         | 15.50               |
      | dataRegistro | 2026-11-01T10:00:00 |
      | statusColeta | PENDENTE            |
      | usuarioId    | 1                   |
    Quando eu enviar a requisição para o endpoint "/residuos" de cadastro de resíduos
    Então o status code da resposta de resíduos deve ser 201
    E a resposta de resíduos deve estar em conformidade com o contrato "residuo-schema.json"
    Então a resposta da requisição de resíduos deve estar em conformidade com o contrato selecionado