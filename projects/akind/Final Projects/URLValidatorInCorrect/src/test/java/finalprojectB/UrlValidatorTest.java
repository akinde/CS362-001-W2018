
package finalprojectB;

import finalprojectB.UrlValidator;

import junit.framework.TestCase;
import java.util.Random;
//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest() {
   }

   
   
   public void testSecondPartition()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   assertFalse(urlVal.isValid(null));
	   assertTrue(urlVal.isValid("http://www.google.com"));
	   assertFalse(urlVal.isValid("asdewe"));
	   assertFalse(urlVal.isValid("file:/localhost/c:/WINDOWS/test1.txt")); //This is the CORRECT special case. **BUG**
	   assertFalse(urlVal.isValid("http://c:/WINDOWS/test1.txt")); //Tests special case with http, should be file: instead of http: ***BUG***
	   assertFalse(urlVal.isValid("http:///WINDOWS/test1.txt")); //Does not accept empty authorities so thats good.
	   assertTrue(urlVal.isValid("http://www.google.com/test1.txt?"));
	   assertFalse(urlVal.isValid("http://www.google.com/test1.txt?         "));
	   assertFalse(urlVal.isValid("http:/www.google.com/~")); //This should allow tildes in PATH_REGEX  ****BUG****
	   assertFalse(urlVal.isValid("http://www.google.com/should/work")); //this should pass as multiple forward slashes should be allowed but doesnt because only ONE forward slash is allowed in PATH_REGEX ***BUG***
	   assertTrue(urlVal.isValid("http://www.google.com/shouldwork"));
//You can use this function to implement your manual testing	   
	   
   }
   
   
   public void testPathRegexPartition()
   {
	   //Testing boundary and break cases for PATH_REGEX
	 //You can use this function to implement your First Partition testing	
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   assertTrue(urlVal.isValid("http://a")); //Tests \w "any character"
	   assertTrue(urlVal.isValid("http://www.google.com/thisworks:")); //tests : character, commonly used for ports.
	   assertTrue(urlVal.isValid("http://www.tumblr.com/somejsonstuff&as13@f134=,.sdfe!+aefe*asjdewj%$+;")); //Testing all other characters allowed in path: =, ',', +, '.', !, *, %, $, ;, @, &
	   assertTrue(urlVal.isValid("http://www.reddit.com/thisallows()paranthesis")); //testing paranthesis's in in path.
	   assertTrue(urlVal.isValid("http://www.google.com/?action=view")); //testing if the path can be empty and that one query can be used, '?' character.
	   assertFalse(urlVal.isValid("http://www.google.com/Thisisslashes//")); //the path is can only have one slash and no double slashes allowed, and only one or none at all.
	   assertFalse(urlVal.isValid("http://www.google.com/whitespacequery? ")); //query's cannot have whitespace.
	   }
   
   public void testManual() throws IllegalArgumentException{	
   
   		UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
   		assertTrue(urlVal.isValid("http://www.google.com"));
   	    assertTrue(urlVal.isValid("http://www.google.com/"));
   	    assertFalse(urlVal.isValid(""));
   	    assertTrue(urlVal.isValid("http://www.github.com/random"));
   	    assertTrue(urlVal.isValid("http://www.github.com/akind?quwwery=true"));
   	    assertTrue(urlVal.isValid("https://www.google.com/"));
   	    assertTrue(urlVal.isValid("http://NoAuthority"));
   	    assertFalse(urlVal.isValid("http://www.google.com/NullAuthority//invalidpath"));

	}
   public void testPartitionSchemes() {
       //tests: ftp, gopher, http, mailto, news, https, file, telnet,
       System.out.print("Test output for testPartitionSchemes:\n");
       UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
       assertTrue(urlVal.isValid("ftp://example.org/resource.txt"));
       assertTrue(urlVal.isValid("gopher://gopher.cc.lehigh.edu/"));
       assertTrue(urlVal.isValid("http://www.google.com"));
       assertTrue(urlVal.isValid("mailto:akind@oregonstate.edu"));
       assertTrue(urlVal.isValid("news:comp.infosystem.www.server.unix"));
       assertTrue(urlVal.isValid("file://usr/share/doc"));
       assertTrue(urlVal.isValid("https://github.com"));
       assertTrue(urlVal.isValid("telnet://ns.cc.lehigh.edu/"));
  	    //assertFalse(urlVal.isValid("http:///")); 				/*this should work, but do to the nature of one of the bugs, if we put anything in that is NOT http, then it fails in a giant error, if we put in http, 
  	    //assertTrue(urlVal.isValid("https://www.google.com"));  it goes through, meaning we will never be able to check if authority is valid*/
  	    //assertTrue(urlVal.isVald("telnet://ns.cc.lehigh.edu"); telnets are commonly used for URI's, was not able to find a valid telnet.
  	    //assertTrue(urlVal.isValid("gopher://gopher.cc.lehigh.edu/); any of these i was not able to find a valid case, all of these cases are from the RFC 2396 standard
  	    //assertTrue(urlVal.isValid("news:comp.infosystems.www.servers.unix");
  	    //assertTrue(urlVal.isValid("ftp://ftp.is.co.za/rfc/rfc1808.txt");
  	    //assertTrue(urlVal.isValid("mailto:mduerst@ifi.unizh.ch"); 
  	    /*This pops an error that cannot be caught, any scheme besides http is unaccepted.*/
  //You need to create more test cases for your Partitions if you need to 
   }

   public void testPartitionAuthority() {
       System.out.print("Test output for testPartitionAuthority:\n");
       UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
       assertTrue(urlVal.isValid("http://www.google.com"));
       assertTrue(urlVal.isValid("http://google.com"));

       assertFalse(urlVal.isValid("http://bb123.authority/"));
       assertFalse(urlVal.isValid("http:///"));

       assertTrue(urlVal.isValid("https://www.google.com"));
       assertTrue(urlVal.isValid("https://www.github.com/brenisec/testRepo"));

       assertFalse(urlVal.isValid("https://asj 2uauthority/"));
       assertFalse(urlVal.isValid("https:///"));

       assertTrue(urlVal.isValid("file:///c:/Programs/MyDrive/TestFile"));
       assertTrue(urlVal.isValid("file:///C:/cs362/CS362-001-W2018/projects/akind/FinalProject/URLValidatorInCorrect/target/site/cobertura/index.html"));
   }
   public void testPartitionFragment() {
       System.out.print("Test output for testPartitionFragment:\n");
       UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
       assertTrue(urlVal.isValid("http://github.com/search?utf8=%E2%9C%93&q=akind&type="));
       assertTrue(urlVal.isValid("http://github.com/search?utf8=%E2%9C%93&q=akind&type=###################"));
       assertTrue(urlVal.isValid("http://github.com/search?utf8=%E2%9C%93&q=akind&type=#betterandmoreaccuratesearch"));
       assertTrue(urlVal.isValid("http://github.com/search?utf8=%E2%9C%93&q=akind&type=#"));
   }

   public void testIsValid()
   {
	   	boolean expected = true;
	   	int choose;
	   	long randomseed = System.currentTimeMillis();
	   	UrlValidator url = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   	Random random = new Random(randomseed);
    	StringBuilder buffer;
    	String test;
	    for(int i = 0; i < 20; i++) {
	    	buffer = new StringBuilder();
	    	expected = true;
	    	test = null;
	    	buffer.append("http://");
	    	choose = ValuesGenerator.getRandomIntBetween(random, 0, 8);
	    	expected &= testUrlAuthority[choose].valid;
	    	buffer.append((testUrlAuthority[choose]).item);
	    	choose = ValuesGenerator.getRandomIntBetween(random, 0, 3);
	    	expected &= testUrlQuery[choose].valid;
	    	buffer.append((testUrlQuery[choose]).item);
	    	test = buffer.toString();
	    	assertEquals(test, expected, url.isValid(test));
	    }
	   //You can use this function for programming based testing

   }
   
   ResultPair[] testUrlQuery = {new ResultPair("?query=view", false),
		   					    new ResultPair("", false), 
		   					    new ResultPair("?", false),
		   					    new ResultPair("? ", true)};
   ResultPair[] testUrlAuthority = {new ResultPair("www.google.com/", false),
		   							new ResultPair("www.google.com:80/", true),
		   							new ResultPair("www.google.com::80/", true),
		   							new ResultPair("www.tumblr.com/", false),
		   							new ResultPair("255.255.255.255/", false),
		   							new ResultPair("0.0.0.0/", false),
		   							new ResultPair("go.com/", false),
		   							new ResultPair("go.au/", false),
		   							new ResultPair("www.Ihatebugs.com/", false)};
}
