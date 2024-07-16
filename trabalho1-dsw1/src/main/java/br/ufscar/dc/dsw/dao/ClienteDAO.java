package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Cliente;

public class ClienteDAO extends GenericDAO {

    public void insert(Cliente cliente) {

        String sql = "INSERT INTO Clientes (email, senha, cpf, nome, telefone, sexo, dataNasc); VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, cliente.getEmail());
            statement.setString(2, cliente.getSenha());
            statement.setString(3, cliente.getCpf());
            statement.setString(4, cliente.getNome());
            statement.setString(5, cliente.getTelefone());
            statement.setString(6, cliente.getSexo());
            statement.setString(5, cliente.getDataNasc());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cliente> getAll() {

        List<Cliente> listaClientes = new ArrayList<>();

        String sql = "SELECT * from Cliente c order by c.id";

        try (Connection conn = this.getConnection(); Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                String dataNasc = resultSet.getString("dataNasc");
                Cliente cliente = new Cliente(id, email, senha, cpf, nome, telefone, sexo, dataNasc);
                listaClientes.add(cliente);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaClientes;
    }

    public void delete(Cliente cliente) {
        String sql = "DELETE FROM Clientes where id = ?";

        try (Connection conn = this.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, cliente.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Cliente cliente) {
        String sql = "UPDATE Cliente SET email = ?, senha = ?, cpf = ?, nome = ?, telefone = ?, sexo = ?, dataNasc= ?";
        sql += "WHERE id = ?";

        try (Connection conn = this.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, cliente.getEmail());
            statement.setString(2, cliente.getSenha());
            statement.setString(3, cliente.getCpf());
            statement.setString(4, cliente.getNome());
            statement.setString(5, cliente.getTelefone());
            statement.setString(6, cliente.getSexo());
            statement.setString(7, cliente.getDataNasc());
            statement.setLong(8, cliente.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente get(Integer id) {
        Cliente cliente = null;

        String sql = "SELECT * from Clientes l where l.id = ?";

        try (Connection conn = this.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String email = resultSet.getString("email");
                    String senha = resultSet.getString("senha");
                    String cpf = resultSet.getString("cpf");
                    String nome = resultSet.getString("nome");
                    String telefone = resultSet.getString("telefone");
                    String sexo = resultSet.getString("sexo");
                    String dataNasc = resultSet.getString("dataNasc");

                    cliente = new Cliente(id, email, senha, cpf, nome, telefone, sexo, dataNasc);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }

    public Cliente getbyLogin(String login) {
        Cliente cliente = null;

        String sql = "SELECT * from Cliente WHERE login = ?";

        try (Connection conn = this.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String email = resultSet.getString("email");
                    String senha = resultSet.getString("senha");
                    String cpf = resultSet.getString("cpf");
                    String nome = resultSet.getString("nome");
                    String telefone = resultSet.getString("telefone");
                    String sexo = resultSet.getString("sexo");
                    String dataNasc = resultSet.getString("dataNasc");

                    cliente = new Cliente(email, senha, cpf, nome, telefone, sexo, dataNasc);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }

}
