package com.maxpapers.services;

import com.maxpapers.common.Photo;
import com.maxpapers.constants.Attribute;
import com.maxpapers.utils.Ansi;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class SearchServiceImpl implements SearchService {
    private DaoService daoService;

    @Autowired
    public SearchServiceImpl(DaoService daoService, List<Photo> searchResults) {
        this.daoService = daoService;
    }

    @Async
    @Override
    public CompletableFuture<Map<String, List<Photo>>> search(@NonNull String query) {
        List<Photo> results = daoService.query(query);
        int firstHalf = (int) Math.ceil(results.size()/2d); // Bigger if size is odd

        return CompletableFuture.completedFuture(
                Map.of(Attribute.FIRST_COL_SEARCH_RESULTS, results.subList(0, firstHalf),
                        Attribute.SECOND_COL_SEARCH_RESULTS, results.subList(firstHalf, results.size())));
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

    @Async
    @Override
    public CompletableFuture<Photo> get(int id) {
        return CompletableFuture.completedFuture(daoService.get(id));
    }

}
