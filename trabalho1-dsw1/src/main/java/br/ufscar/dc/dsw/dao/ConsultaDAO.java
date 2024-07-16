package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.ufscar.dc.dsw.domain.Consulta;

public class ConsultaDAO extends GenericDAO {

    public void insert(Consulta consulta) {

        String sql = "INSERT INTO Consultas (cliente_cpf, profissional_cpf, data_hora, link_videoconferencia) VALUES (?, ?, ?, ?)";

        try {
            try (Connection conn = this.getConnection()) {
                PreparedStatement statement = conn.prepareStatement(sql);
                
                statement = conn.prepareStatement(sql);
                statement.setString(1, consulta.getCpfCliente());
                statement.setString(2, consulta.getCpfProfissional());
                
                // Converter java.util.Date para java.sql.Date
                java.util.Date utilDate = consulta.getDataHora();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                statement.setDate(3, sqlDate);
                
                statement.setString(4, consulta.getLinkVideoconferencia());
                statement.executeUpdate();
                
                statement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
