#language: pt

Funcionalidade: Cadastro de Usuário
Como usuário da API
Quero cadastrar um novo usuário no sistema
Para que ele possa registrar e gerenciar seus resíduos

  Cenário: Cadastro bem-sucedido de usuário
    Dado que eu tenha os seguintes dados do usuário:
      | campo | valor                 |
      | nome  | Raphael Semede        |
      | email | raphael.novo@email.com|
      | senha | 123456                |
      | role  | ADMIN                 |
    Quando eu enviar a requisição para o endpoint "/usuarios" de cadastro de usuários
    Então o status code da resposta deve ser 201

  @padrão
  Cenário: Cadastro de usuário sem sucesso por e-mail duplicado
    Dado que eu tenha os seguintes dados do usuário:
      | campo | valor             |
      | nome  | Raphael Semede    |
      | email | raphael@email.com |
      | senha | 123456            |
      | role  | USER              |
    Quando eu enviar a requisição para o endpoint "/usuarios" de cadastro de usuários
    Então o status code da resposta deve ser 409
    E o corpo de resposta de erro da api deve retornar a mensagem "E-mail já cadastrado no sistema: raphael@email.com"