import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Dictionary dic = new Dictionary("src\\data\\slang.txt");

        dic.searchBySlangWord("AMA");
        dic.searchBySlangWord("$");

        ArrayList<String> aaa = new ArrayList<>();

        aaa.add("Truong Gia Tien");
        aaa.add("Nguyen Dinh Van");


//        dic.showHistory();

        dic.addSlangWord("AMA");

        System.out.println(dic.searchBySlangWord("AMA"));
    }
}
