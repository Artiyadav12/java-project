package com.search.data.util;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Component
public class MailUtil {

private static final Logger logger= LoggerFactory.getLogger(MailUtil.class);



@Autowired
    Mailer mailer;

private String mode="sandbox";

    public void sendOTPMail(String mailContent, String subject, String mailReciever,String mailSender) {

        Email email = null;
        try {
            email = EmailBuilder.startingBlank()
                    .from(new InternetAddress(mailSender)).to(mailReciever).withPlainText(mailContent).withSubject(subject).buildEmail();
        } catch (AddressException e) {
            logger.error("Exception occured while sending mail ---",e);
            throw new RuntimeException(e);
        }

        mailer.sendMail(email, true);
    }

    public void sendMail(String mailContent, String subject) throws  MessagingException {

        Email email = EmailBuilder.startingBlank()
                .from(new InternetAddress(mode == "sandbox" ? "km.arti@moglix.com" : "care@moglix.com"))
                .toMultiple(addRecipients()).withPlainText(mailContent).withSubject(subject).buildEmail();
        mailer.sendMail(email, true);
    }

    private InternetAddress[] addRecipients() throws MessagingException {

        logger.debug("Inside email()");

        List<InternetAddress> recipients = new ArrayList<>();

        if (mode.equalsIgnoreCase("sandbox")) {

            recipients.add(new InternetAddress("arunansh.singh@moglix.com"));
          //  recipients.add(new InternetAddress("arti07411@gmail.com"));

        } else if (mode.equalsIgnoreCase("live")) {

            List<String> toMailByMap = new ArrayList<>();
            toMailByMap.add("km.arti@moglix.com");

            if (!CollectionUtils.isEmpty(toMailByMap)) {
                for(String address: toMailByMap) {
                    recipients.add(new InternetAddress(address));
                }
            } else {
                recipients.add(new InternetAddress("care@moglix.com"));
            }
        }
        return recipients.toArray(new InternetAddress [recipients.size()]);
    }
}
