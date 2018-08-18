//CREATED: ALEXANDER LEE 05/14/2018

import java.io.*;
import java.util.*;
import java.util.Scanner.*;
import java.util.List.*;
import java.util.Hashtable.*;

public class FindAnagrams 
{
    public static void main(String args[]) throws FileNotFoundException 
    {
        //Store all contents of wordList.txt in array
        System.out.println("Enter word list file name:");
        Scanner fileIn = new Scanner(System.in);
        String file = fileIn.next();
        File wordList = new File(file);
        Scanner scanner = new Scanner(wordList);
        List <String> lines = new ArrayList <String>();
        scanner.nextLine();
        while (scanner.hasNextLine()) 
        {
            lines.add(scanner.nextLine());   
        }
        String[] wordL = lines.toArray(new String[0]);
        
        //Create hashtable that maps all letters of alphabet to prime number
        int isPrime = 0;
        int num = 2;
        int counter = 1;
        int prime[] = new int[26];
        prime[0] = 2;
        //Get first 26 prime numbers
        for (int i = 2; i <= 26; ) 
        {
            for (int j = 2; j <= Math.sqrt(num); j++) 
            {
                if (num % j == 0) 
                {
                    isPrime = 0;
                    break;
                }
            }
            if (isPrime != 0) 
            {
                prime[counter] = num;
                counter++;
                i++;
            }
            isPrime = 1;
            num++;
        }
        //Create hashtable
        counter = 0;
        Hashtable <Character, Integer> charVal = new Hashtable <Character, Integer>();
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) 
        {
            charVal.put(alphabet, prime[counter]);
            counter++;
        }
                
        //Give every word a numerical value depending on its letters
        double wordVal[] = new double[wordL.length];
        double wordV = 1;
        double charV = 0;
        String word;
        char c;
        
        for (int i = 0; i < wordL.length; i++) 
        {
            word = wordL[i];
            for (int j = 0; j < word.length(); j++) 
            {
                c = word.charAt(j);
                charV = charVal.get(c);
                wordV = wordV * charV;
            }
            wordVal[i] = wordV;
            wordV = 1;
        }
        
        //Anagram ADT testing
        System.out.println("Anagram ADT Test"); 
        char anagramWord[] = {'r', 'a', 'c', 'e', 's'};
        System.out.println("String constructor test");
        Anagram a1 = new Anagram("scare");     //String constructor test
        Anagram a2 = new Anagram(anagramWord); //Char array test
        Anagram a3 = new Anagram("scary");
        a1.print();                            //Print test
        System.out.println();
        System.out.println("Char array constructor test");
        a2.print();
        System.out.println();
        System.out.println("Anagrams test 1");
        a1.compare(a2, charVal);               //Compare anagrams test
        System.out.println();
        System.out.println("Anagrams test 2");
        a2.compare(a3, charVal);
        System.out.println();
        String randomWord = a1.returnWord(); //Return word test
        System.out.println("Return word test");
        System.out.println(randomWord);
        
        //Find list of anagrams from wordList.txt
        char a;
        //Read user input and print out anagrams
        do 
        {
            System.out.println("type a string of letters");
            double userVal = 1;
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            String userIn[] = input.split(" ");

            for (int i = 0; i < userIn.length; i++) 
            {
                input = userIn[i];
                for (int j = 0; j < input.length(); j++) 
                {
                    c = input.charAt(j);
                    charV = charVal.get(c);
                    userVal = userVal * charV;
                }
                for (int x = 0; x < wordVal.length; x++) 
                {
                    if (userVal == wordVal[x] && !input.equals(wordL[x])) 
                    {
                        System.out.println(wordL[x]);
                    }
                }
                userVal = 1;
            }
            System.out.println("Do another (y/n)?");
            a = in.next().charAt(0);
        } while (a == 'Y' || a == 'y');
    }
}   

