## Fluxo da aplicação

1. Tela de login: O formulário em `login.jsp` envia os dados para `index.jsp` via método POST.
2. Processamento do login: O `IndexController.java` é responsável pelo processamento do login. 
   1. **Recebimento dos Dados do Formulário:**
      - Quando o formulário em `login.jsp` é submetido, os dados são enviados para `index.jsp`, que é gerenciado pelo `IndexController.java`.
   2. **Validação dos Dados:**
      - O controlador verifica se o botão de submissão (`bOK`) foi pressionado.
      - Ele obtém os valores de `login` e `senha` dos parâmetros da requisição.
      - Se `login` ou `senha` estiverem vazios, erros são adicionados ao objeto `Erro`.
   3. **Autenticação do Usuário:**
      - Se não houver erros, o `UsuarioDAO` é utilizado para buscar o usuário pelo email utilizado no login.
      - Se o usuário for encontrado e a senha corresponder, o usuário é autenticado e a sessão é configurada com os atributos do usuário logado e o contexto.
      - Dependendo do papel do usuário (`ADMINISTRADOR`, ` CLIENTE` ou `PROFISSIONAL`), o controlador  instancia um objeto da classe correspondete e redireciona para a página apropriada (decidir para qual página ir de acordo com o papel do usuário!)
3. 