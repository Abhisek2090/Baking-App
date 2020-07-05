package com.example.bakingapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Steps implements Serializable {

    private int id;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;

    public Steps (@JsonProperty("id") int id,
                  @JsonProperty("shortDescription") String shortDescription,
                  @JsonProperty("description") String description,
                  @JsonProperty("videoURL") String videoURL,
                  @JsonProperty("thumbnailURL") String thumbnailURL) {
            this.id = id;
            this.description =description;
            this.shortDescription = shortDescription;
            this.thumbnailURL = thumbnailURL;
            this.videoURL = videoURL;
    }

    public int getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }
}
