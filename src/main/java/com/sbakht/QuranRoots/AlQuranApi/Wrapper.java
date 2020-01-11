package com.sbakht.QuranRoots.AlQuranApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Wrapper {
    private Edition data;

    public Edition getData() {
        return data;
    }

    public void setData(Edition data) {
        this.data = data;
    }
}
