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

    static void writeFile(String fileDir, Map<String, ArrayList<String>> slangList){
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(fileDir));

            for(String key : slangList.keySet()){
                ArrayList<String> defList = slangList.get(key);
                StringBuilder sb = new StringBuilder(key);
                int n = defList.size();

                sb.append("`");

                for(int i = 0; i < n - 1; ++i){
                    sb.append(defList.get(i));
                    sb.append("|");
                }
                sb.append(defList.get(n - 1));
                sb.append("\n");
                fw.write(sb.toString());
            }

        } catch (Exception e){
            System.out.println(e);
        }
    }
}
