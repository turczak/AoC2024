package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day1 {
    private List<Integer> leftList;
    private List<Integer> rightList;
    private final File file = new File("src/main/resources/day1.txt");
    private Scanner scanner;
    private HashMap<Integer, Integer> map;

    public Day1() throws FileNotFoundException {
        this.scanner = new Scanner(file);
        this.leftList = new ArrayList<>();
        this.rightList = new ArrayList<>();
        getInput();
        this.map = new HashMap<>();
    }

    private void getInput() {
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split("   ");
            Integer left = Integer.parseInt(line[0]);
            Integer right = Integer.parseInt(line[1]);
            leftList.add(left);
            rightList.add(right);
        }
        Collections.sort(leftList);
        Collections.sort(rightList);
    }

    private Integer calculateDistanceInPair(Integer left, Integer right) {
        return Math.abs(left - right);
    }

    private Integer calculateTotalDistance(List<Integer> leftList, List<Integer> rightList) {
        Integer total = 0;
        Integer size = leftList.size();

        for (int i = 0; i < size; i++) {
            Integer left = leftList.get(i);
            Integer right = rightList.get(i);
            total += calculateDistanceInPair(left, right);
        }

        return total;
    }

    public Integer getAnswerA() {
        return calculateTotalDistance(leftList, rightList);
    }

    private void listToMap() {
        for (Integer number : rightList) {
            map.put(number, map.getOrDefault(number, 0) + 1);
        }
    }

    private Integer calculateSimilarityScore() {
        Integer score = 0;

        for (Integer number : leftList) {
            Integer frequency = map.getOrDefault(number, 0);
            score += number * frequency;
        }

        return score;
    }

    public Integer getAnswerB() {
        return calculateSimilarityScore();
    }
}
