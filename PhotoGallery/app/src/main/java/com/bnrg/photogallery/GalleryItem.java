package com.bnrg.photogallery;

/**
 * Created by palance on 2017/11/15.
 */

public class GalleryItem {
    private String mCaption;
    private String mUrl;
    private String mId;

    public String getCaption() {
        return mCaption;
    }

    public void setCaption(String caption) {
        mCaption = caption;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }


    @Override
    public String toString(){
        return mCaption;
    }
}
