package model;

public class Photo{

    private String link;
    private boolean isFirst;
    private String photoID;

    public void setLink(String link){
        this.link = link;
    }

    public String getLink(){
        return this.link;
    }

    public void setPhotoID(String photoID){
        this.photoID = photoID;
    }

    public String getPhotoID(){
        return this.photoID;
    }

    public void setIsFirst(boolean isFirst){
        this.isFirst = isFirst;
    }

    public boolean getIsFirst(){
        return this.isFirst;
    }
}