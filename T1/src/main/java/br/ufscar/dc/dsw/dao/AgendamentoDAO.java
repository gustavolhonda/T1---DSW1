package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import br.ufscar.dc.dsw.domain.*;

public class AgendamentoDAO extends GenericDAO {

    public void insert(Agendamento agendamento) {

        String sql = "INSERT INTO Agendamento (id_cliente, id_profissional, data_hora, link_videoconferencia) VALUES (?, ?, ?, ?)";
            
        try {
            try (Connection conn = this.getConnection()) {
                PreparedStatement statement = conn.prepareStatement(sql);
                
                statement = conn.prepareStatement(sql);
                statement.setInt(1, agendamento.getId_cliente());
                statement.setInt(2, agendamento.getId_profissional());
                
                statement.setString(3, agendamento.getData());
                
                statement.setString(4, agendamento.getLinkVideoconferencia());
                statement.executeUpdate();
                
                statement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Agendamento> getAll(Usuario usuario) {

        List<Agendamento> listaAgendamentos = new ArrayList<>();

        String sql = "SELECT Agendamento.*, " +
                     "Cliente.nome AS cliente_nome, Cliente.email AS cliente_email, Cliente.cpf AS cliente_cpf, " +
                     "Profissional.nome AS profissional_nome, Profissional.email AS profissional_email, Profissional.cpf AS profissional_cpf " +
                     "FROM Agendamento " +
                     "LEFT JOIN Clientes ON Agendamento.id_cliente = Clientes.id_cliente " +
                     "LEFT JOIN Usuario AS Cliente ON Clientes.id_cliente = Cliente.id " +
                     "LEFT JOIN Profissionais ON Agendamento.id_profissional = Profissionais.id_profissional " +
                     "LEFT JOIN Usuario AS Profissional ON Profissionais.id_profissional = Profissional.id " +
                     "WHERE Agendamento.id_cliente = ? OR Agendamento.id_profissional = ?";

        try {
        	Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setInt(1, usuario.getId());
            statement.setInt(2, usuario.getId());
            
            ResultSet resultSet = statement.executeQuery(); 
            
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer id_cliente = Integer.parseInt(resultSet.getString("id_cliente"));
                Integer id_profissional = Integer.parseInt(resultSet.getString("id_profissional"));
                String linkVideo = resultSet.getString("link_videoconferencia");
                
                String data_hora = resultSet.getString("data_hora");

                Agendamento agendamento = new Agendamento(id_cliente, id_profissional,data_hora, linkVideo, id);         
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
/* 
    public boolean verifica(Agendamento agendamento) {
        
        String sql = "select * from Agendamento where data_hora = ?";
            
        try {
        	Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, agendamento.getId());
            
            ResultSet resultSet = statement.executeQuery(); 
            
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer id_cliente = Integer.parseInt(resultSet.getString("id_cliente"));
                Integer id_profissional = Integer.parseInt(resultSet.getString("id_profissional"));
                String linkVideo = resultSet.getString("link_videoconferencia");
                
                String data_hora = resultSet.getString("data_hora");

                Agendamento agendamento = new Agendamento(id_cliente, id_profissional,data_hora, linkVideo, id);         
                listaAgendamentos.add(agendamento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    } */
}


