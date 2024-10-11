package com.find_jobs.notification_service.service;

import com.find_jobs.shared_module.dto.request.NotificationPayloadRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @KafkaListener(topics = "${spring.kafka.topic.notification}", groupId = "notification-group")
    public void listen(NotificationPayloadRequestDTO payload) {
        System.out.println("Received message: " + payload);
        sendEmailNotification(payload);
    }

    public void sendEmailNotification(NotificationPayloadRequestDTO payload) {

        System.out.println("Sending email to: " + payload.getEmail());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(payload.getEmail());
        message.setSubject(payload.getSubject());
        message.setText(payload.getMessage());
        mailSender.send(message);
    }
}
