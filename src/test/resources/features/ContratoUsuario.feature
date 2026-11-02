# language: pt

@regressivo
Funcionalidade: Gestão de Usuários e Validação de Contratos
  Como cliente da API
  Quero realizar operações de cadastro e exclusão de usuários
  Para garantir o correto funcionamento dos endpoints e a conformidade dos contratos JSON

  Cenário: Cadastro bem-sucedido com validação de contrato
    Dado que eu tenha os seguintes dados do usuário:
      | campo | valor                      |
      | nome  | Raphael Semede             |
      | email | raphael.contrato@xmail.com |
      | senha | 123456                     |
      | role  | ADMIN                      |
    Quando eu enviar a requisição para o endpoint "/usuarios" de cadastro de usuários
    Então o status code da resposta deve ser 201
    E a resposta deve estar em conformidade com o contrato "usuario-schema.json"
    Então a resposta da requisição deve estar em conformidade com o contrato selecionado