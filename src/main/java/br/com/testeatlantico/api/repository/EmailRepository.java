package br.com.testeatlantico.api.repository;

import br.com.testeatlantico.api.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email,Long> {

}
