/*http://www.java2s.com/Code/Java/Development-Class/ParseanApachelogfilewithRegularExpressions.ht
 *http://www.ocpsoft.org/opensource/guide-to-regular-expressions-in-java-part-1/
 * 
 * Copyright (c) Ian F. Darwin, http://www.darwinsys.com/, 1996-2002.
 * All rights reserved. Software written by Ian F. Darwin and others.
 * $Id: LICENSE,v 1.8 2004/02/09 03:33:38 ian Exp $
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS''
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 * Java, the Duke mascot, and all variants of Sun's Java "steaming coffee
 * cup" logo are trademarks of Sun Microsystems. Sun's, and James Gosling's,
 * pioneering role in inventing and promulgating (and standardizing) the Java 
 * language and environment is gratefully acknowledged.
 * 
 * The pioneering role of Dennis Ritchie and Bjarne Stroustrup, of AT&T, for
 * inventing predecessor languages C and C++ is also gratefully acknowledged.
 */

package JavaRegEx;

import java.util.regex.*;

/**
 * Common fields for Apache Log demo.
 */
interface LogExample {
  /** The number of fields that must be found. */
  public static final int NUM_FIELDS = 9;

  /** The sample log entry to be parsed. */
 // public static final String logEntryLine = "123.45.67.89 - - [27/Oct/2000:09:27:09 -0400] \"GET /java/javaResources.html HTTP/1.0\" 200 10450 \"-\" \"Mozilla/4.6 [en] (X11; U; OpenBSD 2.8 i386; Nav)\"";
 public static final String logEntryLine = "216.24.131.152 - - [25/Jul/2009:01:12:16 -0800] \"GET /?post=321 HTTP/1.1\" 200 8681 \"http://www.google.com.tw/search?hl=zh-TW&q=hadoop+0.20+mapper+example&btnG=Google+%E6%90%9C%E5%B0%8B&meta=&aq=f&oq=\" \"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.5; en-US; rv:1.9.1.1) Gecko/20090715 Firefox/3.5.1\"";
 // public static final String logEntryLine = "74.125.75.17 - - [25/Jul/2009:01:13:06 -0800] \"GET /gwidgets/alexa.xml HTTP/1.1\" 200 2969 \"-\" \"Mozilla/5.0 (compatible) Feedfetcher-Google; (+http://www.google.com/feedfetcher.html)\"";
}


/**
 * Parse an Apache log file with Regular Expressions
 */
public class LogRegExp implements LogExample {

  public static void main(String argv[]) {

  //  String logEntryPattern = "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\"";
	  String logEntryPattern = "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\"";
    System.out.println("Using RE Pattern:");
    System.out.println(logEntryPattern);

    System.out.println("Input line is:");
    System.out.println(logEntryLine);

    Pattern p = Pattern.compile(logEntryPattern);
    Matcher matcher = p.matcher(logEntryLine);
    if (!matcher.matches() || 
      NUM_FIELDS != matcher.groupCount()) {
      System.err.println("Bad log entry (or problem with RE?):");
      System.err.println(logEntryLine);
      return;
    }
    System.out.println("IP Address: " + matcher.group(1)); //([\\d.]+)
    System.out.println("Date&Time: " + matcher.group(4));//
    System.out.println("Request: " + matcher.group(5));
    System.out.println("Response: " + matcher.group(6));
    System.out.println("Bytes Sent: " + matcher.group(7));
      
    
	 if (!matcher.group(8).equals("-")){
	      System.out.println("Referer: " + matcher.group(8));
	      System.out.println("Browser: " + matcher.group(9));
	 }else{
		 System.out.println("Referer: " +"-");
	      System.out.println("Browser: " + matcher.group(9));
	 }
	 String refererPatten ="(.*?)&q=(.*?)&(.*?)";
	 Pattern p_referer = Pattern.compile(refererPatten);
//	 Matcher referermatcher = p_referer.matcher(matcher.group(8));
	 String referer=matcher.group(8);
	 int keystartand = referer.indexOf("q=");
	// int keystartqmark = referer.indexOf("");
//	 int keystartfinal;
//	 if(keystartand!=-1){
//		 keystartfinal=keystartand;
//	 }else {
//		 keystartfinal=keystartqmark;
//	 }
	 int keyend=referer.indexOf("&", keystartand+3);
	// System.out.println("Key Start"+keystart +"Key End" +keyend);
	 String keywordsString=referer.substring(keystartand+3, keyend);
	 
	 System.out.println("Key Words"+ keywordsString);
//    String keywordpattern = "(p|q)=(.+?)&";
//    Pattern p2 = Pattern.compile(keywordpattern);
//    Matcher matcherkeyword = p2.matcher(matcher.group(8));
//    
//    System.out.println("Browser: " + matcherkeyword.group(1));
//    System.out.println("Browser: " + matcherkeyword.group());
  }
}
