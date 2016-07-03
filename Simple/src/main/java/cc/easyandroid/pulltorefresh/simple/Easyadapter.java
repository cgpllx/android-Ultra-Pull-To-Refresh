package cc.easyandroid.pulltorefresh.simple;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chenguoping on 16/7/2.
 */
public class Easyadapter extends  BaseRecyclerAdapter{
    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(),R.layout.fragment_item,null);
        return new EasyViewHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, Object data) {

    }

    class EasyViewHolder extends RecyclerView.ViewHolder{

        public EasyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
