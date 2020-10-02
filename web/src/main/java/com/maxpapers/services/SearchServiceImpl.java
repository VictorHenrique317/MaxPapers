package com.maxpapers.services;

import com.maxpapers.common.Photo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
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
    public CompletableFuture<List<Photo>> search(String query) {
        return CompletableFuture.completedFuture(daoService.query(query));
    }

    @Async
    @Override
    public CompletableFuture<Photo> get(int id) {
        return CompletableFuture.completedFuture(daoService.get(id));
    }
}
