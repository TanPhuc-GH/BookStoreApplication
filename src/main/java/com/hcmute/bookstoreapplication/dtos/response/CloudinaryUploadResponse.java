package com.hcmute.bookstoreapplication.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CloudinaryUploadResponse {
    private String url;
    private String publicId;
    private String resourceType;

    public CloudinaryUploadResponse(String url, String publicId, String resourceType){
        if(url.isEmpty())
            throw new RuntimeException("Upload file to Cloudinary failed, no Url to access upload file in Cloudinary response");
        if(publicId.isEmpty())
            throw new RuntimeException("Upload file to Cloudinary failed, no publicId to access upload file in Cloudinary response");
        if (resourceType.isEmpty())
            throw new RuntimeException("Upload file to Cloudinary failed, no resourceType to access upload file in Cloudinary response");
        this.url = url;
        this.publicId = publicId;
        this.resourceType = resourceType;
    }
}
