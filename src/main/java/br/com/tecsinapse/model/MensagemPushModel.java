package br.com.tecsinapse.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by renancastro on 27/07/16.
 */
public class MensagemPushModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String app_id;
    private List<String> included_segments;
    private Map<String,String> contents;

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public List<String> getIncluded_segments() {
        return included_segments;
    }

    public void setIncluded_segments(List<String> included_segments) {
        this.included_segments = included_segments;
    }

    public Map<String, String> getContents() {
        return contents;
    }

    public void setContents(Map<String, String> contents) {
        this.contents = contents;
    }
}
