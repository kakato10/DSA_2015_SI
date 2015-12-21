package frontBookkeeper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

public class FrontBookkeeper61789 implements IFrontBookkeeper {

    private Map<String, LinkedList<Integer>> soldiers;

    public FrontBookkeeper61789() {
        soldiers = new HashMap<>();
    }

    private String getUnitName(String input) {
        String[] result = input.split("=");
        return result[0].trim();
    }

    private LinkedList<Integer> getUnit(String input) {
        String[] splitInput = input.split("=")[1].trim().split("[^0-9]+");
        LinkedList<Integer> result = new LinkedList<>();
        for (int i = 0; i < splitInput.length - 1; i++) {
            result.add(i, Integer.parseInt(splitInput[i + 1]));
        }
        return result;
    }

    private void createUnit(String input) {
        String key = getUnitName(input);
        LinkedList<Integer> value = new LinkedList<>();
        value = getUnit(input);
        if (this.soldiers.containsKey(key)) {
            System.err.println("Cannot create unit with the same name as a previous one.");
        } else {
            this.soldiers.put(key, value);
        }
    }

    private void attachUnit(String unit1, String unit2) {
        for (Entry<String, LinkedList<Integer>> entry : this.soldiers.entrySet()) {
            if (entry.getValue().containsAll(this.soldiers.get(unit1))
                    && (entry.getValue().size() - this.soldiers.get(unit1).size()) != 0) {
                entry.getValue().removeAll(this.soldiers.get(unit1));
            }
        }
        if (this.soldiers.get(unit2).size() == 1 && this.soldiers.get(unit1).size() > 1) {
            System.err.println("Cannot attach both a soldier and another unit");
        } else {
            this.soldiers.get(unit2).addAll(this.soldiers.get(unit1));
        }
    }

    private void attachUnitAfter(String unit1, String unit2, Integer position) {
        for (Integer i : this.soldiers.get(unit2)) {
            if (i.equals(position)) {
                this.soldiers.get(unit2).addAll(i, this.soldiers.get(unit1));
            }
        }
    }

    private void removeSoldiers(String unit, Integer start, Integer end) {
        for (Entry<String, LinkedList<Integer>> entry : this.soldiers.entrySet()) {
            for (Integer i = start; i <= end; i++) {
                entry.getValue().removeFirstOccurrence(i);
            }
        }
    }

    private void show(String unit) {
        if (!this.soldiers.containsKey(unit)) {
            System.err.println("Unit not found");
        } else {
            System.out.println(this.soldiers.get(unit).toString());
        }
    }

    private void showSoldier(Integer soldier) {
        for (Entry<String, LinkedList<Integer>> entry : this.soldiers.entrySet()) {
            if (entry.getValue().contains(soldier)) {
                System.out.println(entry.getKey());
            }
        }
    }

    private void showCases(String input) {
        if (input.contains("soldier")) {
            showSoldier(Integer.parseInt(input.split("[ ]")[2]));
        } else {
            show(input.split("[ ]")[1]);
        }
    }

    private void attachCases(String input){
        String unit1 = input.split("[ ]")[0];
        String unit2 = input.split("[ ]")[3];
        if(input.contains("after")){
            Integer soldier = Integer.parseInt(input.split("[ ]")[6]);
            attachUnitAfter(unit1,unit2,soldier);
        } else {
            attachUnit(unit1,unit2);
        }

    }

    private void formatInput(String[] news){
        for(String line : news){
            if(line.contains("=")){
                createUnit(line);
            }
            if(line.contains("show")){
                showCases(line);
            }
            if(line.contains("died")){
                String soldiers = line.split("[ ]")[1];
                Integer soldier1 = Integer.parseInt(soldiers.split("[.]+")[0]);
                Integer soldier2 = Integer.parseInt(soldiers.split("[.]+")[1]);
                String unit = line.split("[ ]")[3];
                removeSoldiers(unit,soldier1, soldier2);
            }
            if(line.contains("attached")){
                attachCases(line);
            }
        }
    }

    @Override
    public String updateFront(String[] news) {
        formatInput(news);
        return "Finish";
    }
}

