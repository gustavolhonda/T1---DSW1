package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.ufscar.dc.dsw.domain.Agendamento;

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
}
