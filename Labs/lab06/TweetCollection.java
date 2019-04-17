/*
C343 Summer 2018
Lab 06
Ian Polito
ipolito
 */

import java.util.*;
import java.net.*;

public class TweetCollection {

    private LinkedList<Tweet> tweets;

    //constructor for TweetCollection
    public TweetCollection(String url) {
        this.tweets = new LinkedList<Tweet>();
        this.scanURL(url);
    }

    //scans the given URL and adds tweets to LinkedList
    public void scanURL(String myURL) {
        try {
            URL url = new URL(myURL);
            Scanner in = new Scanner(url.openStream());
            while (in.hasNext()) {
                String author = in.next();
                String content = in.nextLine();
                content.trim();
                Tweet i = new Tweet(content, author);
                tweets.addFirst(i);
            }
            in.close();
        } catch (Throwable e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }
    }

    //prints the tweets in the collection of the given author
    public void findAuthor(String author) {
        for (Tweet i : tweets) {
            if (i.getAuthor().toLowerCase().equals(author.toLowerCase())) {
                System.out.println(i);
            }
        }
    }

    //prints the tweets that contain the given search term in their content
    public void searchContent(String search) {
        for (Tweet i : tweets) {
            if ((i.getContent().toLowerCase()).contains(search.toLowerCase())) {
                System.out.println(i);
            }
        }
    }
}
