package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
                                response.sendRedirect("admin/");
                                break;
                            case "CLIENTE":
                                Cliente cliente = new Cliente();
                                cliente.setId(usuario.getId());
                                cliente.setSenha(usuario.getSenha());
                                cliente.setNome(usuario.getNome());
                                cliente.setEmail(usuario.getEmail());
                                cliente.setPapel(usuario.getPapel());
                                // Set atributos específicos de Cliente, se houver
                                request.getSession().setAttribute("usuarioLogado", cliente);
                                response.sendRedirect("cliente/");
                                break;
                            case "PROFISSIONAL":
                                Profissional profissional = new Profissional();
                                profissional.setId(usuario.getId());
                                profissional.setSenha(usuario.getSenha());
                                profissional.setNome(usuario.getNome());
                                profissional.setEmail(usuario.getEmail());
                                profissional.setPapel(usuario.getPapel());
                                // Set atributos específicos de Profissional, se houver
                                request.getSession().setAttribute("usuarioLogado", profissional);
                                response.sendRedirect("profissional/");
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
