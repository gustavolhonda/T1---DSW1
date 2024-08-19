package br.ufscar.dc.dsw;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.IProfissionalDAO;
import br.ufscar.dc.dsw.dao.IClienteDAO;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Usuario;

@SpringBootApplication
public class ConsultasMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultasMvcApplication.class, args);
	}

	@Bean //arrumar isso
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder, IProfissionalDAO editoraDAO, IClienteDAO livroDAO) {
		return (args) -> {
			
			Usuario u1 = new Usuario();
			u1.setUsername("admin");
			u1.setPassword(encoder.encode("admin"));
			u1.setCPF("012.345.678-90");
			u1.setName("Administrador");
			u1.setRole("ROLE_ADMIN");
			u1.setEnabled(true);
			usuarioDAO.save(u1);
			
			Cliente u2 = new Cliente();
			u2.setUsername("beltrano");
			u2.setPassword(encoder.encode("123"));
			u2.setCPF("985.849.614-10");
			u2.setName("Beltrano Andrade");
			u2.setRole("ROLE_CLIENT");
			u2.setTelefone("17 99999-9999");
			u2.setSexo("M");
			u2.setDataNasc("1999-01-01");
			u2.setEnabled(true);
			usuarioDAO.save(u2);
			
			Profissional u3 = new Profissional();
			u3.setUsername("fulano");
			u3.setPassword(encoder.encode("123"));
			u3.setCPF("367.318.380-04");
			u3.setName("Fulano Silva");
			u3.setRole("ROLE_PROFESSIONAL");
			u3.setEspecialidade("otario");

			u3.setEnabled(true);
			usuarioDAO.save(u3);
		};
	}
}
