package tw.edu.pu.csim.hua.introduction

import android.graphics.Color
import android.graphics.Rect
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.GestureDetector.OnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity(), OnGestureListener, OnTouchListener {

    private lateinit var txv:TextView
    private lateinit var txv2:TextView
    private lateinit var txv3:TextView

    lateinit var gDetector: GestureDetector
    var count:Int = 0

    lateinit var img2: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        img2 = findViewById(R.id.img2)
        img2.setOnTouchListener(this)

        txv = findViewById(R.id.txv)
        txv.setTextColor(Color.parseColor("#0044BB"))
        txv.setBackgroundColor(Color.BLUE)
        txv.setTypeface(
            Typeface.createFromAsset(assets,
                "font/SentyVanilla.ttf"))
        txv.getBackground().setAlpha(50)

        txv = findViewById(R.id.txv2)
        txv.setTextColor(Color.parseColor("#0044BB"))
        txv.setTypeface(
            Typeface.createFromAsset(assets,
                "font/SentyVanilla.ttf"))

        txv = findViewById(R.id.txv3)
        txv.setTextColor(Color.parseColor("#0044BB"))
        txv.setTypeface(
            Typeface.createFromAsset(assets,
                "font/SentyVanilla.ttf"))

        gDetector = GestureDetector(this, this)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gDetector.onTouchEvent(event)
        return true
    }

    override fun onDown(p0: MotionEvent): Boolean {
        txv.text = "按下"
        return true
    }

    override fun onShowPress(p0: MotionEvent) {
        txv.text = "按下後無後續動作"
    }

    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        txv.text = "短按"
        return true
    }

    override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
        if (distanceY >= 0){
            txv.text = "向上拖曳"
        }
        else{
            txv.text = "向下拖曳"
        }
        return true
    }

    override fun onLongPress(p0: MotionEvent) {
        txv.text = "長按"
    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
        if (e1.x <= e2.x){
            txv.text = "往右快滑"
            count++
            if (count>3){
                count = 0
            }
        }
        else{
            txv.text = "往左快滑"
            count--
            if (count<0){
                count = 3
            }
        }
        var res:Int = getResources().getIdentifier("pu" + count.toString(),
            "drawable", getPackageName())
        findViewById<LinearLayout>(R.id.bg).setBackgroundResource(res)
        txv.text = count.toString()
        return true
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        txv.text = "狗狗"
        if (event?.action == MotionEvent.ACTION_MOVE){
            v?.x = event.rawX- v!!.width/2
            v?.y = event.rawY- v!!.height/2
        }
        return true
    }

}