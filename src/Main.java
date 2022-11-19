import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Dictionary dic = new Dictionary("src\\data\\slang.txt");

        dic.searchBySlangWord("AMA");
        dic.searchBySlangWord("$");

//        dic.showHistory();

//        dic.addSlangWord("AMA");

        dic.editSlangWord("AMA");

        System.out.println(dic.searchBySlangWord("TGT"));
        System.out.println(dic.searchBySlangWord("AMA"));
    }
}
