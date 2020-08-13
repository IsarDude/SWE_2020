package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import datingDatabase.ConnectMySql;

public class User {
    private String firstName;
    private int age;
    private String gender;
    private String infoText;
    private String email;
    private String password;
    private int userID;
    private boolean visible;
    private boolean verified;
    private Subject subject;
    private Location location;
    private Filter filter;
    private LinkedList<Hobby> hobbies;
    private LinkedList<Photo> photos;
    private LinkedList<Course> courses;
    private ArrayList<Match> matches;
    private ArrayList<Like> likes;
    private ArrayList<Dislike> dislikes;
    private ConnectMySql connectMySql;

    public User() {
        hobbies = new LinkedList<Hobby>();
        photos = new LinkedList<Photo>();
        courses = new LinkedList<Course>();

        likes = new ArrayList<Like>();
        dislikes = new ArrayList<Dislike>();
        matches = new ArrayList<Match>();

        connectMySql = new ConnectMySql();
    }

    public void setHobbies(LinkedList<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public void setPhotos(LinkedList<Photo> photos) {
        this.photos = photos;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public void setLikes(ArrayList<Like> likes) {
        this.likes = likes;
    }

    public void setDislikes(ArrayList<Dislike> dislikes) {
        this.dislikes = dislikes;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInfoText() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public void addHobby(String newHobby) {
        Hobby hobby = new Hobby(newHobby);
        hobbies.add(hobby);
    }

    public void removeHobby(String deletableHobby) {
        Iterator<Hobby> hobbyIterator = hobbies.iterator();
        //Iterates through hobbies list and searches for Objects with matching hobbyNames. If so, deletes the Object in the list.
        while (hobbyIterator.hasNext()) {
            if (hobbyIterator.next().getName() == deletableHobby) {
                hobbies.remove();
            }
        }
    }

    public LinkedList<Hobby> getHobbies() {
        return hobbies;
    }

    public void addPhoto(Photo photo) {
        photos.add(photo);
    }

    public void removePhoto(String deletablePhotoID) {
        Iterator<Photo> photoIterator = photos.iterator();
        //Iterates through photos list and searches for Objects with matching photoIDs. If so, deletes the Object in the list.
        while (photoIterator.hasNext()) {
            if (photoIterator.next().getPhotoID() == deletablePhotoID) {
                photos.remove();
            }
        }
    }

    public LinkedList<Photo> getPhotos() {
        return photos;
    }

    public Location getLocation() {
        return location;
    }

    public void changeLocation(float newGps, String newName) {
        if(location == null){
            location = new Location();
        }
        location.setGPS(newGps);
        location.setName(newName);
    }

    public int[] getFreeTimeslots() {
        //Erstmal rauslassen
        return null;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(String deletableCourseID) {
        Iterator<Course> courseIterator = courses.iterator();
        //Iterates through courses list and searches for Objects with matching courseIDs. If so, deletes the Object in the list.
        while (courseIterator.hasNext()) {
            if (courseIterator.next().getCourseID() == deletableCourseID) {
                courses.remove();
            }
        }
    }

    public void changeFilter(int newMaxDistance, int newMinAge,int newMaxAge, String newGenderPreferences) {
        if(filter == null){
            filter = new Filter(newMaxDistance,newMinAge,newMaxAge,newGenderPreferences);
        }
        filter.setMaxDistance(newMaxDistance);
        filter.setAgeRange(newMinAge, newMaxAge);
        filter.setGenderPreferences(newGenderPreferences);
    }

    public Filter getFilter() {
        return filter;
    }

    public void changeSubject(int newSemester, String newName) {
        subject.setSemester(newSemester);
        subject.setName(newName);
    }

    public Subject getSubject() {
        return subject;
    }

    //Add a Like to the Array and check for a possible Match
    public void createLike(int otherUserID) {
        Like like = new Like(otherUserID);
        likes.add(like);
        like.checkForMatch(userID);
        connectMySql.addLike(otherUserID, userID); //To save all the Likes in the DB
    }

    public void createDislike(int otherUserID) {
        Dislike dislike = new Dislike(otherUserID);
        dislikes.add(dislike);
    }

    public void createMessage(String likeID, int messageType, String content) {
        //Erstmal rauslassen
    }

    public boolean checkForNewMessages() {
        //Ertmal rauslassen
        return false;
    }

    public void updateFromDatabase() {
        //Was passiert hier?
    }
}