package br.com.testeatlantico.api.service;

import br.com.testeatlantico.api.config.EmailAMQPConfig;
import br.com.testeatlantico.api.model.Email;
import br.com.testeatlantico.api.repository.EmailRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Email save(Email email){
        Email emailSaved = emailRepository.save(email);
        sendEmailToRabbit(email);

        List<String> admins = userService.findUserAdminEmail();
        admins.forEach(admin->{
            email.setDestination(admin);
            sendEmailToRabbit(email);
        });


        return emailSaved;
    }

    private void sendEmailToRabbit(Email email){
        try{
            String json = new ObjectMapper().writeValueAsString(email);
            rabbitTemplate.convertAndSend(EmailAMQPConfig.EXCHANGE_NAME,"",json);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }
}
