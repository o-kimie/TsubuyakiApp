package com.example.mika.tsubuyakiapp;

import com.orm.SugarRecord;

/**
 * Created by Mika on 2016/05/11.
 */
public class Tsubuyaki extends SugarRecord {

//    ID（連番）
    public long id;

//    コメント
    public String comment;

    public Tsubuyaki(){}

    public Tsubuyaki(long id,String comment){
        this.id = id;
        this.comment = comment;
    }


}
