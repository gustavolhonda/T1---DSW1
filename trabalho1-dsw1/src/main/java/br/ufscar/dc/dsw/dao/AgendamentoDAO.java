package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import br.ufscar.dc.dsw.domain.*;

public class AgendamentoDAO extends GenericDAO {

    public void insert(Agendamento agendamento) {

        String sql = "INSERT INTO Agendamento (cliente_cpf, profissional_cpf, data_hora, link_videoconferencia) VALUES (?, ?, ?, ?)";

        try {
            try (Connection conn = this.getConnection()) {
                PreparedStatement statement = conn.prepareStatement(sql);
                
                statement = conn.prepareStatement(sql);
                statement.setString(1, agendamento.getCpfCliente());
                statement.setString(2, agendamento.getCpfProfissional());
                
                // Converter java.util.Date para java.sql.Date
                java.util.Date utilDate = agendamento.getDataHora();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                statement.setDate(3, sqlDate);
                
                statement.setString(4, agendamento.getLinkVideoconferencia());
                statement.executeUpdate();
                
                statement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Agendamento> getAllCliente(Usuario usuario) {

        List<Agendamento> listaAgendamentos = new ArrayList<>();

        String sql = "SELECT * from Agendamento a where a.CPF_CLIENTE = ? order by a.ID";

        try {
        	Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, usuario.getCpf());
            ResultSet resultSet = statement.executeQuery(); 
            
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String cpfCliente = resultSet.getString("cpf_cliente");
                String cpfProfissional = resultSet.getString("cpf_profissional");
                String linkVideo = resultSet.getString("link_videoconferencia");
                Date dataHora = resultSet.getDate("data_hora");
                //Cliente cliente = new ClienteDAO().getbycpf(cpfCliente);
                //Profissional profissional = new ProfissionalDAO().getbycpf(cpfProfissional);

                Agendamento agendamento = new Agendamento(cpfCliente, cpfProfissional,dataHora, linkVideo, id);         
                listaAgendamentos.add(agendamento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAgendamentos;
    }

    public List<Agendamento> getAllProfissional(Usuario usuario) {

        List<Agendamento> listaAgendamentos = new ArrayList<>();

        String sql = "SELECT * from Agendamento a where a.CPF_PROFISSIONAL = ? order by a.ID";

        try {
        	Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, usuario.getCpf());
            ResultSet resultSet = statement.executeQuery(); 
            
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String cpfCliente = resultSet.getString("cpf_cliente");
                String cpfProfissional = resultSet.getString("cpf_profissional");
                String linkVideo = resultSet.getString("link_videoconferencia");
                Date dataHora = resultSet.getDate("data_hora");
                //Cliente cliente = new ClienteDAO().getbycpf(cpfCliente);
                //Profissional profissional = new ProfissionalDAO().getbycpf(cpfProfissional);

                Agendamento agendamento = new Agendamento(cpfCliente, cpfProfissional,dataHora, linkVideo, id);         
                listaAgendamentos.add(agendamento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAgendamentos;
    }
}


