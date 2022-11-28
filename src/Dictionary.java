import java.util.*;


public class Dictionary {
    private Map<String, ArrayList<String>> slangList;
    private final Map<String, ArrayList<String>> originalList;
    private ArrayList<String> historyList;

    private int streak, bestStreak;

    // Constructor doc file va luu vao list
    Dictionary(String fileDir, String originalFileDir, String historyFileDir){
        this.slangList = FileHandler.readFile(fileDir);
        this.originalList = FileHandler.readFile(originalFileDir);
        this.historyList = FileHandler.readHistory(historyFileDir);

        streak = 0;
        bestStreak = 0;
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
            if(tmpList.stream().allMatch(e -> e.contains(finalWord)))
//            for(String s : tmpList)
//                if (s.contains(finalWord)){
                    resultList.add(i + "` " + slangList.get(i));
//                    break;
//                }
        }

        return resultList;
    }

    List<String> searchBySlangWord(String slangWord){
        List<String> resultList = slangList.get(slangWord);

        if(resultList != null)
            historyList.add(slangWord);

        return resultList;
    }

    void showHistory(){
        clearConsole();

        if(!historyList.isEmpty()){
            int i = 1;

            for(String word : historyList)
                System.out.println(i++ + ".   " + word);
            System.out.println();
        }
        else {
            System.out.println("""
                        -------------------------------
                        ||   EMPTY SEARCH HISTORY    ||
                        -------------------------------
                        """);
        }
    }

    void addSlangWord(String word){
        ArrayList<String> defList = inputSlangDef();
        int choice;

       clearConsole();

        if(slangList.containsKey(word)){

            // 1: add more definitions
            // else: overwrite definition (make new def)
            System.out.println("""
                          !!! Slang word Existed !!!
                                    ------
                    Choose
                    ======================================
                    | 1. Duplicate (Add more definitions)|
                    | 2. Overwrite                       |
                    ======================================""");
            System.out.print("YOUR CHOICE: ");
            choice = (new Scanner(System.in)).nextInt();

            if(choice == 1){
                defList.addAll(slangList.get(word));
                System.out.println("""
                        ------------------------------
                        ||   DUPLICATE Successful   ||
                        ------------------------------
                        """);
            }
            else {
                System.out.println("""
                        ------------------------------
                        ||   OVERWRITE Successful   ||
                        ------------------------------
                        """);
            }

            slangList.replace(word, defList);
        }
        else{
            slangList.put(word, defList);
            System.out.println("""
                        -----------------------------------
                        ||   ADD SLANG WORD SUCCESSFUL   ||
                        -----------------------------------
                        """);
        }
    }

    private ArrayList<String> inputSlangDef(){
        ArrayList<String> resList = new ArrayList<>();
        Scanner inputScanner = new Scanner(System.in);
        String tmpString;
        int numOfDef;

        System.out.println("------ Enter Definition(s) ------");
        System.out.print("Enter the number of definition (integer): ");
        numOfDef = inputScanner.nextInt();
        inputScanner.nextLine();

        System.out.println("-----------------------------------------");
        for(int i = 0; i < numOfDef; ++i){
            System.out.print("Enter definition: ");
            tmpString = inputScanner.nextLine();

            resList.add(tmpString);
        }
        return resList;
    }


    // edit a slang
    void editSlangWord(String word){
        if(!slangList.containsKey(word)){
            System.out.println("""
                        -------------------------------
                        ||   SLANG WORD NOT EXIST    ||
                        -------------------------------
                        """);
            return;
        }


        System.out.println("""
                \nChoose
                ================================
                | 1. Edit slang word           |
                | 2. Edit slang Definition(s)  |
                ================================""");
        System.out.print("YOUR CHOICE: ");

        int choice = (new Scanner(System.in)).nextInt();
        Scanner inputScanner = new Scanner(System.in);
        ArrayList<String> tmpDefList = slangList.get(word);

        // Edit slang word
        if(choice == 1){
            String newWord;
            int continueEnterWord = 1;

            clearConsole();

            do{
                System.out.println("----- YOU CHOSE EDIT SLANG WORD -----");

                System.out.println("\nOld Slang Word: " + word + "\n-----------------");

                System.out.print("Enter new slang word: ");
                newWord = inputScanner.nextLine();

                if(slangList.containsKey(newWord)){
                    System.out.println("""
                        ---------------------------
                        ||   SLANG WORD EXIST    ||
                        ---------------------------
                        please enter again or exit
                        """);

                    System.out.println("""
                                        \nChoose
                                        =======================
                                        | 1. Enter again      |
                                        | 2. Exit (no edit)   |
                                        =======================""");
                    System.out.print("YOUR CHOICE: ");

                    continueEnterWord = inputScanner.nextInt();
                    inputScanner.nextLine();
                    System.out.println();

                    if(continueEnterWord == 2){
                        System.out.println("""
                        ----------------------------
                        ||   EXIT WITHOUT EDIT    ||
                        ----------------------------
                        """);
                        break;
                    }

                    clearConsole();
                }
                else
                    break;

            } while (continueEnterWord == 1);

            if(continueEnterWord == 1){
                slangList.remove(word);
                slangList.put(newWord, tmpDefList);

                System.out.println("""
                        -------------------------------------
                        ||   EDIT SLANG WORD SUCCESSFUL    ||
                        -------------------------------------
                        """);
            }
        }
        else if(choice == 2){        // edit slang definition
            String tmpString;
            String []editDefIndex;

            clearConsole();
            System.out.println("----- YOU CHOSE EDIT SLANG DEFINITION(S) -----");
            System.out.println("\nThis Slang Word: " + word + "\n-----------------");
            System.out.println("\nDefinitions of this slang word: " + tmpDefList.size());
            // Show def list cho nguoi dung
            showDefInOder(tmpDefList);

            System.out.print("\nEnter the definitions you want to edit (separate by \",\"): ");
            tmpString = inputScanner.nextLine();
            editDefIndex = tmpString.split(",");

            System.out.println();
            for (String i : editDefIndex) {
                int index = Integer.parseInt(i.trim()) - 1;
                System.out.print("Enter new definition for \""
                        + tmpDefList.get(index)
                        + "\": ");
                tmpString = inputScanner.nextLine();

                tmpDefList.set(index, tmpString);
            }

            slangList.put(word, tmpDefList);
        }
    }

    // remove slang word
    int removeSlangWord(String word){
        if(!slangList.containsKey(word))
            return -1;
        int choice;

        System.out.println("""
                \nDelete word ?
                Choose
                ==========
                | 1. Yes |
                | 0. No  |
                ==========""");
        System.out.print("YOUR CHOICE: ");
        choice = (new Scanner(System.in)).nextInt();

        if(choice == 1){
            slangList.remove(word);
        }

        return choice;
    }

    // reset original slang list
     void resetList(){
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
                , answerIndex = (new Random()).nextInt(4), j = 0
                , choice = -1;

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


        System.out.println("Streak: " + streak);
        System.out.println("Best Streak: " + bestStreak);


        System.out.println("\n----------------------------\nSlang word: " + answerKey +"\n");
        for(int i = 0; i < 4; ++i){
            System.out.println((i + 1) + ". " + multipleChoiceAnswer[i]);
        }

        System.out.print("\nYOUR CHOICE: ");
        choice = (new Scanner(System.in)).nextInt();


        if(choice - 1 == answerIndex){
            System.out.println("""
                        ------------------
                        ||   CORRECT    ||
                        ------------------
                        """);
            streak++;
            if(this.streak > this.bestStreak)
                this.bestStreak = this.streak;
        }

        else{
            System.out.println("""
                        ----------------
                        ||   WRONG    ||
                        ----------------
                        """);
            streak = 0;
        }


    }

    void quizDefinition(){
        String answerKey = randomKey(), answerDefinition;
        String []multipleChoiceAnswer = new String[4];

        int n = this.slangList.get(answerKey).size()
                , answerIndex = (new Random()).nextInt(4), choice = -1;

        answerDefinition = this.slangList.get(answerKey).get((new Random()).nextInt(n));

        multipleChoiceAnswer[answerIndex] = answerKey;
        for(int i = 0; i < 4; ++i){
            if(i == answerIndex)
                continue;
            multipleChoiceAnswer[i] = randomKey();
        }

        System.out.println("Definition: " + answerDefinition);

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

    private void clearConsole(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        catch (Exception e){
            System.out.println("Exception 101: Clear console failed");
        }
    }

    void writeFile(String fileDir){
        FileHandler.writeFile(fileDir, this.slangList);
    }
    void writeHistory(String fileDir){
        FileHandler.writeHistory(fileDir, this.historyList);
    }
}
