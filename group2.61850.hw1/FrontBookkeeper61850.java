
package frontbookkeeper61850;

import java.util.HashMap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import java.util.Collections;

public class FrontBookkeeper61850 implements IFrontBookkeeper{
    
    private static HashMap<String, String> soldierToUnits =  new HashMap<String, String>();
    private static HashMap<String, String>  units = new HashMap<String, String>();
    
    
    
    //Creates new unit
    private static void newUnit(String unit){
        Matcher name =  Pattern.compile("[^\\W_]\\w*[^\\W_]").matcher(unit);
        name.find();
       
        Matcher soldiers = Pattern.compile("[^\\W]*(\\[\\d*(, \\d+)*\\])[^\\W]*").matcher(unit);
        soldiers.find();
        
        units.put(name.group(0), soldiers.group(0));
        
        Matcher soldiersIndexes = Pattern.compile("\\d*").matcher(soldiers.group(0));
        while(soldiersIndexes.find()){
            soldierToUnits.put(soldiersIndexes.group(), name.group(0));
        }
    }
    
    
    //Attaches unit after other unit
    private static void attachUnit(String line){
        Matcher unit =  Pattern.compile("(\\w*[^(\\W+|(attached)|(to)])\\w*").matcher(line);
        
        unit.find();
        String unit1 = unit.group();
        String u1 = units.get(unit1);
        boolean a = Pattern.matches("(\\[\\W*\\d+(, \\d+)*\\])", u1);
        
        unit.find();
        String unit2 = unit.group();
        String u2 = units.get(unit2);
        boolean b = Pattern.matches("(\\[\\W*\\d+(, \\d+)*\\])", u2);
        
        if(a){
            
            if(b){
                units.put(unit2, u2.substring(0,u2.length()-1)+", " + u1.substring(1,u1.length()));
            }
            else{
                units.put(unit2, u1);
            }
            
            Matcher soldiersIndexes = Pattern.compile("\\d*[^\\W*]").matcher(u1);
            while(soldiersIndexes.find()){
                soldierToUnits.put(soldiersIndexes.group(), soldierToUnits.get(soldiersIndexes.group())+", "+unit2);
            }
        }

    }
    
    
    //Attaches unit after some index
    private static void attachAfter(String line){
        Matcher data = Pattern.compile("(\\w*[^(\\W+|(attached)|(to)|(after)|(soldier)])\\w*").matcher(line);
        
        data.find();
        String unit1 = data.group();
        String u1 = units.get(unit1);
        boolean a = Pattern.matches("(\\[\\W*\\d+(, \\d+)*\\])", u1);
        
        data.find();
        String unit2 = data.group();
        String u2 = units.get(unit2);

        
        data.find();
        String index = data.group();
        
        if(a){
            u2 = u2.replaceAll(index , index + ", " +  u1.substring(1, u1.length()-1));
            units.put(unit2, u2);
            
            Matcher soldiersIndexes = Pattern.compile("[\\d*]").matcher(u1);        
            while(soldiersIndexes.find()){
                soldierToUnits.put(soldiersIndexes.group(), soldierToUnits.get(soldiersIndexes.group())+", "+unit1);
            }
        }
        
    }
    
    private static void death(String line){
         Matcher data = Pattern.compile("(\\w*[^(\\W+|(soldiers)|(from)|(died)|(heroically)])\\w*").matcher(line);
        
         data.find();
         String soldier1 = data.group();
         
         data.find();
         String soldier2 = data.group();
         
         data.find();
         String unit = data.group();
         String soldiers = units.get(unit);
       
         if(soldier1.equals(soldier2)){
            
            Matcher soldierUnits = Pattern.compile("\\w*[^,\\W*]").matcher(soldierToUnits.get(soldier2));
            while(soldierUnits.find()){

                String c =soldierUnits.group();

                if(units.get(c).substring(1,soldier1.length()+1).equals(soldier1)){
                    units.put(c, units.get(c).replaceAll("[^\\d]"+soldier1+"[^\\d](, )?",""));
                }
                else{
                    units.put(c, units.get(c).replaceAll("(, )?" + soldier1,""));
                }
            }
         }
         else{
            Matcher deathSoldiers = Pattern.compile("[^\\d]" +soldier1 + "(, \\d*)*" + soldier2+"[^\\d]").matcher(soldiers);
            deathSoldiers.find();
            
            Matcher index = Pattern.compile("[^\\W*]\\d*").matcher(deathSoldiers.group());
            while(index.find()){
                String i = index.group();
                
                Matcher soldierUnits = Pattern.compile("\\w*[^,\\W*]").matcher(soldierToUnits.get(i));
                while(soldierUnits.find()){
                    
                    String c =soldierUnits.group();
                   
                    if(units.get(c).substring(1,i.length()+1).equals(i)){
                        units.put(c, units.get(c).replaceAll("[^\\d]"+i+"[^\\d](, )?",""));
                    }
                    else{
                        units.put(c, units.get(c).replaceAll("(, )?" + i ,""));
                        
                    }
                    
            }
                
            }
         }
    }
    
    
    private static String showUnit(String line){
        Matcher unit = Pattern.compile("([^(show\\W*)]\\w*)").matcher(line);
        unit.find();
        
        return units.get(unit.group(0));
    }
    
    private static String showSoldier(String line){
        Matcher soldier = Pattern.compile("([^(show\\W*soldier\\W*)]\\w*)").matcher(line);
        soldier.find();
        return soldierToUnits.get(soldier.group(0));
    }
    
    
    @Override
    public String updateFront(String[] news){
        String result = "";
        for(int i = 0; i < news.length; i++){
            
            if (Pattern.matches("\\w*\\W*=\\W*\\[\\W*\\d*(, \\d+)*\\]\\W*", news[i])){
                newUnit(news[i]);
            }
            else if(Pattern.matches("\\w*\\W+attached\\W+to\\W+\\w*\\W*", news[i])){
                attachUnit(news[i]);
            }
            else if(Pattern.matches("\\w*\\W+attached\\W+to\\W+\\w*\\W+after\\W+soldier\\W+\\d+", news[i])){
                attachAfter(news[i]);
            }
            else if(Pattern.matches("soldiers\\W+\\d+..\\d+\\W+from\\W+\\w+\\W+died\\W+heroically", news[i])){
                death(news[i]);
            }
            else if(Pattern.matches("\\W*show\\W+\\w+", news[i])){
                result += showUnit(news[i]) + "\n";
            }
            else if(Pattern.matches("\\W*show\\W+soldier\\W+\\d+", news[i])){
                result += showSoldier(news[i]) + "\n";
            }
            else{
                System.out.println("Error message!");
            }}
            
        if(result.length()>0){
        return result.substring(0,result.length()-1);
        }
        return "";
    }
    
    public static void main(String[] args) {

    }
    
}


