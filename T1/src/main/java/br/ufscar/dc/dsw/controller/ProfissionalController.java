package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/profissionais/*")
public class ProfissionalController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProfissionalDAO dao;

    @Override
    public void init() {
        dao = new ProfissionalDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        Erro erros = new Erro();

        if (usuario == null) {
            lista(request, response);
        } else if ((!usuario.getPapel().equals("ADMIN")) && (!usuario.getPapel().equals("CLIENTE"))) {
            erros.add("Acesso não autorizado!");
            erros.add("Apenas Papel [ADMIN] tem acesso a essa página");
            request.setAttribute("mensagens", erros);
            RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
            rd.forward(request, response);
            return;
        }

        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insercao":
                    insere(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém o parâmetro de especialidade da requisição
        String especialidade = request.getParameter("especialidade");
		List<String> especialidades = dao.getEspecialidades();
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

        // Se o parâmetro especialidade não estiver presente, será null
        // Caso esteja presente, ele será usado para filtrar
        List<Profissional> listaProfissionais;
        if (especialidade != null && !especialidade.trim().isEmpty()) {
            // Filtra a lista por especialidade
            listaProfissionais = dao.getAll(especialidade);
        } else {
            // Caso contrário, lista todos os profissionais
            listaProfissionais = dao.getAll(null);
        }

        // Define a lista de profissionais como um atributo da requisição
        request.setAttribute("listaProfissionais", listaProfissionais);
        request.setAttribute("especialidades", especialidades);
        request.setAttribute("usuario", usuario);

        // Encaminha a requisição para a página JSP de resultados
        if ((usuario == null) || (!(usuario.getPapel().equals("ADMIN")))) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/listar.jsp");
            dispatcher.forward(request, response);
        } else if ((usuario.getPapel().equals("ADMIN"))) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/profissional/lista.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/profissional/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Profissional profissional = dao.get(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/profissional/formulario.jsp");
        request.setAttribute("profissional", profissional);
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String papel = request.getParameter("papel");
        String especialidade = request.getParameter("especialidade");

        Profissional profissional = new Profissional(nome, email, senha, cpf, papel, especialidade);

        dao.insert(profissional);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Integer id = Integer.parseInt(request.getParameter("id"));
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String papel = "PROFISSIONAL";
        String especialidade = request.getParameter("especialidade");

        Profissional profissional = new Profissional(id, nome, email, senha, cpf, papel, especialidade);

        dao.update(profissional);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));

        Profissional profissional = new Profissional(id);
        dao.delete(profissional);
        response.sendRedirect("lista");
    }

}
