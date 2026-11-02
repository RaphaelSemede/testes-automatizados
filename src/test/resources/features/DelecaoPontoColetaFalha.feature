# language: pt

@regressivo
Funcionalidade: Deleção de Pontos de Coleta
  Como administrador da API
  Quero deletar um ponto de coleta cadastrado no sistema
  Para remover locais de descarte que foram desativados ou registrados incorretamente

  Cenário: Tentativa de deleção de ponto de coleta inexistente
    Dado que não exista um ponto de coleta cadastrado com o ID "9999"
    Quando eu enviar a requisição DELETE para o endpoint "/pontos-coleta" de deleção de pontos de coleta
    Então o status code da resposta de pontos de coleta deve ser 404
    E a mensagem de erro de pontos de coleta deve retornar "Ponto de coleta não encontrado com ID: 9999"