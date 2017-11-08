package com.yl.crazy.mydongtest.model;

import com.yl.crazy.mydongtest.ICallBack.ICallback;
import com.yl.crazy.mydongtest.ICallBack.IVIew;

/**
 * Created by dongdong on 2017/11/5.
 */

public class Present {

    private IVIew view;
    private Model model;

    public Present(IVIew view) {
        this.view = view;
        model = new Model();
    }

    //获取产品详情
    public void getProductDetial(String token, String id) {
        view.dialogShow();
        model.getProductDetial(token, id, new ICallback() {
            @Override
            public void sucessed(String s) {
                view.getFirstData(s);
                view.dialogCancel();
            }

            @Override
            public void fialed(String s) {
                view.dialogCancel();
                view.showFailed(s);
            }
        });
    }


    //获取产品详情
    public void getYouBianData(String token, int classId, String classOneId, String state, String orderBy, String storeId, int page, String lng, String lat,String areaID) {
        view.dialogShow();
        model.getYouBianData(token, classId, classOneId, state, orderBy, storeId, page, lng, lat,areaID ,new ICallback() {
            @Override
            public void sucessed(String s) {
                view.getFirstData(s);
                view.dialogCancel();
            }

            @Override
            public void fialed(String s) {
                view.dialogCancel();
                view.showFailed(s);
            }
        });
    }



    //获取一级分类列表
    public void getClassOneData(String token) {
        view.dialogShow();
        model.getClassOneData(token, new ICallback() {
            @Override
            public void sucessed(String s) {
                view.getSecondDate(s);
                view.dialogCancel();
            }

            @Override
            public void fialed(String s) {
                view.dialogCancel();
                view.showFailed(s);
            }
        });
    }

    //获取二级分类列表
    public void getClassTwoData(String token,String pid,String area_id) {
        view.dialogShow();
        model.getClassTwoData(token,pid,area_id, new ICallback() {
            @Override
            public void sucessed(String s) {
                view.getThirdDate(s);
                view.dialogCancel();
            }

            @Override
            public void fialed(String s) {
                view.dialogCancel();
                view.showFailed(s);
            }
        });
    }
}
