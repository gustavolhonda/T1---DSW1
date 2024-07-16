package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Profissional;

public class ProfissionalDAO extends GenericDAO {

    public void insert(Profissional profissional) {

        String sql = "INSERT INTO Profissionais (email, senha, cpf, nome, especialidade) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement = conn.prepareStatement(sql);
            statement.setString(1, profissional.getEmail());
            statement.setString(2, profissional.getSenha());
            statement.setString(3, profissional.getCpf());
            statement.setString(4, profissional.getNome());
            statement.setString(5, profissional.getEspecialidade());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Profissional> getAll() {

        List<Profissional> listaProfissionais = new ArrayList<>();

        String sql = "SELECT * from Profissionais u";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String especialidade = resultSet.getString("especialidade");
                Profissional profissional = new Profissional(id, email, senha, cpf, nome, especialidade);
                listaProfissionais.add(profissional);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProfissionais;
    }

    public void delete(Profissional profissional) {
        String sql = "DELETE FROM Profissionais where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, profissional.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    public void update(Profissional profissional) {
        String sql = "UPDATE Profissionais SET email = ?, senha = ?, cpf = ?, nome = ?, especialidade = ?, WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getEmail());
            statement.setString(2, profissional.getSenha());
            statement.setString(3, profissional.getCpf());
            statement.setString(4, profissional.getNome());
            statement.setString(5, profissional.getEspecialidade());
            statement.setInt(6, profissional.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Profissional get(Integer id) {
        Profissional profissional = null;

        String sql = "SELECT * from Profissionais WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String especialidade = resultSet.getString("especialidade");

                profissional = new Profissional(id, email, senha, cpf, nome, especialidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }
    
    public Profissional getbyEmail(String email) {
        Profissional profissional = null;

        String sql = "SELECT * from Profissionais WHERE email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	Integer id = resultSet.getInt("id");
                String senha = resultSet.getString("senha");
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String especialidade = resultSet.getString("especialidade");

                profissional = new Profissional(id, email, senha, cpf, nome, especialidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }
    
}