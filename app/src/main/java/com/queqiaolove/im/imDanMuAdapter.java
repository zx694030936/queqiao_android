package com.queqiaolove.im;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.exceptions.HyphenateException;
import com.queqiaolove.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/7.
 */
public class imDanMuAdapter extends BaseAdapter {

    private Context context;
    private List<EMMessage> list = new ArrayList<>();

    public imDanMuAdapter(Context context, List<EMMessage> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (contentView == null) {
            contentView = LayoutInflater.from(context).inflate(R.layout.im_danmulist_item, null);
            holder = new ViewHolder(contentView);
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }
        EMMessage message = list.get(position);
        EMTextMessageBody txtBody = (EMTextMessageBody) message.getBody();
        String msg = txtBody.getMessage();
        holder.tv_content.setText(msg);
        /**
         * 从消息扩展中获取对方的昵称、头像等
         */
        try {
            String username = message.getStringAttribute("usernick");
            holder.tv_usernick.setText(username);
        } catch (HyphenateException e) {
            e.printStackTrace();
        }


        Log.w("MESS", "MESS" + message);

        return contentView;
    }


    class ViewHolder {
        TextView tv_usernick,//用户昵称
                tv_content;//消息内容

        public ViewHolder(View view) {
            tv_usernick = (TextView) view.findViewById(R.id.tv_usernick);
            tv_content = (TextView) view.findViewById(R.id.tv_content);

        }
    }
}
