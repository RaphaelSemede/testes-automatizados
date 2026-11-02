#language: pt
@regressivo
Funcionalidade: Deleção de Resíduos
  Como usuário da API
  Quero deletar um resíduo cadastrado no sistema
  Para remover registros de descartes incorretos ou cancelados

  Cenário: Tentativa de deleção de resíduo inexistente
    Dado que não exista um resíduo cadastrado com o ID "9999"
    Quando eu enviar a requisição DELETE para o endpoint "/residuos" de deleção de resíduos
    Então o status code da resposta de resíduos deve ser 404
    E a mensagem de erro de resíduos deve retornar "Resíduo não encontrado com ID: 9999"