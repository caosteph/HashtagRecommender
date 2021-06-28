/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class TweetGUI implements Runnable {
    public void run() {
        // NOTE : recall that the 'final' keyword notes immutability even for local variables.

        // Top-level frame in which game components live
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
    	final JFrame frame = new JFrame("TOP LEVEL FRAME");
        frame.setLocation(300, 200);
        
        JFrame f = new JFrame();   
        String hashtag = JOptionPane.showInputDialog(f,"Welcome to our Hashtag Recommender!\n"
        		+ "Please enter a hashtag related to COVID-19 that you would like to\n"
        		+ "receive recommendations based off of.\n");  
        
        JFrame f2 = new JFrame();   
        String numRecs = JOptionPane.showInputDialog(f2,"How many recommendations do you want? A window will pop up when they are ready."); 
        
        
        JPanel welcomePanel = new JPanel();        
        welcomePanel.setPreferredSize(new Dimension(400, 300));
        //welcomePanel.add(new Label("Getting your recommendations now! "), BorderLayout.CENTER);
        frame.setVisible(true);

       
        LinkedList<String> results = getRecommendations(hashtag, Integer.parseInt(numRecs));
        frame.add(welcomePanel);
        welcomePanel.add(new JLabel(""));

        for (String line : results) {
        	JLabel newLabel = new JLabel(line);
        	newLabel.setText("<html><p style=\"width:300px\">" + line + "</p></html>");
        	welcomePanel.add(newLabel, BorderLayout.CENTER);
        }
	   
        //Adding a quit button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.SOUTH);
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	        System.exit(0);
        	}
        });
        control_panel.add(quitButton);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    private LinkedList<String> getRecommendations(String h, int r) {
    	
    	try {
    		LinkedList<String> recs = new LinkedList<String>();
    		recs.add("Recommendations for #" + h + ": ");
        	TweetSearch t = new TweetSearch();

            HashMap<String, LinkedList<Tweet>> map = t.getHashtagMap();
            
            LinkedList<Tweet> tweeeets = t.allTweets;
        	  int vertices = map.size(); // length of the linkedList
              WeightedGraph graph = new WeightedGraph(vertices);

              graph.populateHashtags();
              
              for (Tweet tweet : tweeeets) {
              	graph.addEdge(tweet);
              }
              
              // this will get three hashtags that are most related to hashtag "covid19"
              LinkedList<String> recList = graph.getRecommendations(h, r);
              
              for (int i = 0; i < recList.size(); i++) {
            	  recs.add((i + 1) + ". #" + recList.get(i));
              }
              
              recs.add("Total tweets: " + tweeeets.size());
              return recs;
    	} catch (Exception e) {
    		LinkedList<String> error = new LinkedList<String>();
    		error.add("Sorry, you entered an invalid hashtag. Please try again! ");
    		return error;
    	}
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new TweetGUI());
    }
}