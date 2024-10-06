package com.ytrewq.rosLearning.Entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "themeMaterials")
public class ThemeMaterial extends BaseEntity {
    private String title;
    private String materialType;
    private String materialURL;
    private String materialText;

    public ThemeMaterial(String title, String materialType, String materialURL, String materialText) {
        this.title = title;
        this.materialType = materialType;
        this.materialURL = materialURL;
        this.materialText = materialText;
    }

    protected ThemeMaterial() {
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "materialType")
    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    @Column(name = "materialURL")
    public String getMaterialURL() {
        return materialURL;
    }

    public void setMaterialURL(String materialURL) {
        this.materialURL = materialURL;
    }

    @Column(name = "materialText")
    public String getMaterialText() {
        return materialText;
    }

    public void setMaterialText(String materialText) {
        this.materialText = materialText;
    }
}
