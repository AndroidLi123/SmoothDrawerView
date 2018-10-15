package com.view.smoothview;

/**
 * @author：李晓旺
 * @date：2018/10/12
 * @description：滑动方向枚举类
 */
public enum SmoothDirection {
    /**
     * 向上滑动
     */
    UP(2),
    /**
     * 向下滑动
     */
    DOWN(1),
    /**
     * 向左滑动
     */
    LEFT(3),
    /**
     * 向右滑动
     */
    RIGHT(4);
    private int direction;

    SmoothDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }


}
