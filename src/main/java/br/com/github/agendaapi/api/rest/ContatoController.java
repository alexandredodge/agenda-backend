package br.com.github.agendaapi.api.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public Page<Contato> list(
				@RequestParam(value = "page", defaultValue = "0") Integer pagina, 
				@RequestParam(value = "size", defaultValue = "10") Integer tamanhoPagina)
	{
		PageRequest pageRequest = PageRequest.of(pagina,tamanhoPagina);
		return repository.findAll(pageRequest);
	}
	
	//DEFINIÇÃO DO METODO:
	//@PutMapping VERBO UTILIZADO PARA FAZER UMA ATUALIZAÇÃO PARCIAL NA ENTIDADE.QUANDO VOU 
	//FAZER ATUALIZAÇÃO TOTAL UTILIZO O @PUT MAS NESSE CASO SOMENTE UMA PROPRIEDADE SERÁ ATUALIZADA
	// O /favorito INDICA QUAL A PROPRIEDADE EU QUERO ATUALIZAR E O ID É O IDENTIFICADOR QUE CHEGA P MIM QUE QUERO ATUALIZAR
	// @PathVariable Integer id RECEBERA O VALOR DO ID QUE RECEBI PELA URL
	// @RequestBody Boolean favorito 
	// Optional<Contato> contato = repository.findById(id) RETORNA UM OPTIONAL, PORQUE PODE SER RETORNADO UMA ENTIDADE COM O ID PASSADO OU NÃO
	// ifPresent METODO DA CLASSE OPTION QUE IRA EXECUTAR UMA FUNÇÃO CASO O (CONTATO) RETORNAR ALGUMA COISA, SE FOR RETORNADO ALGUMA COISA ELE SETA O FAVORITO 
	
	@PatchMapping("{id}/favorito")
	public void favorite(@PathVariable Integer id) {
		Optional<Contato> contato = repository.findById(id);
		contato.ifPresent( co ->{
			boolean favorito = co.getFavorito() == Boolean.TRUE;
			co.setFavorito(!favorito);
			repository.save(co);
		} );
	}
	
	@PutMapping("{id}/foto")
	public byte[] addPhoto(@PathVariable Integer id,@RequestParam("foto") Part arquivo ) {
		Optional<Contato> contato = repository.findById(id);
		return contato.map( c ->{
			try {
				InputStream is = arquivo.getInputStream();
				byte[] bytes = new byte[(int) arquivo.getSize() ];
				IOUtils.readFully(is,bytes);
				c.setFoto(bytes);
				repository.save(c);
				is.close();
				return bytes;
			}catch (IOException e) {
				return null;
			}
		}).orElse(null);
	}
	
}
