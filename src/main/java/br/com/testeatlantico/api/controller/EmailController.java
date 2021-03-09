package br.com.testeatlantico.api.controller;


import br.com.testeatlantico.api.config.JwtTokenUtil;
import br.com.testeatlantico.api.model.Email;
import br.com.testeatlantico.api.service.EmailService;
import br.com.testeatlantico.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/email")
@CrossOrigin
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Email> save(@RequestBody Email email,@RequestHeader("Authorization")String token){
        email.setSender(jwtTokenUtil.getUsernameFromToken(token.substring(7)));
        Email emailSaved = emailService.save(email);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin")
    public ResponseEntity<List<String>> getAdminEmails(){
        List<String> emails = userService.findUserAdminEmail();
        return ResponseEntity.ok().body(emails);
    }
}
