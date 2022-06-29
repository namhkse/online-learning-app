package model;

import java.math.BigDecimal;

public class PricePackage {

    private int priceID;
    private String name;
    private int accessDuration;
    private boolean status;
    private BigDecimal listPrice;
    private BigDecimal salePrice;
    private Subject subjectID;

    public PricePackage() {
    }

    public PricePackage(int priceID, String name, int accessDuration, boolean status, BigDecimal listPrice, BigDecimal salePrice, Subject subjectID) {
        this.priceID = priceID;
        this.name = name;
        this.accessDuration = accessDuration;
        this.status = status;
        this.listPrice = listPrice;
        this.salePrice = salePrice;
        this.subjectID = subjectID;
    }

    public int getPriceID() {
        return priceID;
    }

    public void setPriceID(int priceID) {
        this.priceID = priceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccessDuration() {
        return accessDuration;
    }

    public void setAccessDuration(int accessDuration) {
        this.accessDuration = accessDuration;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Subject getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Subject subjectID) {
        this.subjectID = subjectID;
    }

}
