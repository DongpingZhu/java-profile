package com.test.cvat;

import java.sql.Array;

public class Video {
    private Object version;
    private Object meta;
    private Object[] track;

    public Object getVersion() {
        return version;
    }

    public void setVersion(Object version) {
        this.version = version;
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public Object getTrack() {
        return track;
    }

    public void setTrack(Object[] track) {
        this.track = track;
    }

    @Override
    public String toString() {
        return "Video{" +
                "version=" + version +
                ", meta=" + meta +
                ", track=" + track +
                '}';
    }
}
