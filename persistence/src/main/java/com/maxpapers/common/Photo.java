package com.maxpapers.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@EqualsAndHashCode(of={"id"})
public final class Photo {
    private int id;
    private String title;
    private String theme;
    private List<String> tags;
    private byte[] bytes;

    private Photo(int id, String title, String theme, List<String> tags, byte[] bytes) {
        this.id = id;
        this.title = title;
        this.theme = theme;
        this.tags = tags;
        this.bytes = bytes;
    }

    public static Photo of(int id, String title, String theme, String tags, byte[] bytes){
        return new Photo(id, title, theme, TagResolver.resolve(tags), bytes);
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", theme='" + theme + '\'' +
                ", tags=" + tags +
                ", bytes=" + Arrays.toString(bytes) +
                '}';
    }

    private static final class TagResolver {
        private static List<String> resolve(@NonNull String tags){
            List<String> results = new ArrayList<>();
            for (String tag : tags.split(",")){
                results.add(tag.strip());
            }
            return results;
        }
    }
}
