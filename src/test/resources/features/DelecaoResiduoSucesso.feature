#language: pt
@regressivo
Funcionalidade: Deleção de Resíduos
  Como usuário da API
  Quero deletar um resíduo cadastrado no sistema
  Para remover registros de descartes incorretos ou cancelados

  Contexto: Cadastro bem-sucedido de resíduo
      Dado que eu tenha os seguintes dados do resíduo:
        | campo        | valor               |
        | tipo         | PLASTICO            |
        | peso         | 15.50               |
        | dataRegistro | 2026-11-01T10:00:00 |
        | statusColeta | PENDENTE            |
        | usuarioId    | 2                   |
      Quando eu enviar a requisição para o endpoint "/residuos" de cadastro de resíduos
      Então o status code da resposta de resíduos deve ser 201

    Cenário: Deleção bem-sucedida de um resíduo
      Dado que exista um residuo cadastrado com o ID "extraido"
      Quando eu enviar a requisição DELETE para o endpoint "/residuos" de deleção de resíduos
      Então o status code da resposta de resíduos deve ser 204