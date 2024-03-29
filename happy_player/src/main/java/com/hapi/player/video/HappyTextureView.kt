package com.hapi.player.video

import android.content.Context
import android.util.AttributeSet
import android.view.TextureView
import android.view.View
import com.hapi.player.PlayerStatus.MODE_FULL_SCREEN

/**
 *
 */
internal class HappyTextureView : TextureView {

    private var videoHeight: Int = 0
    private var videoWidth: Int = 0

    private var centerCropError = 0f

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    fun adaptVideoSize(videoWidth: Int, videoHeight: Int) {
        if (this.videoWidth != videoWidth && this.videoHeight != videoHeight) {
            this.videoWidth = videoWidth
            this.videoHeight = videoHeight
            requestLayout()
        }
    }

    override fun setRotation(rotation: Float) {
        if (rotation != getRotation()) {
            super.setRotation(rotation)
            requestLayout()
        }
    }


    var parentWindType: (() -> Int)? = null

    fun setCenterCropError(centerCropError: Float) {
        this.centerCropError = centerCropError
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthMeasureSpec = widthMeasureSpec
        var heightMeasureSpec = heightMeasureSpec

        val viewRotation = rotation

//        // 如果判断成立，则说明显示的TextureView和本身的位置是有90度的旋转的，所以需要交换宽高参数。
//        if (viewRotation == 90f || viewRotation == 270f) {
//            val tempMeasureSpec = widthMeasureSpec
//            widthMeasureSpec = heightMeasureSpec
//            heightMeasureSpec = tempMeasureSpec
//        }


        var widthV = videoWidth
        var heightV = videoHeight

        val widthSpecMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val widthSpecSize = View.MeasureSpec.getSize(widthMeasureSpec)
        val heightSpecMode = View.MeasureSpec.getMode(heightMeasureSpec)
        val heightSpecSize = View.MeasureSpec.getSize(heightMeasureSpec)

        //控件宽高

        if (videoWidth > 0 && videoHeight > 0) {


            val hightRatio = heightSpecSize / heightV.toFloat()
            val withRatio = widthSpecSize / widthV.toFloat()
            /**
             * 横瓶播放器　播放横屏视频　
             */

            if (((widthV > heightV && widthSpecSize > heightSpecSize)

                        || (widthV < heightV && widthSpecSize < heightSpecSize))

                && parentWindType?.invoke() != MODE_FULL_SCREEN

            ) {
                val max = if (hightRatio < withRatio) {
                    withRatio
                } else {
                    hightRatio
                }

                val tempW = (heightV * max - heightSpecSize) / heightSpecSize
                val tempH = (tempW * max - widthSpecSize) / widthMeasureSpec

                if (Math.abs(tempH) > centerCropError && Math.abs(tempW) > centerCropError) {
                    heightV = (heightV * max).toInt()
                    widthV = (widthV * max).toInt()
                    setMeasuredDimension(widthV, heightV)
                    return
                }
            }


            if (widthSpecMode == View.MeasureSpec.EXACTLY && heightSpecMode == View.MeasureSpec.EXACTLY) {
                    // the size is fixed
                    widthV = widthSpecSize
                    heightV = heightSpecSize
                    // for compatibility, we adjust size based on aspect ratio
                    if (videoWidth * heightV < widthV * videoHeight) {
                        widthV = heightV * videoWidth / videoHeight
                    } else if (videoWidth * heightV > widthV * videoHeight) {
                        heightV = widthV * videoHeight / videoWidth
                    }
                } else if (widthSpecMode == View.MeasureSpec.EXACTLY) {
                    // only the width is fixed, adjust the height to match aspect ratio if possible
                    widthV = widthSpecSize
                    heightV = widthV * videoHeight / videoWidth
                    if (heightSpecMode == View.MeasureSpec.AT_MOST && heightV > heightSpecSize) {
                        // couldn't match aspect ratio within the constraints
                        heightV = heightSpecSize
                        widthV = heightV * videoWidth / videoHeight
                    }
                } else if (heightSpecMode == View.MeasureSpec.EXACTLY) {
                    // only the height is fixed, adjust the width to match aspect ratio if possible
                    heightV = heightSpecSize
                    widthV = heightV * videoWidth / videoHeight
                    if (widthSpecMode == View.MeasureSpec.AT_MOST && widthV > widthSpecSize) {
                        // couldn't match aspect ratio within the constraints
                        widthV = widthSpecSize
                        heightV = widthV * videoHeight / videoWidth
                    }
                } else {
                    // neither the width nor the height are fixed, try to use actual video size
                    widthV = videoWidth
                    heightV = videoHeight
                    if (heightSpecMode == View.MeasureSpec.AT_MOST && heightV > heightSpecSize) {
                        // too tall, decrease both width and height
                        heightV = heightSpecSize
                        widthV = heightV * videoWidth / videoHeight
                    }
                    if (widthSpecMode == View.MeasureSpec.AT_MOST && widthV > widthSpecSize) {
                        // too wide, decrease both width and height
                        widthV = widthSpecSize
                        heightV = widthV * videoHeight / videoWidth
                    }
                }

                 setMeasuredDimension(widthV, heightV)
                 return
            }


        setMeasuredDimension(widthSpecSize, heightSpecSize)



    }
}
