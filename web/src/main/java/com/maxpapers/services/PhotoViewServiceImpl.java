package com.maxpapers.services;

import com.maxpapers.common.Photo;
import com.maxpapers.utils.Ansi;
import lombok.Cleanup;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class PhotoViewServiceImpl implements PhotoViewService {
    private DaoService daoService;

    @Autowired
    public PhotoViewServiceImpl(DaoService daoService) {
        this.daoService = daoService;
    }

    @Async
    @Override
    public CompletableFuture<List<Photo>> getRelatedPhotosTo(int id, int amount) {
        Photo basePhoto = daoService.get(id);
        List<Photo> relatedPhotos = daoService.queryAllRelated(basePhoto.getTagList());
        relatedPhotos.remove(basePhoto);
        if (relatedPhotos.size() < amount){ // There are less photos than the amount specified
            amount = relatedPhotos.size();
        }
        relatedPhotos = relatedPhotos.subList(0, amount);
        relatedPhotos.forEach(photo -> log.debug("{} Selected related photo with title {}",
                Ansi.BLUE, photo.getTitle()));

        return CompletableFuture.completedFuture(relatedPhotos);
    }


    @NonNull
    @Override
    public File getPhotoFileWithId(int id) {
        if (id <= 0) throw new IllegalArgumentException("id cannot be less than 1");
        Photo photo = daoService.get(id);
        try {
            File file = File.createTempFile("../../../resources/WEB-INF/images/" +
                    photo.getFileName(), "jpg");
            @Cleanup BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file));
            output.write(photo.getBytes());
            file.deleteOnExit();
            return file;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
