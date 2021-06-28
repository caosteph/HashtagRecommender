
public class Edge implements Comparable<Edge> {
    String hashtagTwo;
    int weight;

    public Edge(String hashtagTwo, int weight) {
        this.hashtagTwo = hashtagTwo;
        this.weight = weight;
    }
    
    public int getWeight() {
    	return weight;
    }
    
    public String getHashtag() {
    	return this.hashtagTwo;
    }
    
    public void increaseWeight() {
    	this.weight++;
    }
    
    @Override
    public int compareTo(Edge o) {
    	int c = o.getWeight() - weight;
    	return c;
    }
}
