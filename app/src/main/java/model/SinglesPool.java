package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SinglesPool {
    LinkedList<User> users;
    User randomUser;
    private boolean genderChecker;

    public SinglesPool(LinkedList<User> users){
        this.users = users;
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
