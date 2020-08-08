package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SinglesPool {
    List<User> users;
    User randomUser;

    public SinglesPool(List<User> users, Filter filter){
        this.users = users;
        createPool(filter);
    }

    public void createPool(Filter filter){
        for(int i = 0; i<users.size(); i++){
            if(filter.getMaxAge()<users.get(i).getAge() || filter.getMinAge()>users.get(i).getAge()){
                users.remove(i);
            }
            if(!filter.getGenderPreferences().equals(users.get(i).getGender())){
                users.remove(i);
            }
            //Location funktioniert wahrscheinlich noch nicht
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
