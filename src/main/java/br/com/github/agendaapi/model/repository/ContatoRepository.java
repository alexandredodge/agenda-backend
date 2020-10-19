package br.com.github.agendaapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.github.agendaapi.model.entity.Contato;
@Service
public interface ContatoRepository extends JpaRepository<Contato,Integer>{

}
