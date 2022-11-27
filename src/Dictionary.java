import java.util.*;


public class Dictionary {
    private Map<String, ArrayList<String>> slangList;
    private final Map<String, ArrayList<String>> originalList;
    private ArrayList<String> historyList;

    // Constructor doc file va luu vao list
    Dictionary(String fileDir, String originalFileDir){
        this.slangList = FileHandler.readFile(fileDir);
        this.originalList = FileHandler.readFile(originalFileDir);
        this.historyList = new ArrayList<>();
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
            historyList.add(slangWord);

        return resultList;
    }

    void showHistory(){
        if(!historyList.isEmpty()){
            int i = 1;

            for(String word : historyList)
                System.out.println(i++ + ".   " + word);
        }
        else {
            System.out.println("Empty Search History");
        }
    }

    private int enterChoice(){
        int result;
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


    // edit a slang
    boolean editSlangWord(String word){
        if(!slangList.containsKey(word))
            return false;

        int choice = 2;
        Scanner inputScanner = new Scanner(System.in);
        ArrayList<String> tmpDefList = slangList.get(word);

        // Edit slang word
        if(choice == 1){
            String newWord;
            int continueEnterWord = 1;

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
                else
                    break;
            } while (continueEnterWord == 1);

            if(continueEnterWord == 1){
                slangList.remove(word);
                slangList.put(newWord, tmpDefList);
            }
        }
        else if(choice == 2){        // edit slang definition
            String tmpString;
            String []editDefIndex;

            System.out.println("Definitions of this slang word: " + tmpDefList.size());
            // Show def list cho ngdung
            showDefInOder(tmpDefList);

            System.out.print("Enter the definitions you want to edit (separate by \",\"): ");
            tmpString = inputScanner.nextLine();
            editDefIndex = tmpString.split(",");

            for (String i : editDefIndex) {
                int index = Integer.parseInt(i) - 1;
                System.out.print("Enter new definition for \""
                        + tmpDefList.get(index)
                        + "\": ");
                tmpString = inputScanner.nextLine();

                tmpDefList.set(index, tmpString);
            }

            slangList.put(word, tmpDefList);
        }

        return true;
    }

    // remove slang word
    boolean removeSlangWord(String word){
        if(!slangList.containsKey(word))
            return false;
        int choice = 1;
        System.out.print("Delete word ? 1-yes 0-no\nYOUR CHOICE: ");
        choice = (new Scanner(System.in)).nextInt();

        if(choice == 1){
            slangList.remove(word);

        }

        return true;
    }

    // reset original slang list
     void resetList(String fileDir){
        this.slangList = this.originalList;
    }

    // Show Definition of a slang word
    private void showDefInOder(List<String> defList){
        int i = 1;

        for(String s : defList)
            System.out.println(i++ + ".  " + s);
    }

    private void showDefInOrder(String word){
        List<String> tmpDefList = slangList.get(word);
        int i = 1;

        for(String s : tmpDefList)
            System.out.println(i++ + ".  " + s);

    }

    private String randomKey(){
        List<String> keySetList= new ArrayList<>(this.slangList.keySet());
        return keySetList.get((new Random()).nextInt(this.slangList.size()));
    }

    void randomSlangWord(){
        String key = randomKey();

        System.out.println("Slang word: " + key + " - definition: " + this.slangList.get(key));
    }

    void quizSlang(){
        String answerKey = randomKey(), answerDefinition;
        String []multipleChoiceDefinition = new String[3];
        String []multipleChoiceAnswer = new String[4];

        int n = this.slangList.get(answerKey).size()
                , answerIndex = (new Random()).nextInt(4), j = 0, choice = -1;

        answerDefinition = this.slangList.get(answerKey).get((new Random()).nextInt(n));
        for(int i = 0; i < 3; ++i)
            multipleChoiceDefinition[i] = this.slangList.get(randomKey()).get(0);


        multipleChoiceAnswer[answerIndex] = answerDefinition;
        for(int i = 0; i < 4; ++i){
            if(i == answerIndex)
                continue;
            multipleChoiceAnswer[i] = multipleChoiceDefinition[j];
            ++j;
        }

        System.out.println("Slang word: " + answerKey );

        for(int i = 0; i < 4; ++i){
            System.out.println((i + 1) + ". " + multipleChoiceAnswer[i]);
        }

        System.out.print("YOUR CHOICE: ");
        choice = (new Scanner(System.in)).nextInt();

        if(choice - 1 == answerIndex)
            System.out.println("!!! CORRECT !!!");
        else
            System.out.println("!!! INCORRECT !!!");
    }
//    void testWrite(){
//        FileHandler.writeFile("src\\data\\slang_test.txt", this.slangList);
//    }
}
