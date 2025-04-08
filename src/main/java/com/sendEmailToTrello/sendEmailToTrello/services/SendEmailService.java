package com.sendEmailToTrello.sendEmailToTrello.services;


import com.sendEmailToTrello.sendEmailToTrello.dto.EmailSendDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender emailSender;


    public void sendEmail(EmailSendDTO data) {
        List<String> all = data.destinatarios();
        int batchSize = 50;

        for (int i = 0; i < all.size(); i += batchSize) {
            List<String> batch = all.subList(i, Math.min(i + batchSize, all.size()));
            sendBatch(batch, data.assunto(), data.conteudo());

            try {
                Thread.sleep(2000); // Espera 2 segundos entre lotes (ajustável)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void sendBatch(List<String> destinatarios, String assunto, String conteudo) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(destinatarios.toArray(new String[0]));
            helper.setSubject(assunto);
            helper.setText(conteudo, true);

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


//    public void executarComandosGit(EmailSendDTO dto) {
//        try {
//
//            ProcessBuilder processBuilderAdd = new ProcessBuilder("git", "add", ".");
//            Process processAdd = processBuilderAdd.start();
//            processAdd.waitFor();
//
//
//            ProcessBuilder processBuilderCommit = new ProcessBuilder("git", "commit", "-m", dto.conteudo());
//            Process processCommit = processBuilderCommit.start();
//            processCommit.waitFor();
//
//
//            ProcessBuilder processBuilderPush = new ProcessBuilder("git", "push", "origin", "master");
//            Process processPush = processBuilderPush.start();
//            processPush.waitFor();
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

}
