package cori.sportifyapi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumsRes {

    @SerializedName("albums")
    @Expose
    private Albums albums;

    /**
     * @return The albums
     */
    public Albums getAlbums() {
        return albums;
    }

    /**
     * @param albums The albums
     */
    public void setAlbums(Albums albums) {
        this.albums = albums;
    }

}