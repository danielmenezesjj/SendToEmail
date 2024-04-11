package com.sendEmailToTrello.sendEmailToTrello.services;


import com.sendEmailToTrello.sendEmailToTrello.dto.EmailSendDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender emailSender;



    public void sendEmail(EmailSendDTO data) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(data.destinatario());
            helper.setSubject(data.assunto());
            helper.setText(data.conteudo());
            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void executarComandosGit(EmailSendDTO dto) {
        try {

            ProcessBuilder processBuilderAdd = new ProcessBuilder("git", "add", ".");
            Process processAdd = processBuilderAdd.start();
            processAdd.waitFor();


            ProcessBuilder processBuilderCommit = new ProcessBuilder("git", "commit", "-m", dto.conteudo());
            Process processCommit = processBuilderCommit.start();
            processCommit.waitFor();


            ProcessBuilder processBuilderPush = new ProcessBuilder("git", "push", "origin", "master");
            Process processPush = processBuilderPush.start();
            processPush.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
