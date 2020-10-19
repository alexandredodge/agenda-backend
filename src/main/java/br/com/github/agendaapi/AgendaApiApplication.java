package br.com.github.agendaapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.github.agendaapi.model.entity.Contato;
import br.com.github.agendaapi.model.repository.ContatoRepository;



@SpringBootApplication
public class AgendaApiApplication {

//	@Bean
//	CommandLineRunner commandLineRunner(@Autowired ContatoRepository repository) {
//		return args ->{
//			Contato contato = new Contato();
//			contato.setNome("Alexandre novo");
//			contato.setEmail("alexandre@email.com");
//			contato.setFavorito(true);
//			repository.save(contato);
//		};
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(AgendaApiApplication.class, args);
	}

}
