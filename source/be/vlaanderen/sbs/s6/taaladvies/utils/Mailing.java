package be.vlaanderen.sbs.s6.taaladvies.utils;

import java.io.File;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
//import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.InitialContext;
import be.vlaanderen.sbs.s6.framework.logging.AppLogger;

/**
 * Insert the type's description here.
 * Creation date: (9/3/2001 2:49:35 PM)
 * @author: Administrator
 */
public class Mailing
{
	//private Properties props = System.getProperties();
	//private Session session;
	//private Message message;
	private BodyPart messageBodyPart;
	private Multipart multipart = new MimeMultipart();

	// Various email settings

	private String from;
	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private String body;
	private String smtpserver;
	private String contentType;
	private File[] attachments;

	/**
	 * Mailing constructor comment.
	 */
	public Mailing()
	{
		super();
	}
	/**
	 * Attach a specified file to the current message
	 * @param fileName The name of the file that will be attached to the message
	 */

	public void attachFile() throws MessagingException
	{
		for (int i = 0, j = attachments.length; i < j; i++) {
			File file = (File)attachments[i];

			DataSource source = new FileDataSource(file.getAbsolutePath());

			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(file.getName());

			multipart.addBodyPart(messageBodyPart);
		}
	}
	/**
	 * Return the list of addresses that will be added in the BCC part of the message
	 */

	public java.lang.String getBcc()
	{
		return bcc;
	}
	/**
	 * Return the data that will be added as the BODY of the current message
	 */
	public java.lang.String getBody()
	{
		return body;
	}
	/**
	 * Return the list of addresses that will be added in the CC part of the message
	 */

	public java.lang.String getCc()
	{
		return cc;
	}
	/**
	 * Return the address that will be added in the FROM part of the message
	 */

	public java.lang.String getFrom()
	{
		return from;
	}
	/**
	 * Return the mailserver that will be used for sending the message
	 */

	public java.lang.String getSmtpserver()
	{
		return smtpserver;
	}
	/**
	 * Return the string that will be added in the SUBJECT part of the message
	 */

	public java.lang.String getSubject()
	{
		return subject;
	}
	/**
	 * Return the address that will be added in the TO part of the message
	 */

	public java.lang.String getTo()
	{
		return to;
	}

	/**
	 * Mailt de oproep naar de afzender door gebruik te maken van de appserver's mail session via de mail jndi-naam
	 * @throws Exception gooit een exceptie als het mailen mislukt
	 */
	public void sendMail() throws Exception {
        try {
            Context initial = new InitialContext();
            /**
             * javamail session properties voor in Sun:
             * jndi name:mail/Taaladvies
             * mail host:imap.vlaanderen.be
             * default user:xyz@foo.com
             * default return address:nobody
             */

            Object o = initial.lookup("java:comp/env/mail/Taaladvies");
            javax.mail.Session session = ((javax.mail.Session) o);


            Message msg = new MimeMessage(session);

            //Use a tokenizer to parse the to/cc/bcc strings
			//They might contain one or more email addresses
            //First replace ";" by "," because new API uses "," as internetaddresses separator
            if (from != null) {
                msg.setFrom(new InternetAddress(from));
            }
            else {
                msg.setFrom();

            }

            if(to != null)
            msg.setRecipients(Message.RecipientType.TO,
                              InternetAddress.parse(to.replaceAll(";", ","), false));

            //Possbility for CC email
            if(cc != null && !cc.equals(""))
            msg.setRecipients(Message.RecipientType.CC,
                              InternetAddress.parse(cc.replaceAll(";", ","), false));

            //Possbility for BCC email
            if(bcc != null && !bcc.equals(""))
            msg.setRecipients(Message.RecipientType.BCC,
                              InternetAddress.parse(bcc.replaceAll(";", ","), false));

            if(subject != null)
            msg.setSubject(subject);


            //Add email-body
			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(body, contentType);
			multipart.addBodyPart(messageBodyPart);
			if (attachments != null) {
				if (attachments.length > 0) {
					this.attachFile();
				}
			}
			msg.setContent(multipart);

			Transport.send(msg);
			if (attachments != null) {
				for (int i = 0, j = attachments.length; i < j; i++) {
					attachments[i].delete();
				}
			}
        }
        catch (Exception ex) {
            ex.printStackTrace(System.err);
            AppLogger.getInstance().error(ex.getMessage());
			throw new Exception("Mailing:sendMail Exception : " + ex.toString());
        }
    }


	/**
	 * Set the list of addresses that will be added in the BCC part of the message
	 */

	public void setBcc(java.lang.String newBcc)
	{
		bcc = newBcc;
	}
	/**
	 * Set the BODY-part of the message to a specified string
	 */
	public void setBody(java.lang.String newBody)
	{
		body = newBody;
	}
	/**
	 * Set the list of addresses that will be added in the CC part of the message
	 */

	public void setCc(java.lang.String newCc)
	{
		cc = newCc;
	}
	/**
	 * Set the Content-Type of the data in the BODY part of the message
	 */

	public void setContentType(java.lang.String newContentType)
	{
		contentType = newContentType;
	}
	/**
	 * Set the address that will be added in the FROM part of the message
	 */

	public void setFrom(java.lang.String newFrom)
	{
		from = newFrom;
	}
	/**
	 * Set the hostname for the mailserver that will be used to send the mail
	 */

	public void setSmtpserver(java.lang.String newSmtpserver)
	{
		smtpserver = newSmtpserver;
	}
	/**
	 * Set the string that will be added in the SUBJECT of the message
	 */

	public void setSubject(java.lang.String newSubject)
	{
		subject = newSubject;
	}
	/**
	 * Set the address that will be added in the TO part of the message
	 */

	public void setTo(java.lang.String newTo)
	{
		to = newTo;
	}
	/**
	 * Gets the attachments
	 * @return Returns a File[]
	 */
	public File[] getAttachments() {
		return attachments;
	}
	/**
	 * Sets the attachments
	 * @param attachments The attachments to set
	 */
	public void setAttachments(File[] attachments) {
		this.attachments = attachments;
	}


}

