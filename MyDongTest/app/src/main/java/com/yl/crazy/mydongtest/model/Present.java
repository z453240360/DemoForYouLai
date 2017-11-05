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
    public void getProductDetial(String token,String id){
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

}
