import java.util.*;

public class Main {
    static void menu(){
        Dictionary dic = new Dictionary("src\\data\\slang.txt", "src\\data\\original_slang.txt");
        int featureChoice = -1, innerChoice = -1;

        do {
            showOption();

            System.out.print("YOUR CHOICE: ");
            featureChoice = (new Scanner(System.in)).nextInt();

            if(featureChoice == 1){
                do{
                    clearConsole();
                    System.out.println("                ----- Search by Slang word -----");
                    System.out.print("Enter slang word: ");

                    String word = (new Scanner(System.in)).nextLine();


                    System.out.println("Slang's Definition(s): ");
                    showList(dic.searchBySlangWord(word));


//                    pressToContinue();
                }while (innerChoice != 0);
            }
            else if(featureChoice == 2){
            }

        }while (featureChoice != 0);
    }


    static void showOption(){
        System.out.println("""
                                    ----- SLANG WORD DICTIONARY -----
                    ================================================================
                    |  1. Search by Slang Word                                     |
                    |  2. Search by Definition                                     |
                    |  3. Show search history                                      |
                    |  4. Add new slang word                                       |
                    |  5. Edit slang word                                          |
                    |  7. Reset Slang word list                                    |
                    |  8. Random slang word (On this day slang word)               |
                    |  9. Quiz random slang word                                   |
                    | 10. Quiz random Definition                                   |
                    |                                                              |
                    |  0. EXIT                                                     |
                    ================================================================""");
    }

    //
    static void showList(List<String> l){
        int i = 1;
        for(String s : l)
            System.out.println(i++ + ". " + s);
    }
    static void clearConsole(){
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    static void pressToContinue(){

        System.out.println("Press Enter to continue !");
        try{
            System.in.read();
        }catch (Exception e){
           System.out.println("Exception: Press Enter");
        }
    }

    public static void main(String[] args) {
//        Dictionary dic = new Dictionary("src\\data\\slang.txt", "src\\data\\original_slang.txt");

//        dic.searchBySlangWord("AMA");
//        dic.searchBySlangWord("$");

//        dic.showHistory();

//        dic.addSlangWord("AMA");

//        dic.editSlangWord("$");

//        System.out.println(dic.searchBySlangWord("TGT"));
//        dic.testWrite();
//        System.out.println(dic.searchBySlangWord("$"));
//            dic.quizDefinition();

//        pressToContinue();
//        showOption();

        menu();
    }
}
