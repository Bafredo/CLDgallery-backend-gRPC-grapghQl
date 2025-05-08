package com.muyoma.cld_gallery;

import net.devh.boot.grpc.server.autoconfigure.GrpcServerAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(GrpcServerAutoConfiguration.class)
public class CldGalleryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CldGalleryApplication.class, args);
    }

}

