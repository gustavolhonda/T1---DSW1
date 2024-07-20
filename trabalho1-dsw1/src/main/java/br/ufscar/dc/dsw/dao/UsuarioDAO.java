package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;

public class UsuarioDAO extends GenericDAO {

    public Usuario getbyEmail(String email) {
        Usuario usuario = null;
        String sql = "SELECT * FROM Usuario WHERE email = ?";

        try (Connection conn = this.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    usuario = createUsuarioFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public Usuario get(Integer id) {
        Usuario usuario = null;
        String sql = "SELECT * FROM Usuario WHERE id = ?";

        try (Connection conn = this.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    usuario = createUsuarioFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public List<Usuario> getAll() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";

        try (Connection conn = this.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery();) {

            while (rs.next()) {
                Usuario usuario = createUsuarioFromResultSet(rs);
                listaUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }

    public List<Usuario> getProfissional() {
        List<Usuario> listaProfissionais = new ArrayList<>();
        String sql = "SELECT * FROM Usuario WHERE papel = ?";

        try (Connection conn = this.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "PROFISSIONAL"); // Define o valor do parâmetro na consulta

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Usuario profissional = createUsuarioFromResultSet(rs);
                    listaProfissionais.add(profissional);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProfissionais;
    }

    public void insert(Usuario usuario) {
        String sql = "INSERT INTO Usuario (nome, email, senha, cpf, papel) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = this.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getPapel());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuario.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            if (usuario instanceof Cliente) {
                insertCliente((Cliente) usuario);
            } else if (usuario instanceof Profissional) {
                insertProfissional((Profissional) usuario);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente (id, telefone, sexo, dataNasc) VALUES (?, ?, ?, ?)";

        try (Connection conn = this.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setLong(1, cliente.getId());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getSexo());
            stmt.setString(4, cliente.getDataNasc());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertProfissional(Profissional profissional) {
        String sql = "INSERT INTO Profissional (id, especialidade) VALUES (?, ?)";

        try (Connection conn = this.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setLong(1, profissional.getId());
            stmt.setString(2, profissional.getEspecialidade());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Usuario usuario) {
        String sql = "UPDATE Usuario SET nome= ?, email = ?, senha = ?, cpf = ?, papel = ? WHERE id = ?";

        try (Connection conn = this.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getPapel());
            stmt.setLong(6, usuario.getId());
            stmt.executeUpdate();

            if (usuario instanceof Cliente) {
                updateCliente((Cliente) usuario);
            } else if (usuario instanceof Profissional) {
                updateProfissional((Profissional) usuario);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateCliente(Cliente cliente) {
        String sql = "UPDATE Cliente SET telefone = ?, sexo = ?, dataNasc = ? WHERE id = ?";

        try (Connection conn = this.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, cliente.getTelefone());
            stmt.setString(2, cliente.getSexo());
            stmt.setString(3, cliente.getDataNasc());
            stmt.setInt(4, cliente.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateProfissional(Profissional profissional) {
        String sql = "UPDATE Profissional SET especialidade = ? WHERE id = ?";

        try (Connection conn = this.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, profissional.getEspecialidade());
            stmt.setLong(2, profissional.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM Usuario WHERE id = ?";

        try (Connection conn = this.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Usuario createUsuarioFromResultSet(ResultSet rs) throws SQLException {
        String papel = rs.getString("papel");
        Usuario usuario;
        switch (papel) {
            case "CLIENTE":
                usuario = new Cliente();
                populateCliente((Cliente) usuario, rs.getInt("id"));
                break;
            case "PROFISSIONAL":
                usuario = new Profissional();
                populateProfissional((Profissional) usuario, rs.getInt("id"));
                break;
            default:
                usuario = new Usuario();
                break;
        }
        usuario.setId(rs.getInt("id"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setNome(rs.getString("nome"));
        usuario.setEmail(rs.getString("email"));
        usuario.setPapel(rs.getString("papel"));
        return usuario;
    }

    private void populateCliente(Cliente cliente, Integer id) {
        String sql = "SELECT * FROM Cliente WHERE id = ?";

        try (Connection conn = this.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    cliente.setTelefone(rs.getString("telefone"));
                    cliente.setSexo(rs.getString("sexo"));
                    cliente.setDataNasc(rs.getString("dataNascimento"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void populateProfissional(Profissional profissional, Integer id) {
        String sql = "SELECT * FROM Profissional WHERE id = ?";

        try (Connection conn = this.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    profissional.setEspecialidade(rs.getString("especialidade"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
