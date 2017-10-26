package com.rosteringester.emailsystem;

import java.awt.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class SendEmail {

    public void init(String subject, String body) {
        Desktop desktop;
        String mailTo = "hernandezj@aetna.com";
        String cc = "";
        String lsubject = subject;
        String lbody = body;



        try {
            lsubject = URLEncoder.encode(lsubject, "UTF-8");
            lbody = URLEncoder.encode(lbody, "UTF-8");

            lsubject = lsubject.replaceAll("\\+", "%20");
            lbody = lbody.replaceAll("\\+", "%20");
            desktop = Desktop.getDesktop();

            if (Desktop.isDesktopSupported()) {
                URI mailto = null;
                try {
                    mailto = new URI("" +
                            "mailto:" + // Mail to options
                            mailTo + "?" + // All the email addresses.
                            "subject=" +
                            lsubject +
                            "&BODY=" +
                            lbody
                            //"Boom" // Email body
                    );
                    desktop.mail(mailto);

                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }catch (Exception ex) {
                    ex.printStackTrace();
                }

            } else {
                throw new RuntimeException("desktop doesn't support mailto");
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }

    } // end of init

    private static final String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8").replace(" ", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

} // End of Class
