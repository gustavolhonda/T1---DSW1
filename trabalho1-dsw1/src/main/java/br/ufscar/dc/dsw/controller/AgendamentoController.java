package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.AgendamentoDAO;
import br.ufscar.dc.dsw.domain.Agendamento;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;



@WebServlet(urlPatterns = "/agendamentos/*")
public class AgendamentoController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private AgendamentoDAO dao;

  @Override
    public void init() {
        dao = new AgendamentoDAO();
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
      response.sendRedirect(request.getContextPath());
      return;
    } else if ((!usuario.getPapel().equals("CLIENTE")) && (!usuario.getPapel().equals("PROFISSIONAL"))) {
        erros.add("Acesso não autorizado!");
        erros.add("Apenas Papel [CLIENTE] e [PROFISSIONAL] tem acesso a essa página");
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
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
  }  

  private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
      List<Agendamento> listaAgendamentos = dao.getAll(usuario);
      request.setAttribute("listaAgendamentos", listaAgendamentos);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/agendamento/lista.jsp");
      dispatcher.forward(request, response);
  }
/* 
  private Map<Long, Livro> getLivros() {
      Map<Long, Livro> livros = new HashMap<>();
      for (Livro livro: new LivroDAO().getAll()) {
          livros.put(livro.getId(), livro);
      }
      return livros;
  }
*/

  private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException { 
            Usuario usuario =  (Usuario) request.getSession().getAttribute("usuarioLogado");
            
            
            request.setAttribute("id_profissional", request.getParameter("id"));
            

            RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/agendamento/formulario.jsp");
            dispatcher.forward(request, response);
  }

  private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      
      Usuario usuario =  (Usuario) request.getSession().getAttribute("usuarioLogado");
      Integer id_profissional = Integer.parseInt(request.getParameter("id"));
      String data = request.getParameter("data");
      String hora = request.getParameter("hora");
      
      Agendamento agendamento = new Agendamento(usuario.getId(), id_profissional, data, hora);
      dao.insert(agendamento);
      
      response.sendRedirect("lista");
  }

}