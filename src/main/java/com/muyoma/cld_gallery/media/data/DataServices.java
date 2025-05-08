package com.muyoma.cld_gallery.media.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DataServices {

    private static final Logger log = LoggerFactory.getLogger(DataServices.class);
    private final DataRepository dataRepository;

    public DataServices(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public Data addData(String title, String description, String url) {
        log.info("Attempting to add new data entry: title='{}', description='{}', url='{}'", title, description, url);

        try {
            Data d = new Data(title, description, url);
            Data saved = dataRepository.save(d);
            log.info("Data saved successfully with ID: {}", saved.getId());
            return saved;

        } catch (Exception e) {
            log.error("Failed to save data: {}", e.getMessage(), e);
            return null;
        }
    }
}
