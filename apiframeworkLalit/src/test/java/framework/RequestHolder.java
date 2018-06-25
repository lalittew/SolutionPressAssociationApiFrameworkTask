package framework;

import models.SongRequest;

//This class is to hold the request 
public class RequestHolder {

    private SongRequest request;

    public SongRequest getRequest() {
        return request;
    }

    public void setRequest(SongRequest request) {
        this.request = request;
    }
}
