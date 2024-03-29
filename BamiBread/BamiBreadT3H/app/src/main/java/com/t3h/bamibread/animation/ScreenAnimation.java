package com.t3h.bamibread.animation;


import com.t3h.bamibread.R;

/**
 * Created by Heart Of Dead on 9/10/2017.
 */

public enum ScreenAnimation {
    OPEN_FULL(R.anim.enter_to_right, R.anim.exit_to_right,
            R.anim.enter_to_left, R.anim.exit_to_left),
    NONE(0, 0, 0, 0);


    private final int enterToRight;
    private final int exitToRight;
    private final int enterToLeft;
    private final int exitToLeft;

    ScreenAnimation(int openToRight, int exitToRight, int openToLeft, int exitToLeft) {
        this.enterToRight = openToRight;
        this.exitToRight = exitToRight;
        this.enterToLeft = openToLeft;
        this.exitToLeft = exitToLeft;
    }

    public int getEnterToRight() {
        return enterToRight;
    }

    public int getExitToRight() {
        return exitToRight;
    }

    public int getEnterToLeft() {
        return enterToLeft;
    }

    public int getExitToLeft() {
        return exitToLeft;
    }
}
