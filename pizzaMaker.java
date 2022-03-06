import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class to read text files
import java.text.ParsePosition;


public class pizzaMaker {
    public static void main(String[] args){
        File a = new File("src/d_difficult.in.txt");
        Scanner reader = null;
        try {
            reader = new Scanner(a);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int rows = Integer.parseInt(reader.nextLine());
        rows = rows * 2;
        
        int lengthOfIngredients[] = new int[rows] ;
        String[][] mainData = new String[rows][5];
        for(int i = 0; i < rows; i++) {
            lengthOfIngredients[i] = reader.nextInt();
            for (int j = 0; j < lengthOfIngredients[i]; j++) {
                mainData[i][j] = reader.next();
                System.out.print(mainData[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println(Arrays.toString(lengthOfIngredients));
        reader.close();
        
        
        // Finished parsing
        
        String finalPizza = "";
        String inConflict[] = new String[1000000];
        String currentPizza[] = new String[10000];
        int totalDoWant = 0;
        int totalDontWant = 0;
        
        int count = 0;
        int conflictCount = 0;
        int currentPizzaCount = 0;
        
        for (int i = 0; i < rows; i = i+2) {
            for (int j = 0; j < lengthOfIngredients[i]; j++) {
                boolean insertThisIngredient = true;
                int dontWantIt = 0;
                int doWantIt = 0;
                for (int k = 1; k < rows; k = k+2) {
                    for (int l = 0; l < lengthOfIngredients[i]; l++) {
                        if (mainData[i][j].equals(mainData[k][l])){ 
                            inConflict[conflictCount] = mainData[i][j];
                            conflictCount++;
                            dontWantIt++;
                            insertThisIngredient = false;
//                            System.out.println(" Bruh get this shit out" + mainData[i][j]);   
                        }
                    }
                }
                for (int x = 0; x < rows; x = x+2) {
                    for (int y = 0; y < lengthOfIngredients[x]; y++) {
                    	if (mainData[i][j].equals(mainData[x][y])) {
                    		doWantIt++;
                    	}
                    	
                    	
                    }
                }
                totalDoWant += doWantIt;
                totalDontWant += dontWantIt;
                for (int ing = 0; ing < conflictCount; ing++) {
                    if(mainData[i][j].equals(inConflict[ing])) {
                        insertThisIngredient = false;
                    }
                }
                if (dontWantIt  < doWantIt*0.759) {
                	insertThisIngredient = true;
                	
                }
                for (int b = 0; b < currentPizzaCount; b++) {
                    if(mainData[i][j].equals(currentPizza[b])) {
                        insertThisIngredient = false;
                    }
                }    
                if (insertThisIngredient) {                        
                    currentPizza[currentPizzaCount] = mainData[i][j];
                    currentPizzaCount++;
                    finalPizza = finalPizza + mainData[i][j] + " ";
                    count++;
                }
            }            
        }
        finalPizza = count +" "+ finalPizza;
        System.out.println(finalPizza);
        System.out.println(totalDontWant + " - Dont want it");
        System.out.println(totalDoWant + "- Do want it");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("src/output.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        writer.println(finalPizza);
//        writer.println("The second line");
        writer.close();
        
    }
        
}