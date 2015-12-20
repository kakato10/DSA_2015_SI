import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.SpinnerListModel;


public class FrontBookkeeper61779 implements IFrontBookkeeper{
	public HashMap<String,Unit> units = new HashMap<String,Unit>();
	
	
	@Override
	public String updateFront(String[] news) {
		for(int p = 0; p < news.length;p++){
			String[] splittedNews = news[p].split(" ");
			if(splittedNews.length == 2 && splittedNews[0].equals("show")){
				System.out.println(units.get(splittedNews[1]).getSoldiers().toString()); 
			}
			else if(splittedNews.length == 4 && splittedNews[1].equals("attached") ){
				attachUnit(splittedNews[0],splittedNews[3]);
			}
			else if(splittedNews.length == 3 && splittedNews[0].equals("show")){
				getUnitsContaingSoldier(Integer.parseInt(splittedNews[2]));
			}
			else if(splittedNews.length == 7 && splittedNews[1].equals("attached")){
				attachSoldiersOnPosition(Integer.parseInt(splittedNews[6]), splittedNews[0], splittedNews[3]);
			}else if(splittedNews.length == 6 && splittedNews[0].equals("soldiers")){
				String numbers = splittedNews[1].replace("..", " ");
				String[] numbers1 = numbers.split(" ");
				soldiersDied(splittedNews[3], numbers1[0], numbers1[1]);
			}
			
			String[] array = news[p].replaceAll("\\s+","").split("="); 
			if(array.length == 2){
				addUnit(array);
			}
			
		}
		
		return null;
	}

    private String addUnit(String[] array){
    	if(!Character.toString(array[1].charAt(1)).equals("]")){
    		String[] numbers = array[1].substring(1,array[1].length()-1).split(",");
        	
			LinkedList numbersInt = new LinkedList<Integer>();
			for(int i = 0;i < numbers.length;i++)
			{
					numbersInt.add(Integer.parseInt(numbers[i]));
			}
			Unit newUnit = new Unit();
			newUnit.setSoldiers(numbersInt);
			newUnit.setName(array[0]);
			units.put(array[0],newUnit);
			return null;
    	}
    	Unit newUnit = new Unit();
		newUnit.setSoldiers(new LinkedList());
		newUnit.setName(array[0]);
		units.put(array[0],newUnit);	
    	return null;
		
	}
    
    public void attachUnit(String nameUnitToAttach, String nameUnit){
    	if(units.get(nameUnitToAttach).getNumberUnitsConnected()==1){
        		units.get(nameUnitToAttach).getCurrentConnectedUnit().getSoldiers().removeAll(units.get(nameUnitToAttach).getCurrentConnectedUnit().getSoldiers());
    	  
    	  units.get(nameUnitToAttach).setNumberUnitsConnected(0);
    	}
    	for(int i = 0; i <units.get(nameUnitToAttach).getSoldiers().size();i++ ){
			units.get(nameUnit).getSoldiers().add(units.get(nameUnitToAttach).getSoldiers().get(i));
		}
		units.get(nameUnitToAttach).setNumberUnitsConnected(1);
		units.get(nameUnitToAttach).setCurrentUnit(units.get(nameUnit));
		
    }
	
    public void getUnitsContaingSoldier(int id){
    	LinkedList unitsContainingSoldiers = new LinkedList();
		for (String key: units.keySet()) {
			if(units.get(key).getSoldiers().contains(id)){
				unitsContainingSoldiers.add(key);
		    }
		}
		String result = unitsContainingSoldiers.toString().replace('[', ' ').replace(']',' ');
		System.out.println(result.trim());
    }
    
    public void attachSoldiersOnPosition(int soldierId,String nameUnitToAtach,String nameUnit){
    	Integer size = units.get(nameUnit).getSoldiers().size() - units.get(nameUnit).getSoldiers().indexOf(soldierId)-1;
		for(int i = 0;i <size ;i++){
			units.get(nameUnit).getSoldiers().removeLast();
		}
		Integer start = units.get(nameUnit).getSoldiers().indexOf(soldierId)+1;
		for(int i = 0;i <units.get(nameUnitToAtach).getSoldiers().size() ;i++){
			units.get(nameUnit).getSoldiers().add(start, units.get(nameUnitToAtach).getSoldiers().get(i));
			start++;
		}
		units.get(nameUnitToAtach).setNumberUnitsConnected(1);
		units.get(nameUnitToAtach).setCurrentUnit(units.get(nameUnit));
    }
    
    public void soldiersDied(String name,String number1,String number2){
    
		for (String key: units.keySet()) {
			Unit unit = units.get(key);
			LinkedList m = unit.getSoldiers();
			for(int i =Integer.parseInt(number1);i<=Integer.parseInt(number2);i++){
					int index = m.indexOf(i);
					if(index!=-1){
						m.remove(index);
					}
				}
			}
    	}
	}
