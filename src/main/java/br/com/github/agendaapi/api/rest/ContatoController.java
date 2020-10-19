package br.com.github.agendaapi.api.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.github.agendaapi.model.entity.Contato;
import br.com.github.agendaapi.model.repository.ContatoRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/contatos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ContatoController {

	private final ContatoRepository repository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Contato save(@RequestBody Contato contato) {
		return repository.save(contato);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete( @PathVariable Integer id ) {
		repository.deleteById(id);
	}
	
	@GetMapping
	public List<Contato> list(){
		return repository.findAll();
	}
	
	//DEFINIÇÃO DO METODO:
	//@PutMapping VERBO UTILIZADO PARA FAZER UMA ATUALIZAÇÃO PARCIAL NA ENTIDADE.QUANDO VOU 
	//FAZER ATUALIZAÇÃO TOTAL UTILIZO O @PUT MAS NESSE CASO SOMENTE UMA PROPRIEDADE SERÁ ATUALIZADA
	// O /favorito INDICA QUAL A PROPRIEDADE EU QUERO ATUALIZAR E O ID É O IDENTIFICADOR QUE CHEGA P MIM QUE QUERO ATUALIZAR
	// @PathVariable Integer id RECEBERA O VALOR DO ID QUE RECEBI PELA URL
	// @RequestBody Boolean favorito 
	// Optional<Contato> contato = repository.findById(id) RETORNA UM OPTIONAL, PORQUE PODE SER RETORNADO UMA ENTIDADE COM O ID PASSADO OU NÃO
	// ifPresent METODO DA CLASSE OPTION QUE IRA EXECUTAR UMA FUNÇÃO CASO O (CONTATO) RETORNAR ALGUMA COISA, SE FOR RETORNADO ALGUMA COISA ELE SETA O FAVORITO 
	@PutMapping("{id}/favorito")
	public void favorite(@PathVariable Integer id, @RequestBody Boolean favorito) {
		Optional<Contato> contato = repository.findById(id);
		contato.ifPresent( co ->{
			co.setFavorito(favorito);
			repository.save(co);
		} );
	}
	
}
