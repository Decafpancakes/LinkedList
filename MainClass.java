
//------------------------Main class------------------------------------------


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



class node {
    
    String word;
    int wordCode;
    String meaning;
    node next;
      
}


public class MainClass {

    private static node dictionary;
   
    public static void main(String[] args) throws FileNotFoundException {
       
        File file=new File("C:\\Users\\Zach\\Desktop\\CMPS390\\Linked List\\sll\\textfile.txt");
        Scanner sc=new Scanner(file);
       
       
        String text="";
        while(sc.hasNextLine())
            text+=sc.nextLine();
       
        text=text.toLowerCase();
        text=text.replaceAll("\\W", " ") ;
        String textArray[]=text.split(" ");
       
        for(String s:textArray)
            addWord(s);
       
        System.out.println("-----------------print dictionary---------------");
       
        showDictionary();
       
        System.out.println("--------------------Add word----------------");
        addWord("songs");
        System.out.println("-----------------------------------------------");
        showDictionary();

        System.out.println("--------------------Delete word----------------");
        deleteWord("house");
        System.out.println("-----------------------------------------------");
        showDictionary();
       
        System.out.println("------------------word start with letter-------");
        ArrayList<String> list=wordStartWithLetter('i');
        for(String s: list)
            System.out.println(s);
        System.out.println("-----------------------------------------------");
       
    }
 
    public static void showDictionary() {
        if(dictionary==null) {
            System.out.println("Empty Dictionary");
            return ;
        }
        node temp=dictionary;
 
        while(temp!=null) {
            System.out.println(temp.word);
            temp=temp.next;
        }
       
    }
   
    public static void addWord(String word) {
        if(word==null || word.length()==0 || word.equals(" "))
            return;
        word=word.toLowerCase();
        if(isPresentInDictionary(word)) {
            return ;
        }
        node wordNode=new node();
        wordNode.word=word;
        if(word.length()==1)
            wordNode.wordCode= (word.charAt(0)-'a')*26*26;
        else if(word.length()==2)
            wordNode.wordCode= ((word.charAt(0)-'a')*26*26) + ((word.charAt(1)-'a')*26);
        else if(word.length()>=3)
            wordNode.wordCode= ((word.charAt(0)-'a')*26*26) + ((word.charAt(1)-'a')*26) + ((word.charAt(2)-'a'));
        wordNode.meaning="";
        wordNode.next=null;
        if(dictionary==null) {
            dictionary =wordNode;
        }
        else {
            node prev=null;
            node current=dictionary;
           
            while(current!=null) {
               
                if(current.word.compareTo(word)<0) {
                    prev=current;
                    current=current.next;
                }
                else if(current.word.compareTo(word)>0 && prev==null){
                    wordNode.next=current;
                    current=wordNode;
                    break;
                }
                else{
                    wordNode.next=current;
                    prev.next=wordNode;
                    break;
                }
            }
            if(current==null)
                prev.next=wordNode;
        }
    }
   
    private static boolean isPresentInDictionary(String word) {
        if(dictionary==null)
            return false;
        node temp=dictionary;
        while(temp!=null)
        {
            if(temp.word.equals(word))
                return true;
            temp=temp.next;
        }
        return false;
    }
 
    public static void deleteWord(String word) {
       
        if(isPresentInDictionary(word)) {
            node temp=dictionary;
            node prev=null;
            while(temp!=null) {
                if(temp.word.equals(word)) {
                    prev=temp.next;
                    System.out.println(word+" deleted");
                    break;
                }
                prev=temp;
                temp=temp.next;
            }
        }
       
       
    }
   
    public static void addDefinition(String word,String meaning) {
        if(isPresentInDictionary(word)) {
            node temp=dictionary;
            while(temp!=null) {
                if(temp.word.equals(word)) {
                    temp.meaning=meaning;
                    System.out.println("Meaning added successfully");
                    return ;
                }
                temp=temp.next;
            }
        }
    }
   
    public static void printDefinition(String word) {
        if(isPresentInDictionary(word)) {
            node temp=dictionary;
            while(temp!=null) {
                if(temp.word.equals(word)) {
                    System.out.println(word + " has meaning "+ temp.meaning);
                    return;
                }
                temp=temp.next;
            }
           
        }
    }
   
    public static ArrayList<String> wordStartWithLetter(char letter){
        ArrayList<String> list=new ArrayList<String>();
        node temp=dictionary;
        while(temp!=null) {
            if(temp.word.charAt(0)==letter)
                list.add(temp.word);
            temp=temp.next;
        }
        return list;
    }
 }