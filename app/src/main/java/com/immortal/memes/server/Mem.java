package com.immortal.memes.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Mem {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("likes")
    @Expose
    private Integer likes;
    @SerializedName("reposts")
    @Expose
    private Integer reposts;
    @SerializedName("group_id")
    @Expose
    private Integer groupId;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("attachments")
    @Expose
    private List<Mem.Attachment> attachments = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getReposts() {
        return reposts;
    }

    public void setReposts(Integer reposts) {
        this.reposts = reposts;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public List<Mem.Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Mem.Attachment> attachments) {
        this.attachments = attachments;
    }


    public class Attachment {
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("path")
        @Expose
        private String path;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
