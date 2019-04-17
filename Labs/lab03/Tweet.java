import java.util.*;

public class Tweet {

    private String content;
    private String author;

    //constructor for Tweet, takes two strings, one for content and one for author of tweet
    public Tweet(String cont, String auth) {
        this.content = cont;
        this.author = auth;
    }

    //prints out contents of tweet
    public void printContent() {
        System.out.println(this.content);
    }

    //returns true if content contains the given String s, otherwise returns false
    public boolean contains(String s) {
        return content.contains(s);
    }

}
