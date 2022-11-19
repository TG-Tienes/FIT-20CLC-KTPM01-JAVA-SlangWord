public class Main {
    public static void main(String[] args) {
        Dictionary dic = new Dictionary("src\\data\\slang.txt");

        dic.searchBySlangWord("AMA");
        dic.searchBySlangWord("$");
        dic.showHistory();
    }
}
