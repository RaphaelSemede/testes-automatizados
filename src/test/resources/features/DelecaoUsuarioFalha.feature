# language: pt
@regressivo
Funcionalidade: Deleção de Usuário
  Como um administrador da API
  Quero remover um usuário do sistema
  Para que os dados desatualizados ou contas inativas sejam excluídas

  Cenário: Tentativa de exclusão de usuário inexistente
  Dado que não exista um usuário cadastrado com o ID "999"
  Quando eu enviar a requisição DELETE para o endpoint "/usuarios"
  Então o status code da resposta deve ser 404
  E o corpo de resposta de erro da api deve retornar a mensagem "Usuário não encontrado com ID: 999"