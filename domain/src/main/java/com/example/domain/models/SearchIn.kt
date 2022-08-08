package com.example.domain.models

enum class SearchIn(var title:String,var _in:String) {
    Title("Title","title"),
    Description("Description","description"),
    Content("Content","content"),
    Custom("",""),
    All("All","title,description,content");

    operator fun plus(content: SearchIn):String {
        return if (content.title == All.title || this.title == All.title ){
            All.title
        }else{
            this.title + " " + content.title
        }
    }

}