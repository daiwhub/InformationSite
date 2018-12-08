package daiw.com.informationsite.view.custom.edittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import daiw.com.informationsite.R;


/****************************
 * 类描述：自定义登录输入框
 *
 * @author: daiw
 * @time: 2018/12/8  12:53 AM
 *
 *         ***************************
 */
public class CustomEditTextLogin extends LinearLayout {

    private ImageView iconImg;
    private EditText editText;
    private LinearLayout deleteLl;

    private AddTextChangeListener addTextChangeListener;

    public CustomEditTextLogin(Context context) {
        super(context);
        initViews(context, null);
    }

    public CustomEditTextLogin(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
    }

    public CustomEditTextLogin(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context, attrs);
    }

    private void initViews(Context context, AttributeSet attrs) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.layout_custom_edittext_login, this);

        iconImg = rootView.findViewById(R.id.layout_custom_edit_login_img);

        editText = rootView.findViewById(R.id.layout_custom_edit_login_edit);

        deleteLl = rootView.findViewById(R.id.layout_custom_edit_delete_ll);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomEditTextLogin);

        int backgroundID = typedArray.getResourceId(R.styleable.CustomEditTextLogin_editBackground,
                R.drawable.vector_drawable_svg_login_username);
        //设置背景
        iconImg.setBackgroundResource(backgroundID);
        //设置提示文字
        String hint = typedArray.getString(R.styleable.CustomEditTextLogin_editHint);
        editText.setHint(hint);
        //设置提示文字颜色
        int hintColor = typedArray.getColor(R.styleable.CustomEditTextLogin_editTextColorHint,
                ContextCompat.getColor(context, R.color.text_999999));
        editText.setHintTextColor(hintColor);
        //设置输入文字颜色
        int textColor = typedArray.getColor(R.styleable.CustomEditTextLogin_editTextColor,
                ContextCompat.getColor(context, R.color.text_333333));
        editText.setTextColor(textColor);
        //设置字号（px）
        float textSize = typedArray.getDimension(R.styleable.CustomEditTextLogin_editTextSize,
                getResources().getDimension(R.dimen.sp_16));
        //这里因为上面获取到的是ox所以在设置字号的时候，需要指定单位
        editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        //获取输入属性
        int inputType = typedArray.getInt(R.styleable.CustomEditTextLogin_editInputType, InputType.TYPE_CLASS_TEXT);
        editText.setInputType(inputType);
        //获取最大行数
        int maxLines = typedArray.getInt(R.styleable.CustomEditTextLogin_editMaxLines, 1);
        editText.setMaxLines(maxLines);
        //获取最大位数
        int maxLength = typedArray.getInt(R.styleable.CustomEditTextLogin_editMaxLength, 15);
        //注这里因为没有直接设置setMaxLength方法，只能通过这种方法设置
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});

        initLinsenters();

        typedArray.recycle();
    }

    /*
     * @Description : 获取输入内容
     * @Params :
     * @Author : daiw
     * @Date : 2018/12/8
     */
    public String getEditText() {
        if (editText != null) {
            return editText.getText().toString().trim();
        }
        return "";
    }

    private void initLinsenters() {
        //清除
        deleteLl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText != null) {
                    editText.setText("");
                }
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (addTextChangeListener != null) {
                    addTextChangeListener.beforeTextChanged(s, start, count, after);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (addTextChangeListener != null) {
                    addTextChangeListener.onTextChanged(s, start, before, count);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = s.toString();
                if (addTextChangeListener != null) {
                    addTextChangeListener.afterTextChanged(string);
                }
                if (TextUtils.isEmpty(string)) {
                    //隐藏清除按钮
                    showCleanEditText(false);
                } else {
                    //显示清除按钮
                    showCleanEditText(true);
                }
            }
        });
    }

    private void showCleanEditText(boolean isShow) {
        if (isShow) {
            if (deleteLl != null) {
                deleteLl.setVisibility(VISIBLE);
            }
        } else {
            if (deleteLl != null) {
                deleteLl.setVisibility(GONE);
            }
        }
    }

    /*
     * @Description : 设置输入框简单
     * @Params :
     * @Author : daiw
     * @Date : 2018/12/8
     */
    public void setAddTextChangeListener(AddTextChangeListener addTextChangeListener) {
        this.addTextChangeListener = addTextChangeListener;
    }

    public interface AddTextChangeListener {
        void beforeTextChanged(CharSequence s, int start, int count, int after);

        void onTextChanged(CharSequence s, int start, int before, int count);

        void afterTextChanged(String s);
    }

}
