package br.com.github.agendaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



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
