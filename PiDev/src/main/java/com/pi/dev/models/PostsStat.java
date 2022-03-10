package com.pi.dev.models;

import java.util.ArrayList;
import java.util.List;

public class PostsStat {
    private List<Integer> reactions;
    private List<Integer> postsNumber;
    private List<String> subjects;

    public List<Integer> getPostsNumber() {
        if(postsNumber == null) {
            postsNumber = new ArrayList<>();
        }
        return postsNumber;
    }

    public void setPostsNumber(List<Integer> postsNumber) {
        this.postsNumber = postsNumber;
    }

    public List<String> getSubjects() {
        if(subjects == null) {
            subjects = new ArrayList<>();
        }
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<Integer> getReactions() {
        if(reactions == null) {
            reactions = new ArrayList<>();
        }
        return reactions;
    }

    public void setReactions(List<Integer> reactions) {
        this.reactions = reactions;
    }

}
