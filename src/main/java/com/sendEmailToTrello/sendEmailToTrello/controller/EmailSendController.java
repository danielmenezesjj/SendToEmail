package com.sendEmailToTrello.sendEmailToTrello.controller;


import com.sendEmailToTrello.sendEmailToTrello.dto.EmailSendDTO;
import com.sendEmailToTrello.sendEmailToTrello.services.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/email")
public class EmailSendController {


    @Autowired
    private SendEmailService service;

    @PostMapping("/send")
    public ResponseEntity sendEmail(@RequestBody EmailSendDTO dto){
        service.sendEmail(dto);
        service.executarComandosGit(dto);
        return ResponseEntity.ok("Email enviado, teste github/trello");
    }



}
