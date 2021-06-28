import java.util.LinkedList;

/**
 * 
 */

/**
 * @author stephaniecao
 *
 */
public class Tweet {

	private String username;
	private String date;
	private LinkedList<String> hashtags;
	private int retweets;
	private int favorites;
	private String isRetweet;
	private String text;
	private String id;
	
	public Tweet(String i, String d, String u, String t, String h, String r, String f, String iR) {
		hashtags = new LinkedList<String>();
		username = u;
		date = d;
		id = i;
		text = t;
		
		String hSubstring;
		
		if (h.length() > 0) {
			if (h.charAt(0) == '"') {
				hSubstring = h.substring(3, h.length() - 3);
			} else {
				hSubstring = h.substring(2, h.length() - 2);
			}
			
			String[] hashtagArray = hSubstring.split("' '");
			for (String item : hashtagArray) {
				if (item != null) {
					//System.out.println(item);
					hashtags.add(item.toLowerCase());
				}
			}
		} 
		
		retweets = Integer.parseInt(r);
		favorites = Integer.parseInt(f);
		isRetweet = iR;
	}
	
	public Tweet(String name, String t) {
		hashtags = null; 
		username = name;
		date = "";
		id = name; 
		text = t; 
		retweets = 0; 
		favorites = 0; 
		isRetweet = "false";
	}
	
	public String getText() {
		return text;
	}
	
	public LinkedList<String> getHashtags() {
		return hashtags;
	}
	
	public int getRetweets() {
		return retweets;
	}
	
	public int getFavorites() {
		return favorites;
	}
	
	public String getID() {
		return id;
	}
	
	public String getUser() {
		return username;
	}
	
	public String getDate() {
		return date;
	}
	
	public String isRetweet() {
		return isRetweet;
	}
}
