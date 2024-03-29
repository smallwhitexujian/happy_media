package com.hapi.player;

public class PlayerStatus {




    /**
     * 播放未开始
     **/
    public static final int STATE_IDLE = -1;


    /**
     * 播放预装载中
     */
    public static final int STATE_PRELOADING = 0;
    /**
     * 播放准备中
     **/
    public static final int STATE_PREPARING = 1;
    /**
     * 播放准备就绪
     **/
    public static final int STATE_PREPARED = 2;
    /**
     * 预装载完成　等等通知播放
     */
    public static final int STATE_PRELOADED_WAITING = 3;
    /**
     * 正在播放
     **/
    public static final int STATE_PLAYING = 4;
    /**
     * 暂停播放
     **/
    public static final int STATE_PAUSED = 5;
    /**
     * 正在缓冲(播放器正在播放时，缓冲区数据不足，进行缓冲，缓冲区数据足够后恢复播放)
     **/
    public static final int STATE_BUFFERING_PLAYING = 6;
    /**
     * 正在缓冲(播放器正在播放时，缓冲区数据不足，进行缓冲，此时暂停播放器，继续缓冲，缓冲区数据足够后恢复暂停
     **/
    public static final int STATE_BUFFERING_PAUSED = 7;
    /**
     * 播放完成
     **/
    public static final int STATE_COMPLETED = 8;
    /**
     * 播放错误
     **/
    public static final int STATE_ERROR = 9;
    /**
     * 普通模式
     **/
    public static final int MODE_NORMAL = 10;
    /**
     * 全屏模式
     **/
    public static final int MODE_FULL_SCREEN = 11;
    /**
     * 小窗口模式
     **/
    public static final int MODE_TINY_WINDOW = 12;


}
