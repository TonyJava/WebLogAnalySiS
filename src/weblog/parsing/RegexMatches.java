package weblog.parsing;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public  class RegexMatches
{
	public WebLogWritable parseWebLog(String weblogstring) throws UnsupportedEncodingException{
		WebLogWritable weblog = new WebLogWritable();
		String weblogpattern = "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\"";
		// Create a Pattern object
		Pattern r = Pattern.compile(weblogpattern);
		// Now create matcher object.
		Matcher weblogMatcher = r.matcher(weblogstring);
		if (weblogMatcher.find( )) {
			weblog.setIpaddress(weblogMatcher.group(1));
			weblog.setdateformat(weblogMatcher.group(4));
			weblog.setRequest(weblogMatcher.group(5));
			weblog.setResponse(weblogMatcher.group(6));
			weblog.setSentbyte(weblogMatcher.group(7));
			weblog.setReferer(weblogMatcher.group(8));
			weblog.setUrl(weblogMatcher.group(8));
			weblog.setBrowser(weblogMatcher.group(9));
			weblog.setKeywordsString(this.parsekeyWord(weblogMatcher.group(8)));
			if (!weblogMatcher.group(8).equals("-")){
				weblog.setUrl(this.parseurl(weblogMatcher.group(9)));
			}
			else
			{
				weblog.setUrl(this.parseurl(weblogMatcher.group(8)));	
			}
		} else {
			//	System.out.println("NO Web log MATCH");
			//	System.err.println("MisMtaching record"+weblog.toString());
			weblog.setIpaddress(weblogstring);
			weblog.setRequest("BAD_RECORD");
			return weblog;
		}
		return weblog;
	}

	public String parsekeyWord(String webkeyword) throws UnsupportedEncodingException{
		
		String searchQueryPattern = "(.*?)q=(.*?)(?:&(.*?)|$)";
		// Create a Pattern object
		Pattern keywordpattern = Pattern.compile(searchQueryPattern);
		// Now create matcher object.
		Matcher weblogkeywordMatcher = keywordpattern.matcher(webkeyword);
		if (weblogkeywordMatcher.find( )) {
			return java.net.URLDecoder.decode(weblogkeywordMatcher.group(2), "UTF-8");
		} else {
			return "";
		}
	}

	public String parseurl(String weburl){
		String userUrlEntryPattern = "(http|https)://(.*?)[/\\)\\?\\s$]";
		// Create a Pattern object
		
		Pattern urlpattern = Pattern.compile(userUrlEntryPattern);
		// Now create matcher object.
		Matcher weblogurlmatcher = urlpattern.matcher(weburl);
		if (weblogurlmatcher.find( )) {
			return weblogurlmatcher.group(2);
		} else {
			return "";
		}
	}


//For running individually

	public static void main( String args[] ) throws IOException{

		BufferedReader br = new BufferedReader(new FileReader("weblogintest/webintest.txt"));
		RegexMatches testregexmatch = new RegexMatches();
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				
				testregexmatch.parseWebLog(line);
				//    System.out.println(line);
				System.out.println(testregexmatch.parseWebLog(line).toString());
				System.out.println("URL"+ testregexmatch.parseWebLog(line).getUrl()+ "KeyWord  "+ testregexmatch.parseWebLog(line).getKeywordsString());
				
				line = br.readLine();
				}
             
			System.out.println(testregexmatch.parsekeyWord("http://www.google.com.tw/search?hl=zh-TW&q=hadoop+0.20+mapper+example&btnG=Google+%E6%90%9C%E5%B0%8B&meta=&aq=f&oq="));
			System.out.println(testregexmatch.parseurl("http://www.google.com.tw/search?hl=zh-TW&q=hadoop+0.20+mapper+example&btnG=Google+%E6%90%9C%E5%B0%8B&meta=&aq=f&oq="));
		} finally {
			br.close();
		}

	}
}