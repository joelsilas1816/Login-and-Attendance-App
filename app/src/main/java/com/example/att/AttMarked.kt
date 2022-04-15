package com.example.att

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AttMarked : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_att_marked)

        val presText : TextView = findViewById(R.id.PresentCount)
        presText.text=counter.toString()

        val presentList : TextView =findViewById(R.id.PresentList)
        val absentList : TextView =findViewById(R.id.AbsentList)


        var PstringBuilder : StringBuilder = java.lang.StringBuilder()
        PresentBlogList.sortBy { it.rollNo }
        PstringBuilder.append("Roll no.     Name"+ "\n")


        for (i in PresentBlogList)
        {
           PstringBuilder.append(i.rollNo + "          " + i.name + "\n")
        }

//        stringBuilder.toSortedSet()

            presentList.text=PstringBuilder.toString()

        ////////////////
        var AstringBuilder : StringBuilder = java.lang.StringBuilder()
        AbsentBlogList.sortBy { it.rollNo }
        AstringBuilder.append("Roll no.     Name"+ "\n")


        for (i in AbsentBlogList)
        {
            AstringBuilder.append(i.rollNo + "              " + i.name + "\n")
        }

//        stringBuilder.toSortedSet()

        absentList.text=AstringBuilder.toString()
        /////////////


//            var testing = PresentBlogList[0]
//            Log.d("TESTING" , testing.toString())



    }
}