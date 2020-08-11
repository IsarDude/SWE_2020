package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SinglesPool {
    List<User> users;
    User randomUser;
    private boolean genderChecker;

    public SinglesPool(List<User> users, Filter filter){
        this.users = users;
        createPool(filter);
    }

    public void createPool(Filter filter){
        for(int i = 0; i<users.size(); i++){
            //Abfrage für das Alter
            if(filter.getMaxAge()<users.get(i).getAge() || filter.getMinAge()>users.get(i).getAge()){
                users.remove(i);
            }
            //Abfrage für Geschlechts Präferenzen
            /* Muss noch geändert werden weil preferenc doch kein array sonder -> male,female,both
            genderChecker= false;
            for(int x = 0; x< filter.getGenderPreferences().length; x++){
                for(int y = 0; y< users.get(i).getGender().length; y++){
                    if(filter.getGenderPreferences()[x].equals(users.get(i).getGender()[y])){
                        genderChecker = true;
                    }
                }


            }
            */

            if(genderChecker==false){
                users.remove(i);
            }

            //Abfrage für Location, funktioniert wahrscheinlich noch nicht
            if(filter.getMaxDistance()<users.get(i).getLocation().getGPS()){
                users.remove(i);
            }
        }
    }

    public void shufflePool(){
        Collections.shuffle(users);
    }

    public User getRandomUser(){

        Random rand = new Random();
        int numberOfElements = users.size();

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(users.size());
            randomUser = users.get(randomIndex);
            users.remove(randomIndex);
        }

        return randomUser;
    }
}
