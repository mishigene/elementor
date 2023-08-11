package io.elementor.infra.email;

import com.sun.mail.imap.protocol.FLAGS;

import javax.mail.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GmailService {
   public static List<Email> getMessages(){
       return getMessages(null);
   }
   public static List<Email> getMessages(String from){
       Properties props = new Properties();

       String host      = "imap.gmail.com";
       String username  = "assaf.balzamovich@crowdvocate.com";
       String password  = "CV_73$70m8";
       String provider  = "imaps";

       List<Email> results = new ArrayList<>();
       try
       {
           //Connect to the server
           props.setProperty("mail.store.protocol", "imaps");
           Session session = Session.getDefaultInstance(props, null);
           Store store     = session.getStore(provider);
           store.connect(host, username, password);

           //open the inbox folder
           Folder inbox = store.getFolder("INBOX");
           inbox.open(Folder.READ_WRITE);

           // get a list of javamail messages as an array of messages
           Message[] messages = inbox.getMessages();
           for (Message msg: messages){
               if(!msg.getFlags().contains(Flags.Flag.SEEN)){
                   Pattern pattern =  Pattern.compile("(.*)<(.*)>");
                   Address address = msg.getFrom()[0];
                   String curFrom = address.toString();
                   Matcher matcher = pattern.matcher(curFrom);
                   matcher.matches();
                   curFrom = matcher.group(2);
                   if(from == null || from.equals(curFrom)){
                       results.add(new Email(curFrom,msg.getContent().toString(), msg.getSentDate()));
                       msg.setFlag(FLAGS.Flag.SEEN, true);
                   }
               }
           }

           //close the inbox folder but do not
           //remove the messages from the server
           inbox.close(true);
           store.close();

       }
       catch (NoSuchProviderException nspe)
       {
           System.err.println("invalid provider name");
       }
       catch (MessagingException | IOException me)
       {
           System.err.println("messaging exception");
           me.printStackTrace();
       }
       return results;
   }

}

