package com.muyoma.cld_gallery.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "do8bwuu7j");
        config.put("api_key", "722754764181537");
        config.put("api_secret", "tCZ6w8_DOhS06LLfwP9WVm2k9vk");
        return new Cloudinary(config);
    }
}