package com.alkemy.icons.email;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    @Value("${alkemy.icons.email.sender}")
    private String sender;

    @Value("${alkemy.icons.email.enabled}")
    private boolean enabled;

    public void sendEmail(String toAddress) throws IOException {
        if(!enabled) {
            return;
        }
        Email from = new Email(sender);
        String subject = "Welcome to Icons!";
        Email to = new Email(toAddress);
        Content content = new Content("text/plain", "Welcome to Icons! Enjoy your stay!");
        Mail mail = new Mail(from, subject, to, content);

        String apiKey = System.getenv("SENDGRID_API_KEY");

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}
