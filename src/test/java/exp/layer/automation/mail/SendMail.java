package exp.layer.automation.mail;

import er.automation.engine.helpers.EmailUtils;

import javax.mail.internet.AddressException;
import java.io.IOException;

public class SendMail {

    public static void main(String[] args) throws IOException, AddressException {
        EmailUtils.sendEmailWithDetails();

    }
}
