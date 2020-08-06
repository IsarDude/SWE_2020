package model;

import java.util.Iterator;
import java.util.LinkedList;

public class User {
    private String firstName;
    private  String lastName;
    private int age;
    private String gender;
    private String language;
    private String infoText;
    private String email;
    private String password;
    private String userID;
    private boolean visible;
    private boolean verified;
    private LinkedList<Hobby> hobbies;
    private LinkedList<Photo> photos;
    private LinkedList<Course> courses;
    private Location location;
    private Filter filter;
    private Subject subject;
    private Like[] likes;
    private Dislike[] dislikes;

    public User() {
        hobbies = new LinkedList<Hobby>();
        photos = new LinkedList<Photo>();
        courses = new LinkedList<Course>();

        likes = new Like[500];
        dislikes = new Dislike[1000];
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
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

    public void changeFilter(int newMaxDistance, int[] newAgeRange, String[] newGenderPreferences) {
        filter.setMaxDistance(newMaxDistance);
        filter.setAgeRange(newAgeRange);
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
    public void createLike(Like like) {
        int freeIndex = likes.length;
        likes[freeIndex] = like;
        like.checkForMatch();
    }

    public void createDislike(Dislike dislike) {
        int freeIndex = dislikes.length;
        dislikes[freeIndex] = dislike;
    }

    public void createMessage(String likeID, int messageType, String content) {
        //Was passiert hier?
    }

    public boolean checkForNewMessages() {
        //Was passiert hier?
        return false;
    }

    public void updateFromDatabase() {
        //Was passiert hier?
    }
}