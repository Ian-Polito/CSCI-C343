/*
C343 Summer 2018
Lab 19
Ian Polito
ipolito
 */

import java.io.*;
import java.util.*;

public class Lab19 {

    Hashtable<String, LinkedList<Integer>> myDictionary = new Hashtable<String, LinkedList<Integer>>();

    public Lab19(String pFileName) throws IOException {

        int lLineNumber = 1;
        String lFoundLines;
        File file = new File(pFileName);
        Scanner lInputScanner = new Scanner(file);

        while (lInputScanner.hasNext()) {

            String newLine = lInputScanner.nextLine();
            String[] lAllWordsInLine = newLine.split("[^a-z^A-Z^']");

            for (int i = 0; i < lAllWordsInLine.length; i++) {
                if (myDictionary.containsKey(lAllWordsInLine[i])) {
                    // if the word is already in the dictionary,
                    //   add the the current line number to the line numbers already in the myDictionary,
                    //   i.e. modify the value associated with the key,
                    //   so that the value is updated with the current line:
                    LinkedList<Integer> temp = myDictionary.get(lAllWordsInLine[i]);
                    temp.add(lLineNumber);
                    myDictionary.remove(lAllWordsInLine[i]);
                    myDictionary.put(lAllWordsInLine[i],temp);
                } else {
                    // if it's a new word, store the current line number
                    //   as value in the myDictionary for the new word used as key
                    LinkedList<Integer> temp = new LinkedList<Integer>();
                    temp.add(lLineNumber);
                    myDictionary.put(lAllWordsInLine[i],temp);
                }
            }
            lLineNumber++;
        }
        lInputScanner.close();
    }

    public void wordQuery(String pWord) {
        if (myDictionary.containsKey(pWord)) {
            LinkedList<Integer> lLines = myDictionary.get(pWord);
            System.out.println(pWord + " is found in lines: " + lLines);
        } else {
            System.out.println(pWord + " not found.");
        }
    }

    public static void main(String args[]) throws IOException {
        //TESTING

        Lab19 mytxt = new Lab19("lab-19-testing.txt");
        mytxt.wordQuery("algorithm");
        mytxt.wordQuery("data");
        mytxt.wordQuery("memory");
        mytxt.wordQuery("ADT");

    }
}