import java.util.HashMap;
import java.util.LinkedList;

public class Unit {
	private  String name;
	private  int numberUnitsConnected = 0;
	private LinkedList soldiers;
	private Unit currentConnectedUnit;
	
	
	public String getName(){
		return name;
	}
	
	public void setName(String name1){
		name = name1;
	}
	
	public int getNumberUnitsConnected(){
		return numberUnitsConnected;
	}
	
	public  void setNumberUnitsConnected(int number){
		numberUnitsConnected = number;
	}
	
	public  void setSoldiers(LinkedList soldierss){
		soldiers = soldierss;
	}
	
	public LinkedList getSoldiers(){
		return soldiers;
	}
	
	public  void setCurrentUnit(Unit newunit){
		currentConnectedUnit = newunit;
	}
	
	public Unit getCurrentConnectedUnit(){
		return currentConnectedUnit;
	}
}
