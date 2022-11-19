import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Dictionary {
    private final Map<String, ArrayList<String>> slangList;
    private final Map<String, ArrayList<String>> originalList;
    private ArrayList<String> searchHistoryList;

    // Constructor doc file va luu vao list
    Dictionary(String fileDir){
        this.slangList = FileHandler.readFile(fileDir);
        this.originalList = this.slangList;
        searchHistoryList = new ArrayList<>();
    }

    List<String> searchByDefinition(String word){
        // chuyen tu khoa can tim thanh lower-case
        String finalWord = word.toLowerCase();
        List<String> resultList = new ArrayList<>();

        for(String i : slangList.keySet()){
            // Chuyen tat ca element trong list thanh lower-case
            List<String> tmpList = slangList.get(i)
                    .stream()
                    .map(String::toLowerCase).toList();

            // Tim tu khoa(sub-string) trong List
            if(tmpList.stream().anyMatch(e -> e.contains(finalWord)))
                resultList.add(i);
        }

        return resultList;
    }

    List<String> searchBySlangWord(String slangWord){
        // chuyen tu khoa can tim thanh lower-case
        List<String> resultList = slangList.get(slangWord);

        if(resultList != null)
            searchHistoryList.add(slangWord);

        return resultList;
    }

    void showHistory(){
        if(!searchHistoryList.isEmpty()){
            int i = 1;

            for(String word : searchHistoryList)
                System.out.println(i++ + ".   " + word);
        }
        else {
            System.out.println("Empty Search History");
        }
    }

    private int enterChoice(){
        int result = -1;
        Scanner inputScanner = new Scanner(System.in);

        System.out.print("Enter your choice: ");

        result = inputScanner.nextInt();

        return result;
    }

    private int addWord(String word, ArrayList<String> defList){
        if(slangList.containsKey(word)){
            int choice = enterChoice();

            // 0: overwrite definition (make new def)
            // 1: add more definitions
            // 3: Exit and do nothing
            if(choice == 3)
                return choice;
            if(choice == 1)
                defList.addAll(slangList.get(word));
            slangList.replace(word, defList);

            return choice;
        }
        else{
            slangList.put(word, defList);
        }
        return 2;
    }

    int addSlangWord(String word){
        ArrayList<String> defList = inputSlangDef();

        return addWord(word, defList);
    }

    private ArrayList<String> inputSlangDef(){
        ArrayList<String> resList = new ArrayList<>();
        Scanner inputScanner = new Scanner(System.in);
        String tmpString;
        int numOfDef = 0;

        System.out.print("Enter the number of definition: ");
        numOfDef = inputScanner.nextInt();
        inputScanner.nextLine();

        for(int i = 0; i < numOfDef; ++i){
            System.out.print("Enter definition: ");
            tmpString = inputScanner.nextLine();

            resList.add(tmpString);
        }
        return resList;
    }


    //
    boolean editSlangWord(String word){
        if(!slangList.containsKey(word))
            return false;

        int choice = 1, n = slangList.get(word).size();
        Scanner inputScanner = new Scanner(System.in);
        ArrayList<String> tmpDefList = slangList.get(word);

        // Edit slang word
        if(choice == 1){
            String newWord;
            int continueEnterWord = 0;
            slangList.remove(word);

            do{
                System.out.print("Edit slang word: ");
                newWord = inputScanner.nextLine();

                if(slangList.containsKey(newWord)){
                    System.out.println("!!! Slang word existed !!!");
                    System.out.print("1.Enter Again\n2-Exit\nYOUR CHOICE: ");
                    continueEnterWord = inputScanner.nextInt();
                    System.out.println();

                    if(continueEnterWord == 2)
                        break;
                }
            } while (continueEnterWord == 1);

            if(continueEnterWord == 1)
                slangList.put(newWord, tmpDefList);
        }
        // edit slang definition
        else if(choice == 2){
            String tmpString;


            for(int i = 0; i < n; ++i){
                System.out.print("Enter new definition for \"" + tmpDefList.get(i) + "\" (enter \"skipThis\" to skip: ");
                tmpString = inputScanner.nextLine();

                if(tmpString.compareTo("skipThis") == 0)
                    continue;

                tmpDefList.add(i, tmpString);
            }
            slangList.replace(word, tmpDefList);
        }

        return true;
    }

    // Show Definition of a slang word
    private void showDefInOder(List<String> defList){
        int i = 1;

        for(String s : defList)
            System.out.println(i + ".  " + defList.get(i));
    }

    private void showDefInOrder(String word){
        List<String> tmpDefList = slangList.get(word);
        int i = 1;

        for(String s : tmpDefList)
            System.out.println(i + ".  " + tmpDefList.get(i));
    }
}
