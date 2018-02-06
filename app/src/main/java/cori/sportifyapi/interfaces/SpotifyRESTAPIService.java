package cori.sportifyapi.interfaces;

import cori.sportifyapi.models.AlbumsRes;

import retrofit.*;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

@SuppressWarnings("unused")
public interface SpotifyRESTAPIService {

    @GET("/v1/search?type=album")
    Call<AlbumsRes> searchAlbums(@Query("q") String artistName);
}
