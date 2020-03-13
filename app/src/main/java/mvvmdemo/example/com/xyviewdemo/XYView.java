package mvvmdemo.example.com.xyviewdemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

public class XYView extends View {

    private Paint mOutPaint;
    private float outCircleR;
    private Paint mInPaint;
    private Paint mkdPaint;
    private Paint mkdPaint1;
    private Paint  mScorePaint;
    private Paint  mCirclePaint;
    private Paint  mProgressPaint;
    private Paint mxyPaint;
    private float centerX;
    private float centerY;
    private float mIODistance = 50;
    private String[] levelName = {"较差","中等","良好","优秀","极好"};
    private String title = "信用优秀";
    private float score = 0;
    RectF outRectf ;
    public XYView(Context mContext) {
        super(mContext);
        init();
    }

    public XYView(Context mContext, AttributeSet set) {
        super(mContext, set);
        init();
    }


    private void init() {
        mOutPaint = new Paint();
        mOutPaint.setAntiAlias(true);
        mOutPaint.setColor(Color.BLACK);
        mOutPaint.setStyle(Paint.Style.STROKE);
        mOutPaint.setStrokeWidth(10);

        mInPaint = new Paint();
        mInPaint.setAntiAlias(true);
        mInPaint.setColor(Color.BLACK);
        mInPaint.setStyle(Paint.Style.STROKE);
        mInPaint.setStrokeWidth(50);

        mkdPaint = new Paint();
        mkdPaint.setAntiAlias(true);
        mkdPaint.setColor(Color.WHITE);
        mkdPaint.setStyle(Paint.Style.STROKE);
        mkdPaint.setStrokeWidth(5);

        mkdPaint1 = new Paint();
        mkdPaint1.setAntiAlias(true);
        mkdPaint1.setColor(Color.RED);
        mkdPaint1.setStyle(Paint.Style.STROKE);
        mkdPaint1.setStrokeWidth(5);

        mScorePaint = new Paint();
        mScorePaint.setAntiAlias(true);
        mScorePaint.setColor(Color.RED);
        mScorePaint.setTextSize(40);

        mxyPaint = new Paint();
        mxyPaint.setAntiAlias(true);
        mxyPaint.setColor(Color.WHITE);
        mxyPaint.setTextSize(80);

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(Color.WHITE);

        mProgressPaint = new Paint();
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setColor(Color.WHITE);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        mProgressPaint.setStrokeWidth(10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);
        centerX = width / 2;
        centerY = height / 2;
        outCircleR = (width - getPaddingLeft() - getPaddingRight()) / 2;
    }

    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = width;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                result = width;
                result += getPaddingLeft() + getPaddingRight();
                break;
        }
        return result;
    }

    private int measureHeight(int heightMeasureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = height;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                result = height;
                result += getPaddingTop() + getPaddingBottom();
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
         outRectf = new RectF(getPaddingLeft(), centerY - outCircleR, getPaddingLeft() + 2 * outCircleR, centerY + outCircleR);
        canvas.drawArc(outRectf, 15, -210, false, mOutPaint);
        RectF inRectf = new RectF(getPaddingLeft() + mIODistance, centerY - outCircleR + mIODistance, getPaddingLeft() + 2 * outCircleR - mIODistance, centerY + outCircleR - mIODistance);
        canvas.drawArc(inRectf, 15, -210, false, mInPaint);

        canvas.save();
        canvas.rotate(-105, centerX, centerY);
        for (int i = 0; i <= 30; i++) {
            if (i % 6 == 0) {
                String text = (i / 6 ) * 100 + "";
                canvas.drawLine(centerX, centerY - (outCircleR - mIODistance - mInPaint.getStrokeWidth() / 2), centerX, centerY - (outCircleR - mIODistance + mInPaint.getStrokeWidth() / 2), mkdPaint1);
                float textSize = mScorePaint.measureText(text);
                canvas.drawText(text, centerX - textSize / 2, centerY - (outCircleR - mIODistance - mInPaint.getStrokeWidth() / 2) + 60, mScorePaint);
            } else {
                canvas.drawLine(centerX, centerY - (outCircleR - mIODistance - mInPaint.getStrokeWidth() / 2), centerX, centerY - (outCircleR - mIODistance + mInPaint.getStrokeWidth() / 2), mkdPaint);
            }
            canvas.rotate(7, centerX, centerY);
        }
        canvas.restore();

        canvas.save();
        canvas.rotate(-84, centerX, centerY);
        for (int i = 0; i <levelName.length ; i++) {
            float textSize = mScorePaint.measureText(levelName[i]);
            canvas.drawText(levelName[i], centerX - textSize / 2, centerY - (outCircleR - mIODistance - mInPaint.getStrokeWidth() / 2) + 60, mScorePaint);
            canvas.rotate(42,centerX,centerY);
        }
        canvas.restore();

        canvas.save();
        float textSize = mxyPaint.measureText(title);
        float scoreTextSize = mxyPaint.measureText((int)score+"");
        canvas.drawText((int)score+"", centerX-scoreTextSize/2 , centerY-100 , mxyPaint);
        canvas.drawText(title, centerX-textSize/2 , centerY , mxyPaint);
        canvas.restore();
       // 绘制进度
        drawProgress(canvas);
    }

    float pos[] =new float[2];
    float tan[] =new float[2];
    private void drawProgress(Canvas canvas){
        if (score == 0){
            return;
        }
        Path path = new Path();
        float sweepAngle = score * 210 / 500;
        path.addArc(outRectf, 165, sweepAngle);
        PathMeasure pathMeasure = new PathMeasure(path, false);
        pathMeasure.getPosTan(pathMeasure.getLength() * 1, pos, tan);
        canvas.drawPath(path, mProgressPaint);
        canvas.drawCircle(pos[0], pos[1], 15, mCirclePaint);
    }

    /**
     * 设置分数
     */
    public void setScore(float score){
        this.score = score;
        startAni();
    }
    /**
     * 开启属性动画
     */
    private void startAni(){
        ValueAnimator ani =ValueAnimator.ofFloat(0,score);
        ani.setInterpolator(new LinearInterpolator());
        ani.setDuration(2000);
        ani.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                score = (float) valueAnimator.getAnimatedValue();
                postInvalidate();
            }
        });
        ani.start();
    }
}
