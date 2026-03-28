package com.example.promdetal_backend.service;

import com.example.promdetal_backend.dto.ContactFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final JavaMailSender mailSender;

    private final String adminEmail = "alinaens2005@gmail.com"; // куда будут приходить письма
    private final String fromEmail = "promdetal_site@mail.ru";  // твой Mail.ru для SMTP

    public void sendContactForm(ContactFormDto dto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);        // <- обязательно
        message.setTo(adminEmail);
        message.setReplyTo(fromEmail);// <- пользователь сможет ответить на его email
        message.setSubject("Новая заявка с сайта");
        message.setText(
                "Имя: " + dto.getName() + "\n" +
                        "Контакт: " + dto.getContact() + "\n" +
                        "Сообщение: " + dto.getMessage()
        );

        mailSender.send(message);
    }
}
