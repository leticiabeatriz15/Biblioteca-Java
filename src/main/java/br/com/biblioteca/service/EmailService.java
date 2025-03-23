package br.com.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmail(String destinatario, String assunto){
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(destinatario); 

        email.setFrom("bibliotecaspring@gmail.com");

        if(assunto.equals("emprestimo")){
            email.setText("Seu empréstimo foi realizado com sucesso!");
        
        } else if(assunto.equals("devolucao")){
            email.setText("Seu livro foi devolvido com sucesso!");
        
        } else {
            email.setText("Operação realizada com sucesso!");
        
        }

        email.setSubject("Notificação Biblioteca"); 
        
        mailSender.send(email);
    }

}
