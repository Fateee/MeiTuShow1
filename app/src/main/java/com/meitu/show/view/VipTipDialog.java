package com.meitu.show.view;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.meitu.show.R;
import com.meitu.show.activitys.donate.DonateActivity;

public class VipTipDialog extends DialogFragment implements View.OnClickListener {

    private int[] mDialogBgs = new int[]{R.drawable.bg_shop_one, R.drawable.bg_shop_two, R.drawable.bg_shop_three, R.drawable.bg_shop_four};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //设置背景透明
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_vip, null);
        ImageView mDialogBg = view.findViewById(R.id.iv_vip_dialog_bg);
        int resId = (int) (Math.random() * mDialogBgs.length);
        mDialogBg.setImageResource(mDialogBgs[resId]);
        View mNoDonateBT = view.findViewById(R.id.bt_no_donate_view);
        View mDonateBT = view.findViewById(R.id.bt_go_donate_view);
        mNoDonateBT.setOnClickListener(this);
        mDonateBT.setOnClickListener(this);
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_no_donate_view:
                dismiss();
                break;
            case R.id.bt_go_donate_view:
                //去捐赠
                DonateActivity.startActivity(getActivity());
                break;
        }
    }

}
