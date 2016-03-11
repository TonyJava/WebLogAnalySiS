package json;

import java.util.ArrayList;  
import java.util.List;  
  
public class Country {  
  
    String name;  
    int population;  
    private List<String> listOfStates;  
  
    //getter and setter methods  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public int getPopulation() {  
        return population;  
    }  
  
    public void setPopulation(int population) {  
        this.population = population;  
    }  
  
    public List<String> getListOfStates() {  
        return listOfStates;  
    }  
  
    public void setListOfStates(List<String> listOfStates) {  
        this.listOfStates = listOfStates;  
    }  
  
}  

//Read more at http://www.java2blog.com/2013/11/gson-example-read-and-write-json.html#aCq40uqE860mL7tY.99