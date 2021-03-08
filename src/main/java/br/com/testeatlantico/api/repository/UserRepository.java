package br.com.testeatlantico.api.repository;


import br.com.testeatlantico.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByLogin(String login);

    @Query(value="select u from User u where u.admin = 1")
    List<User> findUserAdmin();
}
