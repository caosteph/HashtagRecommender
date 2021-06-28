

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * the tester class.
 * @author swapneel
 */
public class VectorSpaceModelTester {

	public static void main(String[] args) {
		
		Tweet vax1 = new Tweet("1", "Pfizer");
		Document query = new Document(vax1);
		
		Tweet vax2 = new Tweet("2", "Moderna");
		Document query2 = new Document(vax2);
		
		Tweet vax3 = new Tweet("3", "Johnson and Johnson");
		Document query3 = new Document(vax3); 
		
		Tweet vax4 = new Tweet("4", "Vaxzevria"); 
		Document query4 = new Document(vax4);
		
		Tweet vax5 = new Tweet("5", "Novavax");
		Document query5 = new Document(vax5); 
		
		
		ArrayList<Document> documents = new ArrayList<Document>();
		documents.add(query);
		documents.add(query2);
		documents.add(query3);
		documents.add(query4);
		documents.add(query5);
        
		ArrayList<Document> tweets = new ArrayList<Document>();
		TweetSearch t = new TweetSearch(); 
		LinkedList<Tweet> list = t.getAllTweets();
		
		for (Tweet tw : list) {
			Document doc = new Document(tw);
			tweets.add(doc);
			System.out.println(tw.getText());
		}
		
		Corpus corpus = new Corpus(tweets);
		HashSet<String> querySet = new HashSet<String>();
		querySet.add("Pfizer");
		querySet.add("Moderna");
		querySet.add("Johnson and Johnson");
		querySet.add("Vaxzevria");
		querySet.add("Novavax");
		
		VectorSpaceModel vectorSpace = new VectorSpaceModel(corpus, querySet);
		
		//HashMap<Document, Double> favorites = new HashMap<Document, Double>();
		
		LinkedList<LinkedList<Document>> favorites = new LinkedList<LinkedList<Document>>();
		
		for (int i = 0; i < documents.size(); i++) {
			
			LinkedList<Document> nums = new LinkedList<Document>();
		    for (int j = 0; j < tweets.size(); j++) {
				Document doc1 = documents.get(i);
				Document doc2 = tweets.get(j);
				double similarity = vectorSpace.cosineSimilarity(doc1, doc2);
				//System.out.println(similarity);
				//if (similarity > .15) {
					nums.add(doc2);
				//}
				
				//System.out.println("\nComparing " + doc1 + " and " + doc2);
				//System.out.println(vectorSpace.cosineSimilarity(doc1, doc2));
			}
		    favorites.add(nums);
		    nums.clear();
		}
		
		LinkedList<Double> popularities = new LinkedList<Double>();
		for (LinkedList<Document> li : favorites) {
			Double result = 0.0; 
			for (Document doc : li) {
				Tweet tweet = doc.getFile();
				int retweets = tweet.getRetweets();
				int numfavs = tweet.getFavorites();
				double mult = retweets * numfavs;
				result += mult; 
			}
			popularities.add(result); 
		}
		System.out.println("1- Pfizer");
		System.out.println("2- Moderna");
		System.out.println("3- Johnson and Johnson");
		System.out.println("4- Vaxzevria");
		System.out.println("5- Novavax");
		for (Double dub : popularities) {
			System.out.println(dub);
		}
		/*
		for(int i = 1; i < documents.size(); i++) {
			Document doc = documents.get(i);
			System.out.println("\nComparing to " + doc);
			System.out.println(vectorSpace.cosineSimilarity(query, doc));
		}
		*/
		
		
		
	}

}
