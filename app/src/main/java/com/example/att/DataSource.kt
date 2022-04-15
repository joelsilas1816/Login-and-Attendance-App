package com.example.att

import com.example.att.models.BlogPost

class DataSource{

    companion object{

        fun createDataSet(): ArrayList<BlogPost>{
            val list = ArrayList<BlogPost>()
            list.add(
                BlogPost(
                    "01",
                    "ABC"

                )
            )
            list.add(
                BlogPost(
                    "02",
"DEF"
                )
            )

            list.add(
                BlogPost(
                    "03",
"GHI"
                )
            )
            list.add(
                BlogPost(
                    "04",
"JKL"
                )
            )
            list.add(
                BlogPost(
                    "05",
"MNO"
                )
            )
            list.add(
                BlogPost(
                    "06",
"PQR"
                )
            )
            list.add(
                BlogPost(
                    "07",
"STU"
                )
            )
            list.add(
                BlogPost(
                    "08",
"VWX"
                )
            )
            list.add(
                BlogPost(
                    "09",
"YZA"
                )
            )
            return list
        }
    }
}