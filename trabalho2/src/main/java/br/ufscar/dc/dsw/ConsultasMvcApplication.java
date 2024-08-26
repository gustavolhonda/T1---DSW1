package br.ufscar.dc.dsw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.IAgendamentoDAO;
import br.ufscar.dc.dsw.dao.IClienteDAO;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Agendamento;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;

@SpringBootApplication
public class ConsultasMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultasMvcApplication.class, args);
	}

	@Bean //arrumar isso
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder, IAgendamentoDAO agendamentoDAO, IClienteDAO livroDAO) {
		return (args) -> {
			
			Usuario u1 = new Usuario();
			u1.setUsername("admin@gmai.com");
			u1.setPassword(encoder.encode("admin"));
			u1.setCPF("012.345.678-90");
			u1.setName("Administrador");
			u1.setRole("ROLE_ADMIN");
			u1.setEnabled(true);
			usuarioDAO.save(u1);
			
			Cliente u2 = new Cliente();
			u2.setUsername("beltrano@gmail.com");
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
			u3.setUsername("fulano@yahoo.com");
			u3.setPassword(encoder.encode("123"));
			u3.setCPF("367.318.380-04");
			u3.setName("Fulano Silva");
			u3.setRole("ROLE_PROFESSIONAL");
			u3.setEspecialidade("Ortopedia");

			u3.setEnabled(true);
			usuarioDAO.save(u3);

			Profissional u4 = new Profissional();
			u4.setUsername("joaodasilva@gmail.com");
			u4.setPassword(encoder.encode("456"));
			u4.setCPF("789.456.123-00");
			u4.setName("João da Silva");
			u4.setRole("ROLE_PROFESSIONAL");
			u4.setEspecialidade("Advocacia");
			u4.setEnabled(true);
			usuarioDAO.save(u4);

			Profissional u5 = new Profissional();
			u5.setUsername("mariaribeiro@gmail.com");
			u5.setPassword(encoder.encode("789"));
			u5.setCPF("123.789.456-00");
			u5.setName("Maria Ribeiro");
			u5.setRole("ROLE_PROFESSIONAL");
			u5.setEspecialidade("Engenharia Civil");
			u5.setEnabled(true);
			usuarioDAO.save(u5);

			Profissional u6 = new Profissional();
			u6.setUsername("carlosalmeida@hotmail.com");
			u6.setPassword(encoder.encode("101112"));
			u6.setCPF("654.321.987-00");
			u6.setName("Carlos Almeida");
			u6.setRole("ROLE_PROFESSIONAL");
			u6.setEspecialidade("Cardiologia");
			u6.setEnabled(true);
			usuarioDAO.save(u6);

			Profissional u7 = new Profissional();
			u7.setUsername("anacarvalho@terra.com");
			u7.setPassword(encoder.encode("131415"));
			u7.setCPF("987.654.321-00");
			u7.setName("Ana Carvalho");
			u7.setRole("ROLE_PROFESSIONAL");
			u7.setEspecialidade("Psicologia");
			u7.setEnabled(true);
			usuarioDAO.save(u7);

			Profissional u8 = new Profissional();
			u8.setUsername("pedroramos@icloud.com");
			u8.setPassword(encoder.encode("161718"));
			u8.setCPF("321.654.987-00");
			u8.setName("Pedro Ramos");
			u8.setRole("ROLE_PROFESSIONAL");
			u8.setEspecialidade("Odontologia");
			u8.setEnabled(true);
			usuarioDAO.save(u8);


			Agendamento a2 = new Agendamento();
			a2.setCliente(u2);
			a2.setProfissional(u3);
			a2.setDataHora("10-05-2025 13:00");
			a2.setLinkVideoConferencia("Tome link");
			agendamentoDAO.save(a2);
		};
	}
}
