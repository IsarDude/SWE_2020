package model;

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
    private Hobby[] hobbies;
    private Photo[] photos;
    private Location location;
    private Course[] courses;
    private Filter filter;
    private Subject subject;
    private Like[] likes;
    private Dislike[] dislikes;

    public User() {
        hobbies = new Hobby[10];
        photos = new Photo[6];
        courses = new Course[12];
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

    public void addHobby(Hobby hobby) {
        int freeIndex = hobbies.length;
        hobbies[freeIndex] = hobby;
    }

    public void removeHobby(String hobby) {
        //Hier durchiterieren ob String "blabla" vorhanden ist. falls ja -> löschen
        for ( Hobby : hobbies) {
            System.out.println(hobby);
        }
    }

    public Hobby[] getHobbies() {
        return hobbies;
    }

    public void addPhoto(Photo photo) {
        int freeIndex = photos.length;
        photos[freeIndex] = photo;
    }

    public void removePhoto() {
        //Welches photo soll verschwinden?
    }

    public Photo[] getPhotos() {
        return photos;
    }

    public Location getLocation() {
        return location;
    }

    public void changeLocation(Location location) {
        this.location = location;
    }

    public int[] getFreeTimeslots() {
        //Erstmal rauslassen
        return;
    }

    public void addCourse(Course course) {
        int freeIndex = courses.length;
        courses[freeIndex] = course;
    }

    public void removeCourse(String courseID) {
        //Welcher course soll verschwinden?
    }

    public void changeFilter(int newMaxDistance, int[] newAgeRange, String[] newGenderPreferences) {
        filter.setMaxDistance(newMaxDistance);
        filter.setAgeRange(newAgeRange);
        filter.setGenderPreferences(newGenderPreferences);
    }

    public Filter getFilter() {
        return filter;
    }

    public void changeSubject(Subject subject) {
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }

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
        return;
    }

    public void updateFromDatabase() {
        //Was passiert hier?
    }
}