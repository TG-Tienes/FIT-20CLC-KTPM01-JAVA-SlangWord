import java.io.IOException;
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
                    System.out.println("----- Search by Slang word -----");
                    System.out.print("Enter slang word: ");

                    String word = (new Scanner(System.in)).nextLine();
                    List<String> l = dic.searchBySlangWord(word);

                    if(l != null){
                        System.out.println("\nSlang's Definition(s): ");
                        showList(l);
                    }
                    else {
                        System.out.println("!!! Slang word not exist !!!");
                    }

                    System.out.println("\nCONTINUE ? ");
                    printYesNo();
                    System.out.print("YOUR CHOICE: ");
                    innerChoice = (new Scanner(System.in)).nextInt();
                }while (innerChoice != 0);
            }
            else if(featureChoice == 2){
                do{
                    clearConsole();
                    System.out.println("----- Search by Definition -----");
                    System.out.print("Enter keyword: ");

                    String word = (new Scanner(System.in)).nextLine();
                    List<String> l = dic.searchByDefinition(word);

                    if(!l.isEmpty()){
                        System.out.println("\nSlang word(s): ");
                        showList(l);
                    }
                    else {
                        System.out.println("!!! Slang word not exist !!!");
                    }

                    System.out.println("\nCONTINUE ? ");
                    printYesNo();
                    System.out.print("YOUR CHOICE: ");
                    innerChoice = (new Scanner(System.in)).nextInt();
                }while (innerChoice != 0);
            }
            else if(featureChoice == 3){

            }
            else if(featureChoice == 4){
                do{
                    clearConsole();
                    System.out.println("----- Add new Slang word -----");
                    System.out.print("Enter slang word: ");

                    String word = (new Scanner(System.in)).nextLine();
                    dic.addSlangWord(word);

                    System.out.println("\nCONTINUE ? ");
                    printYesNo();
                    System.out.print("YOUR CHOICE: ");
                    innerChoice = (new Scanner(System.in)).nextInt();
                }while (innerChoice != 0);
            }

            clearConsole();
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

    static void printYesNo(){
        int choice = -1;

        System.out.println("""
                ==========
                | 1. Yes |
                | 0. No  |
                ==========""");
    }
    //
    static void showList(List<String> l){
        int i = 1;
        for(String s : l)
            System.out.println(i++ + ". " + s);
    }

    static void clearConsole(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        catch (Exception e){
            System.out.println("Exception 101: Clear console failed");
        }
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
