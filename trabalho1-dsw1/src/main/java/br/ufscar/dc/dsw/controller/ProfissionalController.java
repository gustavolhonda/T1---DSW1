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
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Agendamento;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/profissional/*")
public class ProfissionalController extends HttpServlet {

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

		Profissional profissional = (Profissional) request.getSession().getAttribute("usuarioLogado");
		Erro erros = new Erro();

		if (profissional == null) {
			response.sendRedirect(request.getContextPath());
			return;
		} else if (!profissional.getPapel().equals("profissional")) {
			erros.add("Acesso não autorizado!");
			erros.add("Apenas Papel [profissional] tem acesso a essa página");
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
								default:
										lista(request, response);
										break;
						}
				} catch (RuntimeException | IOException | ServletException e) {
						throw new ServletException(e);
				}
		}

		private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Profissional profissional = (Profissional) request.getSession().getAttribute("usuarioLogado");
				List<Agendamento> listaAgendamentos = dao.getAll(profissional);
				request.setAttribute("listaAgendamentos", listaAgendamentos);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/agendamento/lista.jsp");
				dispatcher.forward(request, response);
		}

		private Map<Integer, Agendamento> getAgendamentos() {
				Map<Integer, Agendamento> agendamento = new HashMap<>();
				for (Agendamento agendamento: new AgendamentoDAO().getAll()) {
						agendamentos.put(agendamento.getId(), agendamento);
				}
				return agendamentos;
		}


}