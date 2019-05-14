package cafe.jjdev.mall.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import cafe.jjdev.mall.vo.EmailInfo;

@Component
public class EmailServicelmpl{
    @Autowired public JavaMailSender emailSender;
   
    public void sendSimpleMessage(EmailInfo emailInfo) {
    	
    		System.out.println("[cafe.jjdev.mall.email.EmailServicelmpl.sendSimpleMessage] emailInfo"+emailInfo);
    		SimpleMailMessage message = new SimpleMailMessage();
        	message.setTo(emailInfo.getTo());
        	message.setSubject(emailInfo.getSubject());
        	message.setText(emailInfo.getText());
        	System.out.println("---------------이메일 발송-----------");
        	emailSender.send(message);
        	System.out.println("---------------이메일 발송완료-----------");
    }
}
