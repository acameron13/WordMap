//Dictionary class

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Dictionary{
   
   public Map<Integer, String> words = new HashMap<Integer, String>();
   public String[] wordsList = new String[978];
   public LinkedList[] Adj = new LinkedList[978];

   
   public void readFile(String s) throws FileNotFoundException{
      File a = new File(s);
      Scanner scanner = new Scanner(a);
      int count = 0;
      Map<Integer,String> temp = new HashMap<Integer, String>();
      
      while(scanner.hasNext()){
         String word = scanner.nextLine();
         if(word.length() == 3){
            temp.put(count, word);
            wordsList[count] = word;

            count++;
         } 
      }
      words = temp; 
   }
   
   
   public int compareWords(String word1, String word2){
      int diff = 0;
      for(int i = 0; i < 3; i++){
         if(word1.charAt(i) != word2.charAt(i)){
            diff++;
         }
      }
      return diff;
   }
   
   
   public void createAdjList(){
      LinkedList[] temp = new LinkedList[978];
      for (int i = 0; i < 978; i++){
         String checkWord = words.get(i);
         for (int j = 0; j < 978; j++){
            String tempWord = words.get(j);
            if (compareWords(checkWord, tempWord) == 1){
               if(Adj[i] == null){
                  LinkedList<String> list = new LinkedList<String>();
                  Adj[i] = list;
               }
               if(Adj[j] == null){
                  LinkedList<String> list = new LinkedList<String>();
                  Adj[j] = list;
               }
               if(!Adj[i].contains(tempWord)){
                  Adj[i].add(tempWord);
                  Adj[j].add(checkWord);
               } 
            }
         }
      } 
   }
   
}