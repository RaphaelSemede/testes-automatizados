# language: pt
Funcionalidade: Cadastro de Resíduos
  Como usuário da API
  Quero cadastrar um novo resíduo no sistema
  Para que eu possa registrar e gerenciar meus descartes recicláveis

  Cenário: Cadastro bem-sucedido de resíduo
    Dado que eu tenha os seguintes dados do resíduo:
      | campo        | valor               |
      | tipo         | PLASTICO            |
      | peso         | 15.50               |
      | dataRegistro | 2026-11-01T10:00:00 |
      | statusColeta | PENDENTE            |
      | usuarioId    | 3                   |
    Quando eu enviar a requisição para o endpoint "/residuos" de cadastro de resíduos
    Então o status code da resposta de resíduos deve ser 201

  Cenário: Cadastro de resíduo sem sucesso por usuário inexistente
    Dado que eu tenha os seguintes dados do resíduo:
      | campo        | valor               |
      | tipo         | PAPEL               |
      | peso         | 5.00                |
      | dataRegistro | 2026-11-01T10:00:00 |
      | statusColeta | PENDENTE            |
      | usuarioId    | 9999                |
    Quando eu enviar a requisição para o endpoint "/residuos" de cadastro de resíduos
    Então o status code da resposta de resíduos deve ser 404
    E a mensagem de erro de resíduos deve retornar "Usuário não encontrado com ID: 9999"