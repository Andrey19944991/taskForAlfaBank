package com.alfa.model;

public class ClientBalance {
    private String id;
    private String asset;
    private String assetNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getAssetNumber() {
        return assetNumber;
    }

    public void setAssetNumber(String assetNumber) {
        this.assetNumber = assetNumber;
    }

    @Override
    public String toString() {
        return "ClientBalance{" +
                "id='" + id + '\'' +
                ", asset='" + asset + '\'' +
                ", assetNumber='" + assetNumber + '\'' +
                '}';
    }
}
