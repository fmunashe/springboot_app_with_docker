package com.example.experiment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Service
public class EmailService {

    private Long id;
    private String message;
    private String destination;
    private String source;

    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private SupplierService supplierService;
    private String subject;

    public EmailService() {
    }

    public EmailService(Long id, String message, String destination, String source) {
        this.id = id;
        this.message = message;
        this.destination = destination;
        this.source = source;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void sendEmail() throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.addAttachment("logo.png", new ClassPathResource("pronge.png"));
        Context context = new Context();
        context.setVariable("companies", companyService.companies());
        context.setVariable("suppliers",supplierService.listSuppliers());
        String html = templateEngine.process("emailTemplate", context);
        helper.setTo(getDestination());
        helper.setText(html, true);
        helper.setSubject(getSubject());
        helper.setFrom(getSource());
        javaMailSender.send(message);

    }
}
