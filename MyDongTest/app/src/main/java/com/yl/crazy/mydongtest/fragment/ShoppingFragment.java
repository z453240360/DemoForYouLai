package com.yl.crazy.mydongtest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yl.crazy.mydongtest.ICallBack.IVIew;
import com.yl.crazy.mydongtest.R;
import com.yl.crazy.mydongtest.model.Present;

/**
 * Created by dongdong on 2017/11/5.
 */

public class ShoppingFragment extends Fragment implements IVIew{

    private Present present;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detial,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        present = new Present(this);
    }

    @Override
    public void dialogShow() {

    }

    @Override
    public void getFirstData(String s) {

    }

    @Override
    public void getSecondDate(String s) {

    }

    @Override
    public void getThirdDate(String s) {

    }

    @Override
    public void dialogCancel() {

    }

    @Override
    public void showFailed(String s) {

    }
}
