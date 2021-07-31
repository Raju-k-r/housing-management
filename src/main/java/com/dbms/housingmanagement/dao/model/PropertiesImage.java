package com.dbms.housingmanagement.dao.model;

import lombok.Data;

@Data
public class PropertiesImage {

    // == Fields ==
    private int serialNumber;
    private int propertyId;
    private byte[] blob;

    // == Creating new PropertiesImage ==
    public static PropertiesImage createFromImageAndPropertyId(byte[] image, int propertyId) {
        // == Creating new Object ==
        PropertiesImage propertiesImage = new PropertiesImage();

        // == Setting the values ==
        propertiesImage.setPropertyId(propertyId);
        propertiesImage.setBlob(image);

        // == returning the object ==
        return propertiesImage;
    }
}
