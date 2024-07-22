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
import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Agendamento;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.mail.internet.InternetAddress;

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

  private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException { 
            
            Integer id_profissional =  Integer.parseInt(request.getParameter("id"));
            request.setAttribute("id_profissional", id_profissional);
            
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
            request.setAttribute("id_usuario", usuario.getId());

            RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/agendamento/formulario.jsp");
            dispatcher.forward(request, response);
  }

  private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      
      Integer id_cliente=  Integer.parseInt(request.getParameter("id_usuario"));
      Integer id_profissional = Integer.parseInt(request.getParameter("id_profissional"));
      String data_hora = request.getParameter("data_hora");

      data_hora = data_hora.replace('T', ' ');

      Agendamento agendamento = new Agendamento(id_cliente, id_profissional, data_hora);
      dao.insert(agendamento);

      EmailService service = new EmailService();

      ClienteDAO cliDAO = new ClienteDAO();
      ProfissionalDAO profDAO = new ProfissionalDAO();
      Cliente cli = cliDAO.get(id_cliente);
      Profissional prof = profDAO.get(id_profissional);

      String cliEmail = cli.getEmail();
      String profEmail = prof.getEmail();

      InternetAddress from = new InternetAddress("testedswt@gmail.com", "Fulano");
      InternetAddress to1 = new InternetAddress(cliEmail, "Beltrano");
      InternetAddress to2 = new InternetAddress(profEmail, "Beltrano");
          
      String subject1 = "link consulta online";

      String body1 = agendamento.getLinkVideoconferencia();
    
      // Envio sem anexo
      service.send(from, to1, subject1, body1);
      service.send(from, to2, subject1, body1);
      
      response.sendRedirect("lista");
  }

}