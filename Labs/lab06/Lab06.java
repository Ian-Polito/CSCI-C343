/*
C343 Summer 2018
Lab 06
Ian Polito
ipolito
 */

public class Lab06 {

    public static void main(String[] args) {
        //Test TweetCollection
        TweetCollection tester = new TweetCollection("http://homes.soic.indiana.edu/classes/summer2018/csci/c343-mitja/test2018/tweet-data-May16.txt");
        tester.findAuthor("NoSQLDigest"); //print all tweets by this author
        System.out.println("");
        tester.searchContent("big data"); //print all tweets that mention big data
    }
}
