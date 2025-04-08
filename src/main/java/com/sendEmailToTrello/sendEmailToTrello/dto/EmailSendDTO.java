package com.sendEmailToTrello.sendEmailToTrello.dto;

import java.util.List;

public record EmailSendDTO(
        List<String> destinatarios,
        String assunto,
        String conteudo
) {}

