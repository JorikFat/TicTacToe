package dev.jorik.tictactoe.android

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import dev.jorik.tictactoe.core.Player
import dev.jorik.tictactoe.core.field.FieldDto

class FieldView : GridLayout {
    //top row
    private val tl: ImageButton
    private val tc: ImageButton
    private val tr: ImageButton
    //center row
    private val cl: ImageButton
    private val cc: ImageButton
    private val cr: ImageButton
    //bottom row
    private val bl: ImageButton
    private val bc: ImageButton
    private val br: ImageButton
    private var state: FieldDto = FieldDto()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        columnCount = 3
        rowCount = 3

        View.inflate(context, R.layout.view_field, this)
        //top row
        tl = findViewById(R.id.tl)
        tc = findViewById(R.id.tc)
        tr = findViewById(R.id.tr)
        //center row
        cl = findViewById(R.id.cl)
        cc = findViewById(R.id.cc)
        cr = findViewById(R.id.cr)
        //bottom row
        bl = findViewById(R.id.bl)
        bc = findViewById(R.id.bc)
        br = findViewById(R.id.br)
    }

    fun listen(listener :(Int, Int) -> Unit){
        tl.setOnClickListener { listener.invoke(0,0) }
        tc.setOnClickListener { listener.invoke(1,0) }
        tr.setOnClickListener { listener.invoke(2,0) }
        cl.setOnClickListener { listener.invoke(0,1) }
        cc.setOnClickListener { listener.invoke(1,1) }
        cr.setOnClickListener { listener.invoke(2,1) }
        bl.setOnClickListener { listener.invoke(0,2) }
        bc.setOnClickListener { listener.invoke(1,2) }
        br.setOnClickListener { listener.invoke(2,2) }
    }

    fun setState(field: FieldDto) {
        //tor row
        field.tl.also(tl::setSign)
        field.tc.also(tc::setSign)
        field.tr.also(tr::setSign)
        //center row
        field.cl.also(cl::setSign)
        field.cc.also(cc::setSign)
        field.cr.also(cr::setSign)
        //bottom row
        field.bl.also(bl::setSign)
        field.bc.also(bc::setSign)
        field.br.also(br::setSign)
        state = field
    }
}

private fun ImageView.setSign(player: Player?) =
    setImageResource(
        when (player) {
            null -> 0
            Player.CIRCLE -> R.drawable.ic_circle
            Player.CROSS -> R.drawable.ic_cross
        }
    )