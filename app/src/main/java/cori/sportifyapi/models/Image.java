package cori.sportifyapi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Image {

        @SerializedName("height")
        @Expose
        private Integer height;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("width")
        @Expose
        private Integer width;

        /**
         * No args constructor for use in serialization
         *
         */
        public Image() {
        }

        /**
         *
         * @param height
         * @param width
         * @param url
         */
        public Image(Integer height, String url, Integer width) {
            this.height = height;
            this.url = url;
            this.width = width;
        }

        /**
         *
         * @return
         * The height
         */
        public Integer getHeight() {
            return height;
        }

        /**
         *
         * @param height
         * The height
         */
        public void setHeight(Integer height) {
            this.height = height;
        }

        public Image withHeight(Integer height) {
            this.height = height;
            return this;
        }

        /**
         *
         * @return
         * The url
         */
        public String getUrl() {
            return url;
        }

        /**
         *
         * @param url
         * The url
         */
        public void setUrl(String url) {
            this.url = url;
        }

        public Image withUrl(String url) {
            this.url = url;
            return this;
        }

        /**
         *
         * @return
         * The width
         */
        public Integer getWidth() {
            return width;
        }

        /**
         *
         * @param width
         * The width
         */
        public void setWidth(Integer width) {
            this.width = width;
        }

        public Image withWidth(Integer width) {
            this.width = width;
            return this;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }

    }