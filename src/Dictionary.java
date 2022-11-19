import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
}
