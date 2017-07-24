package demo.test.test.test;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    private static final int[] DIGIT_STATES = {
            R.attr.state_zero,
            R.attr.state_one,
            R.attr.state_two,
            R.attr.state_three,
            R.attr.state_four,
            R.attr.state_five,
            R.attr.state_six,
            R.attr.state_seven,
            R.attr.state_eight,
            R.attr.state_nine,
    };

    final int numStates = DIGIT_STATES.length;
    private Button add, plus, minus;
    final int[] stateSet = new int[numStates];
    private ImageView rightImgView,midImageView,leftImageView;
    private int onthPlaceNum=-1,tenthPlaceNum=-1,hundredthPlaceNum=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rightImgView = (ImageView) findViewById(R.id.countdownIcon);
        midImageView = (ImageView) findViewById(R.id.countdownIcon1);
        leftImageView = (ImageView) findViewById(R.id.countdownIcon2);
        add = (Button) findViewById(R.id.add);
        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        rightImgView.setColorFilter(Color.parseColor("#000000"));
        midImageView.setColorFilter(Color.parseColor("#000000"));
        leftImageView.setColorFilter(Color.parseColor("#000000"));

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(onthPlaceNum==0){

                    midImageView.setVisibility(View.VISIBLE);
                    if(tenthPlaceNum==0){
                        leftImageView.setVisibility(View.VISIBLE);
                        hundredthPlaceNum= plusValue(hundredthPlaceNum);
                        plusFun(leftImageView,hundredthPlaceNum);
                    }
                    tenthPlaceNum=plusValue(tenthPlaceNum);
                    plusFun(midImageView,tenthPlaceNum);
                }
                onthPlaceNum=plusValue(onthPlaceNum);
                plusFun(rightImgView,onthPlaceNum);





            }

        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(onthPlaceNum==-1&& tenthPlaceNum==-1 && hundredthPlaceNum==-1){
                    resetFun();
                    return;
                }
                if(onthPlaceNum==8 && tenthPlaceNum==-1 && hundredthPlaceNum==-1){
                    resetFun();
                }
                if(onthPlaceNum==-1){
                    if(tenthPlaceNum==-1){
                        minusFun(leftImageView,++hundredthPlaceNum);
                        hundredthPlaceNum= minusValue(hundredthPlaceNum);
                    }
                    minusFun(midImageView,++tenthPlaceNum);
                    tenthPlaceNum= minusValue(tenthPlaceNum);
                }
                minusFun(rightImgView,++onthPlaceNum);
                onthPlaceNum= minusValue(onthPlaceNum);
            }


        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                final AnimatorSet add_0_1 = new AnimatorSet();

                add_0_1.playTogether(
                        ObjectAnimator.ofFloat(add, "alpha", 1, 0).setDuration(150),
                        ObjectAnimator.ofFloat(add, "scaleX", 1, 0.5f).setDuration(150),
                        ObjectAnimator.ofFloat(add, "scaleY", 1, 0.5f).setDuration(150)
                );
                add_0_1.start();
                add_0_1.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        plus.setVisibility(View.VISIBLE);
                        minus.setVisibility(View.VISIBLE);
                        final AnimatorSet minus_0_1 = new AnimatorSet();
                        minus_0_1.playTogether(
                                ObjectAnimator.ofFloat(minus, "alpha", 0, 1).setDuration(150),
                                ObjectAnimator.ofFloat(minus, "scaleX", 0.5f, 1).setDuration(150),
                                ObjectAnimator.ofFloat(minus, "scaleY", 0.5f, 1).setDuration(150)
                        );
                        minus_0_1.start();
                        final AnimatorSet plus_0_1 = new AnimatorSet();
                        plus_0_1.playTogether(
                                ObjectAnimator.ofFloat(plus, "alpha", 0, 1).setDuration(150),
                                ObjectAnimator.ofFloat(plus, "scaleX", 0.5f, 1).setDuration(150),
                                ObjectAnimator.ofFloat(plus, "scaleY", 0.5f, 1).setDuration(150)
                        );
                        plus_0_1.start();
                        plus_0_1.addListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                ObjectAnimator plusMoveRight = ObjectAnimator.ofFloat(plus,
                                        "translationX", 100);
                                plusMoveRight.setDuration(300);
                                plusMoveRight.start();

                                ObjectAnimator minusMoveLeft = ObjectAnimator.ofFloat(minus,
                                        "translationX", -100);
                                minusMoveLeft.setDuration(300);
                                minusMoveLeft.start();
                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        });
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });

            }
        });


    }

    private void resetFun() {

        leftImageView.setVisibility(View.GONE);
        midImageView.setVisibility(View.GONE);
        ObjectAnimator plusMoveLeft = ObjectAnimator.ofFloat(plus,
                "translationX", 0);
        plusMoveLeft.setDuration(300);
        plusMoveLeft.start();

        ObjectAnimator minusMoveRight = ObjectAnimator.ofFloat(minus,
                "translationX", 0);
        minusMoveRight.setDuration(300);
        minusMoveRight.start();
        minusMoveRight.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

                final AnimatorSet minus_1_0 = new AnimatorSet();
                minus_1_0.playTogether(
                        ObjectAnimator.ofFloat(minus, "alpha", 1, 0).setDuration(150),
                        ObjectAnimator.ofFloat(minus, "scaleX", 1, 0.5f).setDuration(150),
                        ObjectAnimator.ofFloat(minus, "scaleY", 1, 0.5f).setDuration(150)
                );
                minus_1_0.start();
                final AnimatorSet plus_1_0 = new AnimatorSet();
                plus_1_0.playTogether(
                        ObjectAnimator.ofFloat(plus, "alpha", 1, 0).setDuration(150),
                        ObjectAnimator.ofFloat(plus, "scaleX", 1, 0.5f).setDuration(150),
                        ObjectAnimator.ofFloat(plus, "scaleY", 1, 0.5f).setDuration(150)
                );
                plus_1_0.start();
                plus_1_0.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        final AnimatorSet add_0_1 = new AnimatorSet();

                        add_0_1.playTogether(
                                ObjectAnimator.ofFloat(add, "alpha", 0, 1).setDuration(150),
                                ObjectAnimator.ofFloat(add, "scaleX", 0.5f, 1).setDuration(150),
                                ObjectAnimator.ofFloat(add, "scaleY", 0.5f, 1).setDuration(150)
                        );
                        add_0_1.start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }

    private int minusValue(int onthPlaceNum) {
        if (onthPlaceNum == 9)
           return -1;
        else
            return onthPlaceNum;
    }

    private void minusFun(ImageView rightImgView, int onthPlaceNum) {
        for (int i = 0; i < stateSet.length; i++) {

            if (i == onthPlaceNum) {

                stateSet[i] = DIGIT_STATES[numStates - i - 1];
            } else {
                stateSet[i] = -DIGIT_STATES[numStates - i - 1];
            }
        }
        rightImgView.setImageState(stateSet, true);
    }

    private int plusValue(int hundredthPlaceNum) {
        if(hundredthPlaceNum==-1){
            hundredthPlaceNum=9;
        }

        return --hundredthPlaceNum;
    }

    private void plusFun(ImageView rightImgView, int onthPlaceNum) {

        for (int i = 0; i < stateSet.length; i++) {

            if (i == onthPlaceNum) {

                stateSet[i] = DIGIT_STATES[numStates - i - 1];
            } else {
                stateSet[i] = -DIGIT_STATES[numStates - i - 1];
            }
        }
        rightImgView.setImageState(stateSet, true);
    }



   /* void exitReveal(final View mLoadViewLong) {
        // get the center for the clipping circle
        int cx = mLoadViewLong.getMeasuredWidth() / 2;
        int cy = mLoadViewLong.getMeasuredHeight() / 2;
        // get the initial radius for the clipping circle
        int initialRadius = mLoadViewLong.getWidth() / 2;
        // create the animation (the final radius is zero)
        Animator anim =
                null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(mLoadViewLong, cx, cy, initialRadius, 0);
            // make the view invisible when the animation is done
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mLoadViewLong.setVisibility(View.GONE);
                    showRevealplus(plus);
                    showReveal(minus);
                }
            });
            anim.start();
        } else {
            mLoadViewLong.setVisibility(View.GONE);
        }
        // start the animation
    }
    void showRevealplus(final View v) {

        // get the center for the clipping circle
        int cx = v.getMeasuredWidth() / 2;
        int cy = v.getMeasuredHeight() / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(v.getWidth(), v.getHeight()) / 2;

        // create the animator for this view (the start radius is zero)
        Animator anim =
                null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0, finalRadius);


            // make the view visible and start the animation
            v.setVisibility(View.VISIBLE);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator ani) {
                    super.onAnimationEnd(ani);

                    plusx=v.getX();
                    ObjectAnimator animation = ObjectAnimator.ofFloat(v,
                            "translationX", 100);
                    animation.setDuration(300);

                    animation.start();
                }
            });
            anim.start();
        } else {


        }
    }
    void showReveal(final View v) {

        // get the center for the clipping circle
        int cx = v.getMeasuredWidth() / 2;
        int cy = v.getMeasuredHeight() / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(v.getWidth(), v.getHeight()) / 2;

        // create the animator for this view (the start radius is zero)
        Animator anim =
                null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0, finalRadius);


            // make the view visible and start the animation
            v.setVisibility(View.VISIBLE);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator anim) {
                    super.onAnimationEnd(anim);

                    ObjectAnimator animation = ObjectAnimator.ofFloat(v,
                            "translationX", -100);
                    animation.setDuration(300);

                    animation.start();

                }
            });
            anim.start();
        } else {


        }
    }
*/
}
