package br.com.testeatlantico.api.repository;

import br.com.testeatlantico.api.model.Email;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailRepository {
    private List<Email> emails;

    public EmailRepository(){
        this.emails = new ArrayList<>();
    }

    public Email save(Email email){
        this.emails.add(email);
        return email;
    }
}
