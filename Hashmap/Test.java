import java.io.*;
import java.util.*;

class Test{
      
   public static void main(String [] args){
   
      HashMap<String, HashMap> map = new HashMap<>();
      try{
         //String useFile = "test.txt";
         String useFile = "words_alpha.txt";
         File file = new File(useFile); 
         BufferedReader br = new BufferedReader(new FileReader(file)); 
         String word;
         int count = 0; 
         while ((word = br.readLine()) != null){
            count += 1;
            String temp[] = word.split("");
            HashMap<String, HashMap> map1 = map;
            //map1 = map;
            for(int i = 0; i<temp.length; i++){
               if(map1.containsKey(temp[i])){
                  map1 = map1.get(temp[i]);
               }
               else{
                  map1.put(temp[i], new HashMap());
               }
            }
            map1.put(null, new HashMap());
         }
         System.out.println("Number of words: " + count);
         System.out.print("Enter one letter at a time, and this will print all the next avalible letters, 'end' will exit program: ");
         Scanner sc = new Scanner(System.in);
         String letter = sc.nextLine();
         while(!letter.equals("end")){
            map = map.get(letter);
            if(!map.isEmpty()){
               System.out.println(map.keySet());
               System.out.print("Enter next letter , and this will print all the next avalible letters, 'end' will exit program: ");
               letter = sc.nextLine();
            }
            else{
               System.out.print("No more letters to make words");
               System.exit(0);
            }
         }
      }
      catch(Exception e){
         System.out.print("Not a valid word");
      }
   }
   
   public void addTo(String temp[], HashMap map1){
      
   }
}