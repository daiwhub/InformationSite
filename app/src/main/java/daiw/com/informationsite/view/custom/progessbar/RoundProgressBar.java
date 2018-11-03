package daiw.com.informationsite.view.custom.progessbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import daiw.com.informationsite.R;

/****************************
 * 类描述：自定义圆环进度条
 *
 * @author: daiw
 * @time: 2018/11/3  6:05 PM
 *
 *         ***************************
 */
public class RoundProgressBar extends View {
    /**
     * 画笔对象的引用
     */
    private Paint paint;

    /**
     * 圆环的颜色
     */
    private int roundColor;

    /**
     * 圆环进度的颜色
     */
    private int roundProgressColor;

    /**
     * 中间进度百分比的字符串的颜色
     */
    private int textColor;

    /**
     * 中间进度百分比的字符串的字体
     */
    private float textSize;

    /**
     * 圆环的宽度
     */
    private float roundWidth;

    /**
     * 最大进度
     */
    private int max;

    /**
     * 当前进度
     */
    private int progress;
    /**
     * 是否显示中间的进度
     */
    private boolean textIsDisplayable;

    /**
     * 内容
     */
    private String text;

    /**
     * 进度的风格，实心或者空心
     */
    private int style;

    /**
     * 进度开始的角度数
     */
    private int startAngle;

    private int backColor;


    public static final int STROKE = 0;
    public static final int FILL = 1;


    public RoundProgressBar(Context context) {
        this(context, null);
    }

    public RoundProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();

        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);

        //获取自定义属性和默认值，第一个参数是从用户属性中得到的设置，如果用户没有设置，那么就用默认的属性，即：第二个参数

        //圆环的颜色
        roundColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.GRAY);
        //圆环进度的颜色
        roundProgressColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundProgressColor, Color.BLUE);
        //中间进度百分比的字符串的颜色
        textColor = mTypedArray.getColor(R.styleable.RoundProgressBar_textColor, Color.BLACK);
        //中间进度百分比的字符串的字体
        textSize = mTypedArray.getDimension(R.styleable.RoundProgressBar_textSize, 18);
        //圆环的宽度
        roundWidth = mTypedArray.getDimension(R.styleable.RoundProgressBar_roundWidth, 5);
        //最大进度
        max = mTypedArray.getInteger(R.styleable.RoundProgressBar_max, 100);
        //是否显示中间的进度
        textIsDisplayable = mTypedArray.getBoolean(R.styleable.RoundProgressBar_textIsDisplayable, true);
        //进度的风格，实心或者空心
        style = mTypedArray.getInteger(R.styleable.RoundProgressBar_style, 0);
        //进度开始的角度数
        startAngle = mTypedArray.getInteger(R.styleable.RoundProgressBar_startAngle, -90);
        //圆形颜色
        backColor = mTypedArray.getColor(R.styleable.RoundProgressBar_backColor, 0);
        //内容
        text = mTypedArray.getString(R.styleable.RoundProgressBar_text);

        mTypedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 画最外层的大圆环
         */
        //获取圆心的x坐标
        int centre = getWidth() / 2;
        //圆环的半径
        int radius = (int) (centre - roundWidth / 2);
        //设置圆环的颜色
        paint.setColor(roundColor);
        //设置空心
        paint.setStyle(Paint.Style.STROKE);
        //设置圆环的宽度
        paint.setStrokeWidth(roundWidth);
        //消除锯齿
        paint.setAntiAlias(true);
        //画出圆环
        canvas.drawCircle(centre, centre, radius, paint);

        if (backColor != 0) {
            paint.setAntiAlias(true);
            paint.setColor(backColor);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(centre, centre, radius, paint);
        }

        /**
         * 画进度百分比
         */

        paint.setStrokeWidth(0);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        ////设置字体
        paint.setTypeface(Typeface.DEFAULT_BOLD);

        //中间的进度百分比，先转换成float在进行除法运算，不然都为0
        int percent = (int) (((float) progress / (float) max) * 100);
        //测量字体宽度，我们需要根据字体的宽度设置在圆环中间
        float textWidth = 0;
        if (TextUtils.isEmpty(text)) {
            text = percent + "%";
        }
        textWidth = paint.measureText(text);

        if (textIsDisplayable && percent != 0 && style == STROKE) {
            //画出进度百分比
            canvas.drawText(text,
                    centre - textWidth / 2,
                    centre + textSize / 2,
                    paint);

        }
        /**
         * 画圆弧 ，画圆环的进度
         */
        //设置进度是实心还是空心

        //设置圆环的宽度
        paint.setStrokeWidth(roundWidth);
        //设置进度的颜色
        paint.setColor(roundProgressColor);
        //用于定义的圆弧的形状和大小的界限
        RectF oval = new RectF(centre - radius,
                centre - radius,
                centre + radius,
                centre + radius);

        switch (style) {
            case STROKE:
                paint.setStyle(Paint.Style.STROKE);
                /*第二个参数是进度开始的角度，-90表示从12点方向开始走进度，如果是0表示从三点钟方向走进度，依次类推
                 *public void drawArc(RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint)
                    oval :指定圆弧的外轮廓矩形区域。
                    startAngle: 圆弧起始角度，单位为度。
                    sweepAngle: 圆弧扫过的角度，顺时针方向，单位为度。
                    useCenter: 如果为True时，在绘制圆弧时将圆心包括在内，通常用来绘制扇形。
                    paint: 绘制圆弧的画板属性，如颜色，是否填充等
                 *
                 */
                //根据进度画圆弧
                canvas.drawArc(oval, startAngle, 360 * progress / max, false, paint);

                break;
            case FILL:
                //根据进度画圆弧
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                if (progress != 0) {
                    canvas.drawArc(oval, startAngle, 360 * progress / max, true, paint);
                }
                break;
            default:
                break;
        }
    }

    public synchronized int getMax() {
        return max;
    }

    /*
     * @Description : 设置进度的最大值
     * @Params :
     * @Author : daiw
     * @Date : 2018/11/3
     */
    public synchronized void setMax(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.max = max;
    }

    /*
     * @Description :获取进度.需要同步
     * @Params :
     * @Author : daiw
     * @Date : 2018/11/3
     */
    public synchronized int getProgress() {
        return progress;
    }

    /*
     * @Description :设置进度，此为线程安全控件，由于考虑多线的问题，需要同步
     * 刷新界面调用postInvalidate()能在非UI线程刷新
     * @Params :
     * @Author : daiw
     * @Date : 2018/11/3
     */
    public void setProgress(int progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }

        if (progress > max) {
            progress = max;
        }
        if (progress <= max) {
            this.progress = progress;
            postInvalidate();
        }
    }

    public int getRoundColor() {
        return roundColor;
    }

    public void setRoundColor(int roundColor) {
        this.roundColor = roundColor;
    }

    public int getRoundProgressColor() {
        return roundProgressColor;
    }

    public void setRoundProgressColor(int roundProgressColor) {
        this.roundProgressColor = roundProgressColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public float getRoundWidth() {
        return roundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }
}
