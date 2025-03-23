package br.com.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.biblioteca.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @GetMapping("/enviar")
    public String enviarEmail(@RequestParam String destinatario, @RequestParam String assunto){
        emailService.enviarEmail(destinatario, assunto);
        return "Email enviado com sucesso!\nPara: " + destinatario;
    }
    
}
