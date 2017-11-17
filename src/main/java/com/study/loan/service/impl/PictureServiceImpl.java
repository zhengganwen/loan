package com.study.loan.service.impl;

import com.study.loan.core.AttachesConfig;
import com.study.loan.core.uuid;
import com.study.loan.dao.PictureDao;
import com.study.loan.pojo.Picture;
import com.study.loan.service.PictureService;
import com.study.loan.util.PictureUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Mr.Zheng
 * @Description 照片实现层
 * @Date 2017/11/14
 */
@Service
public class PictureServiceImpl implements PictureService {
    @Resource
    private PictureDao pictureDao;

    @Override
    public int insert(Picture picture) {
        if(picture.getPath()!=null){
            //获取照片id
            String pathid = picture.getId();
            //设置照片本地存储路径
            PictureUtil.picture(picture.getPath(), AttachesConfig.getValue("pics_local_path"),pathid+".png");
            //设置照片的访问路径
            String Picurl = AttachesConfig.getValue("pics_url_root")+pathid+".png";
            picture.setPath(Picurl);
        }
        return pictureDao.insert(picture);
    }

    @Override
    public int update(Picture picture) {
        return pictureDao.update(picture);
    }

    @Override
    public int delete(String id) {
        return pictureDao.delete(id);
    }

    @Override
    public List<Picture> findPictureList() {
        return pictureDao.findPictureList();
    }
}
