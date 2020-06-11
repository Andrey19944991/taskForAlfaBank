package com.alfa.model;

public class Report {
    private String report_id;
    private String transactSale_id;
    private String transactBuy_id;
    private String assetCount;

    @Override
    public String toString() {
        return "Report{" +
                "report_id='" + report_id + '\'' +
                ", transactSale_id='" + transactSale_id + '\'' +
                ", transactBuy_id='" + transactBuy_id + '\'' +
                ", assetCount='" + assetCount + '\'' +
                '}';
    }

    public String getReport_id() {
        return report_id;
    }

    public void setReport_id(String report_id) {
        this.report_id = report_id;
    }

    public String getTransactSale_id() {
        return transactSale_id;
    }

    public void setTransactSale_id(String transactSale_id) {
        this.transactSale_id = transactSale_id;
    }

    public String getTransactBuy_id() {
        return transactBuy_id;
    }

    public void setTransactBuy_id(String transactBuy_id) {
        this.transactBuy_id = transactBuy_id;
    }

    public String getAssetCount() {
        return assetCount;
    }

    public void setAssetCount(String assetCount) {
        this.assetCount = assetCount;
    }

    public Report(String report_id, String transactSale_id, String transactBuy_id, String assetCount) {
        this.report_id = report_id;
        this.transactSale_id = transactSale_id;
        this.transactBuy_id = transactBuy_id;
        this.assetCount = assetCount;
    }
}
