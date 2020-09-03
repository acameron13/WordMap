//Changeling Class

import java.util.*;
import java.util.concurrent.*;
import java.io.FileNotFoundException;

public class Changeling{

   public Map<String, Integer> level = new HashMap<String,Integer>();
   public Map<String, String> parentMap = new HashMap<String,String>();
   
   public Integer getIdx(String s, String[] wordsList){
   
      for(Integer idx = 0; idx < wordsList.length; idx++){
         if (wordsList[idx].equals(s)){
            return idx;
         }
      }
      return -1;
   }
   
   public void BFS(String s, LinkedList[] Adj, Map<Integer, String> words, String[] wordsList){
      
      level.put(s, 0);
      parentMap.put(s, "---");
      ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
      Integer idx = getIdx(s, wordsList);
      queue.offer(idx);
      Integer a;
      LinkedList<String> b;
      String c;
      while(!queue.isEmpty()){
         a = queue.poll();
         b = Adj[a];
         for (int i = 0; i < b.size(); i++){
            c = b.get(i);
            if (!level.containsKey(c)){
               level.put(c, level.get(words.get(a)) + 1);
               parentMap.put(c, words.get(a));
               queue.offer(getIdx(c, wordsList));
            }
         }
      }
   }
   
   public static void main(String[] args) throws FileNotFoundException{
      String fileName = args[0];
      String word1 = args[1];
      String word2 = args[2];
      
      Dictionary dict = new Dictionary();
      dict.readFile(fileName);
      dict.createAdjList();
      Map<Integer, String> words = dict.words;
      String[] wordsList = dict.wordsList;
      LinkedList[] Adj = dict.Adj;
      
      Scanner scanner = new Scanner(System.in);
      while(!words.containsValue(word1)){
         System.out.println("Word 1 is not in the dictionary. Please enter a new word: ");
         word1 = scanner.next();
      } 
      while(!words.containsValue(word2)){
         System.out.println("Word 2 is not in the dictionary. Please enter a new word: ");
         word2 = scanner.next();
      } 
           
      Changeling change = new Changeling();
      change.BFS(word1, Adj, words, wordsList);

      Stack<String> stack = new Stack<String>();
      
      String child = word2;
      String parentWord = word2;
      while(!parentWord.equals(word1)){
         parentWord = change.parentMap.get(child);
         stack.push(parentWord);
         child = parentWord;
      }
      
      StringBuilder path = new StringBuilder();
      
      while(!stack.empty()){
         path.append(stack.pop() + ", ");
      }
      path.append(word2);
      
      System.out.println(path);
   }

}