package model;

public class Role {

    public static String ADMIN = "ADMIN";
    public static String EXPERT = "EXPERT";
    public static String CUSTOMER = "CUSTOMER";
    public static String SALE = "SALE";
    public static String MARKETING = "MARKETING";

    private int id;
    private String name;
    private int order;
    private boolean status;
    private String type;

    public Role() {
    }

    public Role(int id, String name, int order, boolean status, String type) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.status = status;
        this.type = type;
    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getADMIN() {
        return ADMIN;
    }

    public static void setADMIN(String ADMIN) {
        Role.ADMIN = ADMIN;
    }

    public static String getEXPERT() {
        return EXPERT;
    }

    public static void setEXPERT(String EXPERT) {
        Role.EXPERT = EXPERT;
    }

    public static String getCUSTOMER() {
        return CUSTOMER;
    }

    public static void setCUSTOMER(String CUSTOMER) {
        Role.CUSTOMER = CUSTOMER;
    }

    public static String getSALE() {
        return SALE;
    }

    public static void setSALE(String SALE) {
        Role.SALE = SALE;
    }

    public static String getMARKETING() {
        return MARKETING;
    }

    public static void setMARKETING(String MARKETING) {
        Role.MARKETING = MARKETING;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }
}
