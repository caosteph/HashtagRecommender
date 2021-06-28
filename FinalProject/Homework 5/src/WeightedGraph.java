import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class WeightedGraph {
	int vertices;
	LinkedList<String> listOfHashtags;
	LinkedList<Tweet> tweets;
	LinkedList<Edge>[] al;
	HashMap<String, LinkedList<Tweet>> hash;
	int[][] adjMatrix;
	
	@SuppressWarnings("unchecked")
	public WeightedGraph(int vertices) {
		TweetSearch t = new TweetSearch();
		this.hash = t.getHashtagMap();
		this.vertices = vertices;
		this.tweets = t.getAllTweets();
		this.listOfHashtags = new LinkedList<String>();
		this.adjMatrix = new int[vertices][vertices];
		al = new LinkedList[vertices];
		//initialize adjacency lists for all the vertices
		for (int i = 0; i < vertices ; i++) {
			al[i] = new LinkedList<>();
		}
	}
	
	public int calculateWeight(String hashtagOne, String hashtagTwo) {
		int w = 0;
		for (int i = 0; i < tweets.size(); i++) {
			LinkedList<String> hashtags = tweets.get(i).getHashtags();
			if (hashtags.contains(hashtagOne) && hashtags.contains(hashtagTwo)) {
				w = w + 1;
			}
		}
		return w;
	}
	
	public void populateHashtags() {
		for (String s : hash.keySet()) {
			listOfHashtags.add(s);
		}
	}
	
	public void printGraph() throws IOException{
		FileWriter myWriter = new FileWriter("Edges.txt");
		for (int i = 0; i <vertices ; i++) {
			LinkedList<Edge> list = al[i];
			for (int j = 0; j <list.size() ; j++) {
				myWriter.write(listOfHashtags.get(i) + 
						" is connected to " +
						list.get(j).hashtagTwo + " with weight " +  list.get(j).weight + "\n");

			}
		}
		myWriter.close();
		
		
	}
	
	public LinkedList<String> getRecommendations(String hashtag, int r) {
		LinkedList<String> recommended = new LinkedList<String>();
		
		//creating a list of edges associated with the hashtag, meaning the other hashtags
		//it is tweeted with
		LinkedList<Edge> edges = new LinkedList<Edge>();
		int index = listOfHashtags.indexOf(hashtag);
		for (int i = 0; i < al[index].size(); i++) {
			edges.add(al[index].get(i));
		}
				
		//creating a list of the weight of each of those edges
		LinkedList<Integer> weights = new LinkedList<Integer>();
		for (Edge e : edges) {
			String h2 = e.getHashtag();
			int index2 = listOfHashtags.indexOf(h2);
			weights.add(adjMatrix[index][index2]);
		}
		
		Collections.sort(edges);
		for (int i = 0; i < r; i++) {
			recommended.add(edges.get(i).getHashtag() + " - " + edges.get(i).getWeight() + " Tweets");
		}

		// return a list of the top r hashtags used with the inputted hashtag and their weights
		return recommended; 
	}
	
	public void addEdge(Tweet t) {
		LinkedList<String> hashtags = t.getHashtags();
		for (int i = 0; i < hashtags.size(); i++) {
			String h1 = hashtags.get(i);
			int index1 = listOfHashtags.indexOf(h1);
			for (int j = i + 1; j < hashtags.size(); j++) {
				String h2 = hashtags.get(j);
				int index2 = listOfHashtags.indexOf(h2);
				int weight = adjMatrix[index1][index2];
				
				if (weight == 0) {
					adjMatrix[index1][index2] = 1;
					adjMatrix[index2][index1] = 1;
					al[index1].add(new Edge(h2, 1));
					al[index2].add(new Edge(h1, 1));
				} else {
					int newWeight = weight + 1;
					adjMatrix[index1][index2] = newWeight;
					adjMatrix[index2][index1] = newWeight;
					
					for (Edge e1 : al[index1]) {
						if (e1.getHashtag().equals(h2)) {
							e1.increaseWeight();
							break;
						}
					}
					
					for (Edge e2 : al[index2]) {
						if (e2.getHashtag().equals(h1)) {
							e2.increaseWeight();
							break;
						}
					}					
				}
			}
		}
	}	
}