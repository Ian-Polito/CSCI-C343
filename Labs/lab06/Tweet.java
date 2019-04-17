/*
C343 Summer 2018
Lab 06
Ian Polito
ipolito
 */

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

    //gets author of tweet
    public String getAuthor() {
        return this.author;
    }

    //gets content of tweet
    public String getContent() {
        return this.content;
    }

    @Override
    public String toString() {
        String result = "";
        result += author + " " + content;
        return result;
    }
}
