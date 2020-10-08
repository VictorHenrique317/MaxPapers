package com.maxpapers.services;

import com.maxpapers.common.Photo;
import com.maxpapers.common.Theme;
import com.maxpapers.dao.PhotoDao;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Profile("!dev")
public class DaoServiceImpl implements DaoService {
    private final PhotoDao photoDao;

    @Autowired
    public DaoServiceImpl(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    @Override
    public List<Photo> queryAllRelated(@NonNull List<String> tags) {
        Set<Photo> allRelatedPhotos = new HashSet<>();
        for (String tag :tags){
            allRelatedPhotos.addAll(query(tag));
        }
        return new LinkedList<>(allRelatedPhotos);
    }

    @Override
    public List<Photo> query(@NonNull String query){
        return photoDao.query(query);
    }

    @Override
    public List<Photo> queryByTheme(@NonNull Theme theme){
        return photoDao.queryByTheme(theme);
    }

    @Override
    public Photo get(int id){
        return photoDao.get(id);
    }

    @Override
    public int getEntryCount() {
        return photoDao.getEntryCount();
    }
}
