/**
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author stephaniecao
 *
 */
public class TweetSearch {
	
	HashMap<String, LinkedList<Tweet>> hashtagMap;
	LinkedList<Tweet> allTweets;
	
	public TweetSearch() {
		
		allTweets = new LinkedList<Tweet>();
		hashtagMap = new HashMap<String, LinkedList<Tweet>>();
		
	        //Get scanner instance
	       
			try {
				Scanner scanner = new Scanner(new File("vaccineData.csv"));
				
				//Set the delimiter used in file
		        scanner.useDelimiter(",");
		        //System.out.println(scanner.nextLine());
		        String id = scanner.next();
		        
		        while (scanner.hasNext()) {
		        	//System.out.println("id: " + id);
		        	String user = scanner.next();
		        	//System.out.println("user: " + user);
		        	String date = scanner.next();
		        	//System.out.println("date: " + date);
		        	
		        	
		        	String text = "";
		        	String next = scanner.next();
		        	
		        	while (!next.contains("['") & scanner.hasNext()) {
		        		text += next;
		        		next = scanner.next();
		        	}
		        			
		        	//System.out.println("text: " + text);
		        	String hashtags = "";
		        	
		        	while (!next.isEmpty()) {
		        		hashtags += next;
		        		if (next.contains("']")) {
		        			break;
		        		}
		        		
		        		if (scanner.hasNext()) {
		        			next = scanner.next();
		        		} else {
		        			break;
		        		}
		        		
		        		
		        	}
		        	
		        	//System.out.println("Hashtags: " + hashtags);
		        	String retweets = scanner.next();
		        	//System.out.println("Retweets: " + retweets);
		        	String favorites = scanner.next();
		        	//System.out.println("Favorites: " + favorites);
		        	
		        	next = scanner.next();
		        	String[] splitted = next.split("\n");
		        	
		        	String isRetweet = splitted[0];
		        	//System.out.println("isRetweet: " + isRetweet);
		        	
		        	if (splitted.length <= 1) {
		        		break;
		        	}
		        	
		        	Tweet newTweet = new Tweet(id, date, user, text, hashtags, retweets, favorites, isRetweet);
		        	
		        	//Updating set of Tweets and hashmap of hashtags and tweets
		        	allTweets.add(newTweet);
		        	updateHashtagMap(newTweet);
		        	
		        	id = splitted[1];
		        }

		        //System.out.println(allTweets.size());
		        scanner.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	private void updateHashtagMap(Tweet t) {
		LinkedList<String> hashtagList = t.getHashtags();
		for (String entry : hashtagList) {
			if (hashtagMap.containsKey(entry)) {
				LinkedList<Tweet> tweets = hashtagMap.get(entry);
				tweets.add(t);
			} else {
				LinkedList<Tweet> newTweetList = new LinkedList<Tweet>();
				hashtagMap.put(entry, newTweetList);
			}
		}
	}
	
	public HashMap<String, LinkedList<Tweet>> getHashtagMap() {
		return hashtagMap;
	}
	
	public LinkedList<Tweet> getAllTweets() {
		return allTweets;
	}
	
	public static void main(String[] args) {
		new TweetSearch();
	}
	
}


