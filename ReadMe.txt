Sarah Batta, Arushi Srivastava, Stephanie Cao, Madelyn Dempsey

In order to interact with our program, you should open up TweetGUI.java, which contains a main 
method that initializes the graphic user interface. You will be prompted to enter the hashtag that 
you wish to receive recommendations based off of, and then you will be prompted to enter how many 
hashtags you would like to be recommended. Please input a hashtag related to covid19, and an integer 
between 1 and 5. If you enter a hashtag that is not prominent enough in our dataset, you will see an 
error message. Otherwise, after waiting less than a minute you should see your recommended hashtags 
along with how many Tweets they were found in with the hashtag you entered. The total number of 
Tweets analyzed will also be displayed. 

Our project changed quite a bit from our original proposal for a multitude of reasons. Firstly, we 
ran into a lot of trouble working with different APIs. Our original idea was to use Instagram’s API, 
however this was not feasible as they had a more rigorous application in order to get access to the 
API. We then turned to Twitter, which we were able to access quite quickly but realized we needed a 
lot of help getting started and were quite confused on how to get started. Finally, we turned to an 
external dataset that contained data of tens of thousands of Tweets related to covid-19 and vaccines, 
which we settled on using. We stuck with our original idea to analyze the relationship between 
different hashtags, which is our get recommendations feature, and decided to add an empirical 
analysis and GUI. We had a bit of trouble with our empirical analysis due to the size of our 
dataset but were able to adjust it in the end. 

In the WeightedGraph.java file, we are creating a weighted graph of hashtags that we use for our 
recommendation feature. In the constructor of this class, an adjacency list and an adjacency matrix 
are initialized and they are both used to represent the graph. The HashMap of hashtags and tweets is 
also initialized in the constructor. In the calculateWeight method, we iterate through each tweet in 
the data set and get a list of all the hashtags contained in that specific tweet. Then, for that 
specific tweet, we check to see if it contains two certain hashtags. This method is called later in 
the class when we add an edge between two nodes (which are hashtags) and so the edge weight is the 
number of tweets that contain both hashtags. In the getRecommendations method, we first get a list of 
all the edges that are connected to the specific hashtag the user wants. We then create a list of 
the weights of those edges and return the number of recommendations the user wants (so if the user 
wants 3 recommendations, we will return the three heaviest edges in the list). 
