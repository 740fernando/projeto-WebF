# projeto-WebF
## Framework - Conjunto de classes suporte para desenvolvimento web. 

- Apenas um servlet na aplicação

- Criado classes de ações para cada funcionalidade na interface gráfica

- Foi definido servlets com anotações, função disponível a partir do java ee 6.

- O mapeamento do servlets é tudo que terminar com ".action"

- A primeira vez que ControllerServlet for carregado, ele faz a leitura do arquivo action.properties.

- action.properties mapeia o nomes das actions para classes.

- Quando ocorre uma requisição tanto GET, quanto POST delega a execução para o método "Process"

- O método "Process", executa o método "getServletPath" para saber qual foi caminho que foi chamado

