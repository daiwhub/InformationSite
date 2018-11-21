package daiw.com.informationsite.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/19  11:51 PM
 *
 *         ***************************
 */
public class BaseActivity extends AppCompatActivity {

    private int contentView;

    public BaseActivity(int contentView){
        this.contentView = contentView;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(contentView);
    }
}
