package com.hcmute.bookstoreapplication.services.cloudinary;

import com.hcmute.bookstoreapplication.dtos.response.CloudinaryUploadResponse;
import org.springframework.stereotype.Service;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService{
    public static final String CLOUDINARY_CLOUD_NAME = "dj9vzrbnw";
    public static final String CLOUDINARY_API_KEY = "382428451222284";
    public static final String CLOUDINARY_API_SECRET = "RLc4ON-ey-ucU4gOYidNaf-3ZXs";
    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl() {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CLOUDINARY_CLOUD_NAME,
                "api_key", CLOUDINARY_API_KEY,
                "api_secret", CLOUDINARY_API_SECRET,
                "secure", true
        ));
    }

    @Override
    public CloudinaryUploadResponse upload(byte[] image) throws IOException {
        Map response = cloudinary.uploader().upload(image, ObjectUtils.asMap("resource_type", "auto"));
        return new CloudinaryUploadResponse(
                response.get("secure_url").toString(),
                response.get("public_id").toString(),
                response.get("resource_type").toString()
        );
    }
}
