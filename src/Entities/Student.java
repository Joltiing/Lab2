package Entities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Student {
    public String _name;

    public HashMap<String, Integer> _results;

    public Student(String name, HashMap<String, Integer> results) throws Exception {
        if(name.isEmpty()){
            throw new Exception("Empty name");
        }

        if(results.isEmpty()){
            throw new Exception("Empty results");
        }

        _name = name;
        _results = results;
    }

    public String SendMessage(Faculty from){
        Map<String, Integer> filteredResults = new HashMap<>();
        var scoreSum = 0;
        for (var result : _results.keySet()) {
            if(Arrays.asList(from._disciplines).contains(result)){
                filteredResults.put(result, _results.get(result));
                scoreSum+=_results.get(result);
            }
        }
        var answer="";
        if(scoreSum>=from._passScore){
            answer = String.format("Congratulations! You reached pass score to out great \"%s\" faculty\n" +
                    "Your results:\n",from._name);

            for (var result : filteredResults.keySet()) {
                answer+=String.format("%s : %d\n",result,filteredResults.get(result));
            }
        }

        if(scoreSum<from._passScore){
            answer = String.format("Congratulations! You didn't reach pass score to out great \"%s\" faculty\n" +
                    "Your results:\n",from._name);

            for (var result : filteredResults.keySet()) {
                answer+=String.format("%s : %d\n",result,filteredResults.get(result));
            }
        }

        return answer;
    }
}
