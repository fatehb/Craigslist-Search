
/**
 *
 * @author Muhammad Bajwa
 */
// This examplebased on the code from from the book _Java in a Nutshell_ by David Flanagan.
// Written by David Flanagan.  Copyright (c) 1996 O'Reilly & Associates.

import java.net.*;
import java.io.*;
import java.util.*;

public class GetURLInfo {
    public static String ct=null;
    public static int cl;
    public static String lm=null;
    public static String ep=null;
    public static String ce=null;

    public static void printURLinfo(URLConnection uc) throws IOException {
        // Display the URL address, and information about it.
        System.out.println(uc.getURL().toExternalForm() + ":");
        System.out.println("  Content Type: " + uc.getContentType());
        ct=uc.getContentType();
        System.out.println("  Content Length: " + uc.getContentLength());
        cl=uc.getContentLength();
        System.out.println("  Last Modified: " + new Date(uc.getLastModified()));
        lm=(new Date(uc.getLastModified())).toString();
        System.out.println("  Expiration: " + new Date(uc.getExpiration()));
        ep=(new Date(uc.getExpiration())).toString();
        System.out.println("  Content Encoding: " + uc.getContentEncoding());
        ce=uc.getContentEncoding();



    } // printURLinfo


}
