# language: pt
@regressivo
Funcionalidade: Deleção de Usuário
  Como um administrador da API
  Quero remover um usuário do sistema
  Para que os dados desatualizados ou contas inativas sejam excluídas

  Contexto:   Cenário: Cadastro bem-sucedido de usuário
    Dado que eu tenha os seguintes dados do usuário:
      | campo | valor                 |
      | nome  | Raphael Semede        |
      | email | raphael.novo@xmail.com|
      | senha | 123456                |
      | role  | ADMIN                 |
    Quando eu enviar a requisição para o endpoint "/usuarios" de cadastro de usuários
    Então o status code da resposta deve ser 201

  Cenário: Exclusão bem-sucedida de usuário
    Dado que exista um usuário cadastrado com o ID "extraido"
    Quando eu enviar a requisição DELETE para o endpoint "/usuarios"
    Então o status code da resposta deve ser 204