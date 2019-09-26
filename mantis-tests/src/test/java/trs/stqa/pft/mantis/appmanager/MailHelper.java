package trs.stqa.pft.mantis.appmanager;

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import trs.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

  public class MailHelper {
    private  ApplicationManager app;
    private  Wiser wiser;

    public MailHelper(ApplicationManager app) {
      this.app = app;
      wiser = new Wiser();
    }

    public void start() {
      wiser.start();
    }

    public void stop() {
      wiser.stop();
    }

    public List<MailMessage> waitForMail(int count, long timeout) {
      long start = System.currentTimeMillis();
      while (System.currentTimeMillis() < start + timeout) {
        if (wiser.getMessages().size() >= count) {
          return wiser.getMessages().stream().map((m) -> toModeMail(m)).collect(Collectors.toList());
        }
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      throw new Error("No mail:");
    }

    public static MailMessage toModeMail(WiserMessage m) {
      try {
        MimeMessage nm = m.getMimeMessage();
        return new MailMessage(nm.getAllRecipients()[0].toString(), (String) nm.getContent());
      } catch (MessagingException e) {
        e.printStackTrace();
        return null;
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      }
    }

  }


