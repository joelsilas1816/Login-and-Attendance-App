package com.example.att

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.att.models.BlogPost
import kotlinx.android.synthetic.main.row_style.view.*

//var i =0

class  BlogRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //private val TAG: String = "AppDebug"

    private var items: List<BlogPost> = ArrayList()
//    var counter=0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return BlogViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.row_style, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BlogViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(blogList: List<BlogPost>) {
        items = blogList
    }

    class BlogViewHolder
    constructor(
            itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        var blog_title: TextView = itemView.blog_title
        var blog_author: TextView = itemView.blog_author
        var checkingBox : CheckBox = itemView.chkbox
        var myLinearLayout: LinearLayout = itemView.container1

////        var counter = 0
//////////////
//        class presentStudentsNameList{
//
//            companion object{
//
//                fun presentStudents() {
//
//                    var arrList: ArrayList<BlogPost>
//                }
//
//            }
//        }
//   ///////////////
        fun bind(blogPost: BlogPost) {

//            val requestOptions = RequestOptions()
//                .placeholder(R.drawable.ic_launcher_background)
//                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
//                .applyDefaultRequestOptions(requestOptions)
            // .load(blogPost.rollNo)
            blog_title.text = blogPost.rollNo
            blog_author.text = blogPost.name

//           Log.i("OnClick", "No color change")

            calling()
        }

        fun calling() {
            checkingBox.setOnClickListener {

//                val view : View = layout.inflate(R.layout.activity_student_name_list,null)
//                var presStudCount : TextView = view.findViewById(R.id.pres)

                if (checkingBox.isChecked)
                {
                    counter += 1
                    blog_title.setBackgroundColor(Color.parseColor("#96F19B"))
                    blog_author.setBackgroundColor(Color.parseColor("#96F19B"))
                    myLinearLayout.setBackgroundColor(Color.parseColor("#96F19B"))

                    PresentBlogList.add(BlogPost(blog_title.text.toString(),blog_author.text.toString()))

//                    Log.d("TESTING", BlogPost)
                }
                else
                {
                    counter -= 1
                    blog_title.setBackgroundColor(Color.parseColor("#ffffff"))
                    blog_author.setBackgroundColor(Color.parseColor("#ffffff"))
                    myLinearLayout.setBackgroundColor(Color.parseColor("#ffffff"))
                    PresentBlogList.remove(BlogPost(blog_title.text.toString(),blog_author.text.toString()))
                }
//            presStudCount.text= counter.toString()
            }
                }


            }
}

class mutableListOf<String> {

}

//class SimpleCallback : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
//    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//
//        var position : Int = viewHolder.adapterPosition
//
//        when(direction){
//            ItemTouchHelper.LEFT -> holder.
//            ItemTouchHelper.RIGHT -> position.red
//            // else ->
//        }
//    }

