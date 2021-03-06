package com.maxpapers.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EqualsAndHashCode(of={"id"})
public final class Photo {
    @Getter private int id;
    @Getter private String title;
    @Getter private String theme;
    private Tags tags;
    @Getter private byte[] bytes;
    @Getter private String encodedString;

    // Used to instantiate images from a file
    private Photo(String title, String theme, String tags, byte[] bytes, String encodedString) {
        this(0, title, theme, tags, bytes, encodedString);
    }

    // Used to instantiate images from the database
    private Photo(int id, String title, String theme, String tags, byte[] bytes, String encodedString) {
        this.id = id;
        this.title = title;
        this.theme = theme;
        this.tags = new Tags(tags);
        this.bytes = bytes;
        this.encodedString = encodedString;
    }

    // Used to instantiate images from a file
    public static Photo ofFile(@NonNull String title,@NonNull Theme theme,
                               @NonNull String tags,@NonNull byte[] bytes, @NonNull String encodedString){
        return new Photo(title, theme.toString(), tags, bytes, encodedString);
    }

    // Used to instantiate images from the database
    public static Photo ofEntry(int id,@NonNull String title,@NonNull Theme theme,
                                       @NonNull String tags,@NonNull byte[] bytes, @NonNull String base64String){
        if (id <= 0) throw new IllegalArgumentException("id cannot be less than 1");
        return new Photo(id, title, theme.toString(), tags, bytes, base64String);
    }

    public List<String> getTagList(){
        return tags.asList();
    }

    public String getTagString(){
        return tags.asString();
    }

    public String getFileName() {
        return this.title.strip().replace(" ", "_");
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", theme='" + theme + '\'' +
                ", tags=" + tags +
                '}';
    }



    // Class used to provide the tags in both String and List<String> forms
    private final class Tags{
        private final String tags;

        @NonNull
        public Tags(String tags) {
            this.tags = tags;
        }

        public List<String> asList(){
            List<String> results = new ArrayList<>();
            for (String tag : tags.split(",")){
                results.add(tag.strip());
            }
            return results;
        }

        public String asString(){
            return tags;
        }
    }
}
