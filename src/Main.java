import java.util.*;

public class Main {
    static void menu(){
        String slangDir = "src\\data\\slang.txt"
                , originalDir = "src\\data\\original_slang.txt"
                , historyDir = "src\\data\\history.txt";
        Dictionary dic = new Dictionary(slangDir, originalDir, historyDir);
        int featureChoice, innerChoice;

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
                        System.out.println("""
                        -------------------------------
                        ||   SLANG WORD NOT EXIST    ||
                        -------------------------------
                        """);
                    }

                    System.out.println("\nCONTINUE ? ");
                    printYesNo();
                    System.out.print("YOUR CHOICE: ");
                    innerChoice = (new Scanner(System.in)).nextInt();
                }while (innerChoice != 0);

                dic.writeHistory("src\\data\\history.txt");
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
                        System.out.println("""
                        -------------------------------
                        ||   SLANG WORD NOT EXIST    ||
                        -------------------------------
                        """);
                    }

                    System.out.println("\nCONTINUE ? ");
                    printYesNo();
                    System.out.print("YOUR CHOICE: ");
                    innerChoice = (new Scanner(System.in)).nextInt();
                }while (innerChoice != 0);

            }
            else if(featureChoice == 3){
                clearConsole();
                System.out.println("----- SEARCH HISTORY -----\n");
                dic.showHistory();
                pressToContinue();
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

                dic.writeFile("src\\data\\slang.txt");

            }
            else if(featureChoice == 5){
                do{
                    clearConsole();
                    System.out.println("----- Edit Slang word -----");
                    System.out.print("Enter slang word: ");

                    String word = (new Scanner(System.in)).nextLine();
                    dic.editSlangWord(word);

                    System.out.println("\nCONTINUE ? ");
                    printYesNo();
                    System.out.print("YOUR CHOICE: ");
                    innerChoice = (new Scanner(System.in)).nextInt();
                }while (innerChoice != 0);

                dic.writeFile("src\\data\\slang.txt");
            }
            else if(featureChoice == 6){
                do{
                    clearConsole();
                    System.out.println("----- Delete Slang word -----");
                    System.out.print("Enter slang word: ");

                    String word = (new Scanner(System.in)).nextLine();
                    int tmp = dic.removeSlangWord(word);
                    if(tmp == 1)
                        System.out.println("""
                        ---------------------------------------
                        ||   DELETE SLANG WORD SUCCESSFUL    ||
                        ---------------------------------------
                        """);
                    else if(tmp == 0)
                        System.out.println("""
                        -----------------------------------------
                        ||   DELETE SLANG WORD UNSUCCESSFUL    ||
                        -----------------------------------------
                        """);
                    else
                        System.out.println("""
                        -------------------------------
                        ||   SLANG WORD NOT EXIST    ||
                        -------------------------------
                        """);


                    System.out.println("\nCONTINUE ? ");
                    printYesNo();
                    System.out.print("YOUR CHOICE: ");
                    innerChoice = (new Scanner(System.in)).nextInt();
                }while (innerChoice != 0);

                dic.writeFile("src\\data\\slang.txt");
            }
            else if(featureChoice == 7){
                clearConsole();
                System.out.println("----- RESET ORIGINAL SLANG LIST -----");

                dic.resetList();

                System.out.println("""
                        ----------------------------------------------------
                        ||   RESET ORIGINAL SLANG WORD LIST SUCCESSFUL    ||
                        ----------------------------------------------------
                        """);
                dic.writeFile(slangDir);

                pressToContinue();
            }
            else if(featureChoice == 8){
                do{
                    clearConsole();
                    System.out.println("----- RANDOM (ON THIS DAY SLANG WORD) -----");

                    dic.randomSlangWord();

                    System.out.print("\nCONTINUE ? ");
                    printYesNo();
                    System.out.print("YOUR CHOICE: ");
                    innerChoice = (new Scanner(System.in)).nextInt();
                }while (innerChoice != 0);
            }
            else if(featureChoice == 9){
                do{
                    clearConsole();
                    System.out.println("----- QUIZ SLANG WORD -----");

                    dic.quizSlang();

                    System.out.print("\nCONTINUE ? ");
                    printYesNo();
                    System.out.print("YOUR CHOICE: ");
                    innerChoice = (new Scanner(System.in)).nextInt();
                }while (innerChoice != 0);
            }
            else if(featureChoice == 10){
                do{
                    clearConsole();
                    System.out.println("----- QUIZ DEFINITION -----");

                    dic.quizDefinition();

                    System.out.print("\nCONTINUE ? ");
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
                    |  6. Delete slang word                                        |
                    |  7. Reset Slang word list                                    |
                    |  8. Random slang word (On this day slang word)               |
                    |  9. Quiz random slang word                                   |
                    | 10. Quiz random Definition                                   |
                    |                                                              |
                    |  0. EXIT                                                     |
                    ================================================================""");
    }

    static void printYesNo(){
        System.out.println("""
                \nChoose
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
        menu();
    }
}
