import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Dictionary dic = new Dictionary("src\\data\\slang.txt", "src\\data\\original_slang.txt");

//        dic.searchBySlangWord("AMA");
//        dic.searchBySlangWord("$");

//        dic.showHistory();

//        dic.addSlangWord("AMA");

//        dic.editSlangWord("$");

//        System.out.println(dic.searchBySlangWord("TGT"));
//        dic.testWrite();
//        System.out.println(dic.searchBySlangWord("$"));
            dic.quizDefinition();
    }
}
