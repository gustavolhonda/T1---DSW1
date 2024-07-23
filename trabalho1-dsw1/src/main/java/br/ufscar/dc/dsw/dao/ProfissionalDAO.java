package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;

public class ProfissionalDAO extends GenericDAO {

    public void insert(Profissional profissional) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Integer userID = usuarioDAO.insert(new Usuario(
            profissional.getNome(),
            profissional.getEmail(),
            profissional.getSenha(),
            profissional.getCpf(),
            "PROFISSIONAL"
        ));
    
        String sql = "INSERT INTO Profissionais (id_profissional, especialidade) VALUES (?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            
            statement.setInt(1, userID);
            statement.setString(2, profissional.getEspecialidade());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Profissional> getAll(String especialidade) {
        List<Profissional> listaProfissionais = new ArrayList<>();
    
        // Inicia a consulta SQL base
        String sql = "SELECT u.*, p.* " +
                     "FROM Usuario u " +
                     "JOIN Profissionais p ON u.id = p.id_profissional";
        
        // Adiciona cláusula WHERE se a especialidade for fornecida
        if (especialidade != null && !especialidade.isEmpty()) {
            sql += " WHERE p.especialidade = ?";
        }
    
        try {
            Connection conn = this.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
    
            // Define o valor do parâmetro se a especialidade for fornecida
            if (especialidade != null && !especialidade.isEmpty()) {
                preparedStatement.setString(1, especialidade);
            }
    
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cpf = resultSet.getString("cpf");
                String papel = resultSet.getString("papel");
                String especialidadeResult = resultSet.getString("especialidade");
    
                Profissional profissional = new Profissional(id, nome, email, senha, cpf, papel, especialidadeResult);
                listaProfissionais.add(profissional);
            }
    
            resultSet.close();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProfissionais;
    }
    
    public List<String> getEspecialidades() {
        List<String> listaEspecialidades = new ArrayList<>();
    
        // Inicia a consulta SQL base
        String sql = "SELECT DISTINCT especialidade FROM Profissionais";
    
        try {
            Connection conn = this.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()) {
                listaEspecialidades.add(resultSet.getString("especialidade"));
            }
    
            resultSet.close();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    
        return listaEspecialidades;
    }
    
    public void delete(Profissional profissional) {
        String sql = "DELETE FROM Profissionais where id_profissional = ?";

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
        String sql = "UPDATE Profissionais SET especialidade = ? WHERE id_profissional = ?";

        try (Connection conn = this.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, profissional.getEspecialidade());
            stmt.setLong(2, profissional.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Profissional get(Integer id) {
        Profissional profissional = null;

        String sql = "SELECT u.*, p.* " +
                     "FROM Usuario u " +
                     "JOIN Profissionais p ON u.id = ?";

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
                String papel = resultSet.getString("papel");

                profissional = new Profissional(id, nome, email, senha, cpf, papel, especialidade);
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
                String papel = resultSet.getString("papel");

                profissional = new Profissional(id,nome, email, senha, cpf, papel, especialidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }

    public Profissional getbycpf(String cpf) {
        Profissional profissional = null;

        String sql = "SELECT * from Profissionais l where l.cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                String nome = resultSet.getString("nome");
                String especialidade = resultSet.getString("especialidade");
                
                profissional = new Profissional(id, nome, email, senha, cpf, papel, especialidade);
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