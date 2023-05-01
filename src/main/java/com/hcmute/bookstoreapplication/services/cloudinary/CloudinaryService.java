package com.hcmute.bookstoreapplication.services.cloudinary;

import com.hcmute.bookstoreapplication.dtos.response.CloudinaryUploadResponse;

import java.io.IOException;

public interface CloudinaryService {

    CloudinaryUploadResponse upload(byte[] image) throws IOException;
}
