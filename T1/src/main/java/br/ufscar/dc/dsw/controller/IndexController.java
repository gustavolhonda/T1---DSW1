package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(name = "Index", urlPatterns = { "/index.jsp", "/logout.jsp" })
public class IndexController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Erro erros = new Erro();
        if (request.getParameter("bOK") != null) {
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            if (login == null || login.isEmpty()) {
                erros.add("Login não informado!");
            }
            if (senha == null || senha.isEmpty()) {
                erros.add("Senha não informada!");
            }
            if (!erros.isExisteErros()) {
                UsuarioDAO dao = new UsuarioDAO();
                Usuario usuario = dao.getbyEmail(login);
                if (usuario != null) {
                    if (usuario.getSenha().equalsIgnoreCase(senha)) {
                        String papel = usuario.getPapel();
                        switch (papel) {
                            case "ADMIN":
                                request.getSession().setAttribute("usuarioLogado", usuario);
                                response.sendRedirect("usuarios/");
                                break;
                            case "CLIENTE":
                                ClienteDAO clienteDAO = new ClienteDAO();
                                Cliente cliente = new Cliente();

                                cliente = clienteDAO.get(usuario.getId());
                                
                                request.getSession().setAttribute("usuarioLogado", cliente);
                                response.sendRedirect("agendamentos/");
                                break;
                            case "PROFISSIONAL":
                                ProfissionalDAO profissionalDAO = new ProfissionalDAO();
                                Profissional profissional = new Profissional();

                                profissional = profissionalDAO.get(usuario.getId());
                                
                                request.getSession().setAttribute("usuarioLogado", profissional);
                                response.sendRedirect("agendamentos/");
                                break;
                            default:
                                erros.add("Papel de usuário desconhecido!");
                                request.setAttribute("mensagens", erros);
                                RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                                rd.forward(request, response);
                                break;
                        }
                        return;
                    } else {
                        erros.add("Senha inválida!");
                    }
                } else {
                    erros.add("Usuário não encontrado!");
                }
            }
        }
        request.getSession().invalidate();
        request.setAttribute("mensagens", erros);
        String URL = "/login.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(URL);
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
