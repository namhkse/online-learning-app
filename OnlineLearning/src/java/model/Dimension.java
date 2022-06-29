package model;

public class Dimension {

    private int dimensionID;
    private String name;
    private String description;
    private DimensionType typeID;
    private Subject subjectID;

    public Dimension() {
    }

    public Dimension(int dimensionID, String name, String description, DimensionType typeID, Subject subjectID) {
        this.dimensionID = dimensionID;
        this.name = name;
        this.description = description;
        this.typeID = typeID;
        this.subjectID = subjectID;
    }

    public int getDimensionID() {
        return dimensionID;
    }

    public void setDimensionID(int dimensionID) {
        this.dimensionID = dimensionID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DimensionType getTypeID() {
        return typeID;
    }

    public void setTypeID(DimensionType typeID) {
        this.typeID = typeID;
    }

    public Subject getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Subject subjectID) {
        this.subjectID = subjectID;
    }

}
