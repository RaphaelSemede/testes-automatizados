# language: pt

@regressivo
Funcionalidade: Deleção de Pontos de Coleta
  Como administrador da API
  Quero deletar um ponto de coleta cadastrado no sistema
  Para remover locais de descarte que foram desativados ou registrados incorretamente

  Contexto: Cadastro bem-sucedido de ponto de coleta
    Dado que eu tenha os seguintes dados do ponto de coleta:
      | campo            | valor                        |
      | endereco         | Rua das Flores, 123 - Centro |
      | capacidadeMaxima | 500.00                       |
      | quantidadeAtual  | 0.00                         |
    Quando eu enviar a requisição para o endpoint "/pontos-coleta" de cadastro de pontos de coleta
    Então o status code da resposta de pontos de coleta deve ser 201

  Cenário: Deleção bem-sucedida de um ponto de coleta
    Dado que exista um ponto de coleta cadastrado com o ID "extraido"
    Quando eu enviar a requisição DELETE para o endpoint "/pontos-coleta" de deleção de pontos de coleta
    Então o status code da resposta de pontos de coleta deve ser 204