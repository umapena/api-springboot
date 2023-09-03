package br.edu.senai.sc.web.springboot.repository;

import br.edu.senai.sc.web.springboot.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

}
