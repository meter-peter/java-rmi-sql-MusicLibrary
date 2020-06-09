package noPlayer.api;

import java.io.Serializable;

public class Artist implements Serializable {
    int id;
    String name;
    String profilePhotoUrl;

    public Artist(int id, String name, String profilePhotoUrl) {
        this.id = id;
        this.name = name;
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }
}


