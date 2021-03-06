/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package emails;

import com.sendgrid.ASM;
import com.sendgrid.Attachments;
import com.sendgrid.BccSettings;
import com.sendgrid.ClickTrackingSetting;
import com.sendgrid.Client;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.FooterSetting;
import com.sendgrid.GoogleAnalyticsSetting;
import com.sendgrid.Mail;
import com.sendgrid.MailSettings;
import com.sendgrid.Method;
import com.sendgrid.OpenTrackingSetting;
import com.sendgrid.Personalization;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.Setting;
import com.sendgrid.SpamCheckSetting;
import com.sendgrid.SubscriptionTrackingSetting;
import com.sendgrid.TrackingSettings;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author tiago
 */
public class Example {/*
    // Fully populated Mail object
  public static Mail buildKitchenSink() throws IOException {
    Mail mail = new Mail();

    Email fromEmail = new Email();
    fromEmail.setName("Example User");
    fromEmail.setEmail("test@example.com");
    mail.setFrom(fromEmail);

    mail.setSubject("Hello World from the SendGrid Java Library");

    Personalization personalization = new Personalization();
    Email to = new Email();
    to.setName("Example User");
    to.setEmail("test1@example.com");
    personalization.addTo(to);
    to.setName("Example User");
    to.setEmail("test2@example.com");
    personalization.addTo(to);
    Email cc = new Email();
    cc.setName("Example User");
    cc.setEmail("test3@example.com");
    personalization.addCc(cc);
    cc.setName("Example User");
    cc.setEmail("test4@example.com");
    personalization.addCc(cc);
    Email bcc = new Email();
    bcc.setName("Example User");
    bcc.setEmail("test5@example.com");
    personalization.addBcc(bcc);
    bcc.setName("Example User");
    bcc.setEmail("test6@example.com");
    personalization.addBcc(bcc);
    personalization.setSubject("Hello World from the Personalized SendGrid Java Library");
    personalization.addHeader("X-Test", "test");
    personalization.addHeader("X-Mock", "true");
    personalization.addSubstitution("%name%", "Example User");
    personalization.addSubstitution("%city%", "Riverside");
    personalization.addCustomArg("user_id", "343");
    personalization.addCustomArg("type", "marketing");
    personalization.setSendAt(1443636843);
    mail.addPersonalization(personalization);

    Personalization personalization2 = new Personalization();
    Email to2 = new Email();
    to2.setName("Example User");
    to2.setEmail("test1@example.com");
    personalization2.addTo(to2);
    to2.setName("Example User");
    to2.setEmail("test2@example.com");
    personalization2.addTo(to2);
    Email cc2 = new Email();
    cc2.setName("Example User");
    cc2.setEmail("test3@example.com");
    personalization2.addCc(cc2);
    cc2.setName("Example User");
    cc2.setEmail("test4@example.com");
    personalization2.addCc(cc2);
    Email bcc2 = new Email();
    bcc2.setName("Example User");
    bcc2.setEmail("test5@example.com");
    personalization2.addBcc(bcc2);
    bcc2.setName("Example User");
    bcc2.setEmail("test6@example.com");
    personalization2.addBcc(bcc2);
    personalization2.setSubject("Hello World from the Personalized SendGrid Java Library");
    personalization2.addHeader("X-Test", "test");
    personalization2.addHeader("X-Mock", "true");
    personalization2.addSubstitution("%name%", "Example User");
    personalization2.addSubstitution("%city%", "Denver");
    personalization2.addCustomArg("user_id", "343");
    personalization2.addCustomArg("type", "marketing");
    personalization2.setSendAt(1443636843);
    mail.addPersonalization(personalization2);

    Content content = new Content();
    content.setType("text/plain");
    content.setValue("some text here");
    mail.addContent(content);
    content.setType("text/html");
    content.setValue("<html><body>some text here</body></html>");
    mail.addContent(content);

    Attachments attachments = new Attachments();
    attachments.setContent("TG9yZW0gaXBzdW0gZG9sb3Igc2l0IGFtZXQsIGNvbnNlY3RldHVyIGFkaXBpc2NpbmcgZWxpdC4gQ3JhcyBwdW12");
    attachments.setType("application/pdf");
    attachments.setFilename("balance_001.pdf");
    attachments.setDisposition("attachment");
    attachments.setContentId("Balance Sheet");
    mail.addAttachments(attachments);

    Attachments attachments2 = new Attachments();
    attachments2.setContent("BwdW");
    attachments2.setType("image/png");
    attachments2.setFilename("banner.png");
    attachments2.setDisposition("inline");
    attachments2.setContentId("Banner");
    mail.addAttachments(attachments2);

    mail.setTemplateId("13b8f94f-bcae-4ec6-b752-70d6cb59f932");

    mail.addSection("%section1%", "Substitution Text for Section 1");
    mail.addSection("%section2%", "Substitution Text for Section 2");

    mail.addHeader("X-Test1", "1");
    mail.addHeader("X-Test2", "2");

    mail.addCategory("May");
    mail.addCategory("2016");

    mail.addCustomArg("campaign", "welcome");
    mail.addCustomArg("weekday", "morning");

    mail.setSendAt(1443636842);

    ASM asm = new ASM();
    asm.setGroupId(99);
    asm.setGroupsToDisplay(new int[] {4,5,6,7,8});
    mail.setASM(asm);

    // This must be a valid [batch ID](https://sendgrid.com/docs/API_Reference/SMTP_API/scheduling_parameters.html) to work
    // mail.setBatchId("sendgrid_batch_id");

    mail.setIpPoolId("23");

    MailSettings mailSettings = new MailSettings();
    BccSettings bccSettings = new BccSettings();
    bccSettings.setEnable(true);
    bccSettings.setEmail("test@example.com");
    mailSettings.setBccSettings(bccSettings);
    Setting sandBoxMode = new Setting();
    sandBoxMode.setEnable(true);
    mailSettings.setSandboxMode(sandBoxMode);
    Setting bypassListManagement = new Setting();
    bypassListManagement.setEnable(true);
    mailSettings.setBypassListManagement(bypassListManagement);
    FooterSetting footerSetting = new FooterSetting();
    footerSetting.setEnable(true);
    footerSetting.setText("Footer Text");
    footerSetting.setHtml("<html><body>Footer Text</body></html>");
    mailSettings.setFooterSetting(footerSetting);
    SpamCheckSetting spamCheckSetting = new SpamCheckSetting();
    spamCheckSetting.setEnable(true);
    spamCheckSetting.setSpamThreshold(1);
    spamCheckSetting.setPostToUrl("https://spamcatcher.sendgrid.com");
    mailSettings.setSpamCheckSetting(spamCheckSetting);
    mail.setMailSettings(mailSettings);

    TrackingSettings trackingSettings = new TrackingSettings();
    ClickTrackingSetting clickTrackingSetting = new ClickTrackingSetting();
    clickTrackingSetting.setEnable(true);
    clickTrackingSetting.setEnableText(true);
    trackingSettings.setClickTrackingSetting(clickTrackingSetting);
    OpenTrackingSetting openTrackingSetting = new OpenTrackingSetting();
    openTrackingSetting.setEnable(true);
    openTrackingSetting.setSubstitutionTag("Optional tag to replace with the open image in the body of the message");
    trackingSettings.setOpenTrackingSetting(openTrackingSetting);
    SubscriptionTrackingSetting subscriptionTrackingSetting = new SubscriptionTrackingSetting();
    subscriptionTrackingSetting.setEnable(true);
    subscriptionTrackingSetting.setText("text to insert into the text/plain portion of the message");
    subscriptionTrackingSetting.setHtml("<html><body>html to insert into the text/html portion of the message</body></html>");
    subscriptionTrackingSetting.setSubstitutionTag("Optional tag to replace with the open image in the body of the message");
    trackingSettings.setSubscriptionTrackingSetting(subscriptionTrackingSetting);
    GoogleAnalyticsSetting googleAnalyticsSetting = new GoogleAnalyticsSetting();
    googleAnalyticsSetting.setEnable(true);
    googleAnalyticsSetting.setCampaignSource("some source");
    googleAnalyticsSetting.setCampaignTerm("some term");
    googleAnalyticsSetting.setCampaignContent("some content");
    googleAnalyticsSetting.setCampaignName("some name");
    googleAnalyticsSetting.setCampaignMedium("some medium");
    trackingSettings.setGoogleAnalyticsSetting(googleAnalyticsSetting);
    mail.setTrackingSettings(trackingSettings);

    Email replyTo = new Email();
    replyTo.setName("Example User");
    replyTo.setEmail("test@example.com");
    mail.setReplyTo(replyTo);

    return mail;
  }

  // Minimum required to send an email
  public static Mail buildHelloEmail() throws IOException {
    Email from = new Email("test@example.com");
    String subject = "Hello World from the SendGrid Java Library";
    Email to = new Email("tiago_lima411@hotmail.com");
    Content content = new Content("text/plain", "some text here");
    // Note that when you use this constructor an initial personalization object
    // is created for you. It can be accessed via
    // mail.personalization.get(0) as it is a List object
    Mail mail = new Mail(from, subject, to, content);
    Email email = new Email("test2@example.com");
    mail.personalization.get(0).addTo(email);

    return mail;
  }

  public static void baselineExample() throws IOException {
    SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
    sg.addRequestHeader("X-Mock", "true");

    Request request = new Request();
    Mail helloWorld = buildHelloEmail();
    request.setMethod(Method.POST);
    request.setEndpoint("mail/send");
    request.setBody(helloWorld.build());
    Response response = sg.api(request);
    System.out.println(response.getStatusCode());
    System.out.println(response.getBody());
    System.out.println(response.getHeaders());
  }

  public static void kitchenSinkExample() throws IOException {
    SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
    sg.addRequestHeader("X-Mock", "true");

    Request request = new Request();
    Mail kitchenSink = buildKitchenSink();
    request.setMethod(Method.POST);
    request.setEndpoint("mail/send");
    request.setBody(kitchenSink.build());
    Response response = sg.api(request);
    System.out.println(response.getStatusCode());
    System.out.println(response.getBody());
    System.out.println(response.getHeaders());
  }*/

  public static void main(String[] args) {
    /*try {
      SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
      Request request = new Request();
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody("{\"custom_args\":{\"New Argument 1\":\"New Value 1\",\"activationAttempt\":\"1\",\"customerAccountNumber\":\"[CUSTOMER ACCOUNT NUMBER GOES HERE]\"},\"from\":{\"email\":\"sam.smith@example.com\",\"name\":\"Sam Smith\"},\"attachments\":[{\"name\":\"file1\",\"filename\":\"file1.jpg\",\"content\":\"[BASE64 encoded content block here]\",\"disposition\":\"inline\",\"content_id\":\"ii_139db99fdb5c3704\",\"type\":\"jpg\"}],\"personalizations\":[{\"to\":[{\"email\":\"tiago_lima411@hotmail.com\",\"name\":\"Tiago Lima\"}],\"cc\":[{\"email\":\"tiagolima.0411@gmail.com\",\"name\":\"Tiago Lima\"}],\"bcc\":[{\"email\":\"sam.doe@example.com\",\"name\":\"Sam Doe\"}],\"custom_args\":{\"New Argument 1\":\"New Value 1\",\"activationAttempt\":\"1\",\"customerAccountNumber\":\"[CUSTOMER ACCOUNT NUMBER GOES HERE]\"},\"headers\":{\"X-Accept-Language\":\"en\",\"X-Mailer\":\"MyApp\"},\"send_at\":1409348513,\"substitutions\":{\"type\":\"object\",\"id\":\"substitutions\"},\"subject\":\"Hello, World!\"}],\"subject\":\"Hello, World!\",\"ip_pool_name\":\"[YOUR POOL NAME GOES HERE]\",\"content\":[{\"type\":\"text/html\",\"value\":\"<html><p>Hello, world!</p><img src=[CID GOES HERE]></img></html>\"}],\"headers\":{},\"asm\":{\"groups_to_display\":[1,2,3],\"group_id\":1},\"batch_id\":\"[YOUR BATCH ID GOES HERE]\",\"tracking_settings\":{\"subscription_tracking\":{\"text\":\"If you would like to unsubscribe and stop receiveing these emails <% click here %>.\",\"enable\":true,\"html\":\"If you would like to unsubscribe and stop receiving these emails <% clickhere %>.\",\"substitution_tag\":\"<%click here%>\"},\"open_tracking\":{\"enable\":true,\"substitution_tag\":\"%opentrack\"},\"click_tracking\":{\"enable\":true,\"enable_text\":true},\"ganalytics\":{\"utm_campaign\":\"[NAME OF YOUR REFERRER SOURCE]\",\"enable\":true,\"utm_name\":\"[NAME OF YOUR CAMPAIGN]\",\"utm_term\":\"[IDENTIFY PAID KEYWORDS HERE]\",\"utm_content\":\"[USE THIS SPACE TO DIFFERENTIATE YOUR EMAIL FROM ADS]\",\"utm_medium\":\"[NAME OF YOUR MARKETING MEDIUM e.g. email]\"}},\"mail_settings\":{\"footer\":{\"text\":\"Thanks,/n The SendGrid Team\",\"enable\":true,\"html\":\"<p>Thanks</br>The SendGrid Team</p>\"},\"spam_check\":{\"threshold\":3,\"post_to_url\":\"http://example.com/compliance\",\"enable\":true},\"bypass_list_management\":{\"enable\":true},\"sandbox_mode\":{\"enable\":false},\"bcc\":{\"enable\":true,\"email\":\"ben.doe@example.com\"}},\"reply_to\":{\"email\":\"sam.smith@example.com\",\"name\":\"Sam Smith\"},\"sections\":{\"section\":{\":sectionName2\":\"section 2 text\",\":sectionName1\":\"section 1 text\"}},\"template_id\":\"[YOUR TEMPLATE ID GOES HERE]\",\"categories\":[\"category1\",\"category2\"],\"send_at\":1409348513}");
      Response response = sg.api(request);
      System.out.println(response.getStatusCode());
      System.out.println(response.getBody());
      System.out.println(response.getHeaders());
    } catch (IOException ex) {
      throw ex;
    }*/
}

}