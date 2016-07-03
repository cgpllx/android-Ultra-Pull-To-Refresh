package cc.easyandroid.pulltorefresh.simple.dummy;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.GenericArrayType;

import cc.easyandroid.pulltorefresh.simple.ItemFragment;
import cc.easyandroid.pulltorefresh.simple.R;
import in.srain.cube.views.ptr.loadmore.LoadMorePtrFrameLayout;

public class Main2Activity extends AppCompatActivity {
    LoadMorePtrFrameLayout ptr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportFragmentManager().beginTransaction().replace(R.id.content,new ItemFragment()).commit();
    }

}
