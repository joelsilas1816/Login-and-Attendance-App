package com.example.att

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.att.models.BlogPost
import kotlinx.android.synthetic.main.activity_att_marked.*
import kotlinx.android.synthetic.main.activity_student_name_list.*
import kotlinx.android.synthetic.main.row_style.*

class StudentNameList : AppCompatActivity()  {

    private lateinit var blogAdapter: BlogRecyclerAdapter
    private lateinit var tv : TextView
       lateinit var presentStud : TextView
      lateinit var myMystery : TextView
//      lateinit var view1 : View
//     lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_name_list)

        initRecyclerView()
        addDataSet()

        var gotprev : Intent = getIntent()
        var lname = gotprev.getStringExtra("lectName")
        tv = findViewById(R.id.lecture_name)
        tv.text = lname
        presentStud= findViewById(R.id.pres)
        presentStud.text=counter.toString()

    }

    private fun addDataSet(){
        val data = DataSource.createDataSet()
        blogAdapter.submitList(data)

    }

    private fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@StudentNameList)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            blogAdapter = BlogRecyclerAdapter()
            adapter = blogAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.login_icon,menu)
        return super.onCreateOptionsMenu(menu)
    }

     fun onSubmitAtt(view: View) {

         var indication = true
         for (i in DataSource.createDataSet().listIterator())
         {
             Log.d("Data i Inside Outer for",i.toString())
             for(j in PresentBlogList)
             {             Log.d("Data i Inside Inner for",i.rollNo.toString())
                          Log.d("Data j Inside Inner for",j.rollNo.toString())
                 if(i.rollNo==j.rollNo)
                 {

                     Log.d("Data i Inside if check",i.rollNo.toString())
                     Log.d("Data j Inside if check",j.rollNo.toString())

                     indication=false
                     break
                 }

             }
             if(indication){
                 Log.d("Data i Inside if check",i.rollNo.toString())
//                 Log.d("Data j Inside if check",j.rollNo.toString())
                 AbsentBlogList.add(BlogPost(i.rollNo,i.name))
             }
             Log.d("Data i Inside Outer for",i.rollNo.toString())
//             Log.d("Data j Inside Inner for",j.rollNo.toString())
         }

         val resultPage = Intent(this,AttMarked::class.java)

         startActivity(resultPage)
//         finish()
     }

 }