package com.maxpapers.services;

import com.maxpapers.common.Photo;
import com.maxpapers.common.Theme;
import com.maxpapers.dao.PhotoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("prod")
public class PhotoServiceImpl implements PhotoService{
    private final PhotoDao photoDao;

    @Autowired
    public PhotoServiceImpl(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    @Override
    public List<Photo> query(String query){
        return photoDao.query(query);
    }

    @Override
    public List<Photo> queryByTheme(Theme theme){
        return photoDao.queryByTheme(theme);
    }

    @Override
    public Photo get(int id){
        return photoDao.get(id);
    }
}
