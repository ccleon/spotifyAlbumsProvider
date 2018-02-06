package cori.sportifyapi.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ExternalUrls {

        @SerializedName("spotify")
        @Expose
        private String spotify;

        /**
         * No args constructor for use in serialization
         *
         */
        public ExternalUrls() {
        }

        /**
         *
         * @param spotify
         */
        public ExternalUrls(String spotify) {
            this.spotify = spotify;
        }

        /**
         *
         * @return
         * The spotify
         */
        public String getSpotify() {
            return spotify;
        }

        /**
         *
         * @param spotify
         * The spotify
         */
        public void setSpotify(String spotify) {
            this.spotify = spotify;
        }

        public ExternalUrls withSpotify(String spotify) {
            this.spotify = spotify;
            return this;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }

    }