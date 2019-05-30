package net.ukr.ksm.model;

import java.util.Objects;

public class Picture {
    private long picture_id;
    private String link;

    public Picture() {
    }

    public Picture(long picture_id, String link) {
        this.picture_id = picture_id;
        this.link = link;
    }

    public long getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(long picture_id) {
        this.picture_id = picture_id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picture that = (Picture) o;
        if (!link.equals(that.link)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(picture_id);
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + picture_id +
                ", link='" + link + '\'' +
                '}';
    }
}
