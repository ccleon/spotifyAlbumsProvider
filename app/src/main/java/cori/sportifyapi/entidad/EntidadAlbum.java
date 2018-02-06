package cori.sportifyapi.entidad;

import android.os.Parcel;
import android.os.Parcelable;

public class EntidadAlbum implements Parcelable{

    private int id;
    private String name, type, smallImage, largeImage;

    public EntidadAlbum(int id, String name, String type, String smallImage, String largeImage) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.smallImage = smallImage;
        this.largeImage = largeImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public String getLargeImage() {
        return largeImage;
    }


    @Override
    public String toString() {
        return "EntidadAlbum{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", smallImage='" + smallImage + '\'' +
                ", bigImage='" + largeImage + '\'' +
                '}';
    }

    /**
     *PARCELABLE
     * */

    public EntidadAlbum(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        type = in.readString();
        smallImage = in.readString();
        largeImage = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(smallImage);
        dest.writeString(largeImage);
    }

    public static final Parcelable.Creator<EntidadAlbum> CREATOR = new
            Parcelable.Creator<EntidadAlbum>() {
        public EntidadAlbum createFromParcel(Parcel in) {
            return new EntidadAlbum(in);
        }

        public EntidadAlbum[] newArray(int size) {
            return new EntidadAlbum[size];
        }
    };

}
