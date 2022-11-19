package FileHandler;

import java.io.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileHandler {
    static Map<String, ArrayList<String>> readFile(String fileDir){
        Map<String, ArrayList<String>> slangList = new HashMap<>();

        try {
            String line;
            String []tempPair, tempSet = null;

            BufferedReader fr = new BufferedReader(new FileReader(fileDir));

            // Bo qua dong dau tien
            line = fr.readLine();
            while((line = fr.readLine()) != null){
                ArrayList<String> tmpList = new ArrayList<>();

                tempPair = line.split("`");
                tempPair[0] = tempPair[0].trim();


                if(slangList.containsKey(tempPair[0]))
                    tmpList = slangList.get(tempPair[0]);

                // Kiem tra tu nhieu nghia
                if (tempPair[1].contains("|")){
                    tempSet = tempPair[1].split("\\|");

                    for(String i : tempSet){
                        tmpList.add(i.trim());
                    }
                }
                else
                    tmpList.add(tempPair[1]);
                slangList.put(tempPair[0], tmpList);
            }
            fr.close();
        }
        catch (Exception e){
            System.out.println("Error reading file");
        }

        return slangList;
    }

    public static void main(String[] args) {
        Map<String, ArrayList<String>> tmp = readFile("src\\data\\slang.txt");

        System.out.println(tmp.get("AA"));
    }


}
