package cori.sportifyapi.entidad;

import android.provider.BaseColumns;

public class AlbumContract {

    public AlbumContract(){}

    public static class tablaAlbum implements BaseColumns{
        public static final String TABLE_NAME = "albumes";
        public static final String COL_NAME_ID = _ID;
        public static final String COL_NAME_NAME = "name";
        public static final String COL_NAME_TYPE = "type";
        public static final String COL_NAME_LARGE_IMAGE = "large_image";
        public static final String COL_NAME_SMALL_IMAGE = "small_image";
    }

}
