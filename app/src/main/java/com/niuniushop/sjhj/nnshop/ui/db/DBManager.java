package com.niuniushop.sjhj.nnshop.ui.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.niuniushop.sjhj.nnshop.config.Config;
import com.niuniushop.sjhj.nnshop.model.ProuctDataModel;
import com.niuniushop.sjhj.nnshop.model.RecommendContentModel;
import com.niuniushop.sjhj.nnshop.utils.BaseUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 */

public class DBManager {

    private static DBManager singleton;
    private DBHelper helper;
    private SQLiteDatabase db;

    /**
     * 私有构造器
     *
     * @param context
     */
    private DBManager(Context context) {
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public static DBManager getManager(Context context) {
        if (singleton == null) {
            synchronized (DBManager.class) {
                singleton = new DBManager(context);
            }
        }
        return singleton;
    }

    /**
     * 删除所有商品
     */
    public void removeAllProducts() {
        db.beginTransaction();
        db.execSQL("delete from " + MySql.ProductTable + "");
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    /**
     * 添加所有商品
     */
    public void addAllProducts(List<ProuctDataModel> listDatas) {
        db.beginTransaction();
        for (ProuctDataModel data : listDatas) {
            addProduct(data);
        }
        db.setTransactionSuccessful();
        db.endTransaction();

    }

    /**
     * 添加商品
     */
    public void addProduct(ProuctDataModel data) {
        db.execSQL("insert into " + MySql.ProductTable + " values(null,?,?,?,?,?,?,?,?)",
                new Object[]{BaseUtils.tranLowCase(data.getName()), data.getImg(), data.getPrice(), data.getType(), data.getId(), data.getColor(), data.getStorage(), data.getCarrieroperator()});
    }

    /**
     * 从数据库获取推荐商品列表
     *
     * @return
     */
    public List<RecommendContentModel> getRecommendContentsFromDB() {
        List<RecommendContentModel> listDatas = new ArrayList<RecommendContentModel>();
        Cursor cursor = db.rawQuery("select * from " + MySql.ProductTable + " order by productType asc", null);
        if (cursor != null && cursor.moveToFirst()) {

            for (int i = 0; i < Config.recommdendTips.length; i++) {
                RecommendContentModel recommendTip = new RecommendContentModel();
                recommendTip.setJudgeType(true);
                recommendTip.setTip(Config.recommdendTips[i]);
                listDatas.add(recommendTip);
                for (int j = 0; j < 4; j++) {
                    int type = cursor.getInt(cursor.getColumnIndex("productType"));
                    if (i == type) {
                        RecommendContentModel recommendContent = new RecommendContentModel();
                        recommendContent.setPid(cursor.getInt(cursor.getColumnIndex("pid")));
                        recommendContent.setUid(0);
                        recommendContent.setPrice(cursor.getFloat(cursor.getColumnIndex("price")));
                        recommendContent.setImageUrl(cursor.getString(cursor.getColumnIndex("imgPath")));
                        recommendContent.setTitle(cursor.getString(cursor.getColumnIndex("productName")));
                        recommendContent.setType(cursor.getInt(cursor.getColumnIndex("productType")));
                        recommendContent.setColor(cursor.getInt(cursor.getColumnIndex("color")));
                        recommendContent.setStorage(cursor.getInt(cursor.getColumnIndex("storage")));
                        recommendContent.setCarrieroperator(cursor.getInt(cursor.getColumnIndex("carrieroperator")));
                        listDatas.add(recommendContent);
                        if (!cursor.isLast()) {
                            cursor.moveToNext();
                        } else {
                            break;
                        }
                    } else if (i > type) {
                        if (!cursor.isLast()) {
                            cursor.moveToNext();
                        }
                    }

                }

            }
        }
        cursor.close();
        RecommendContentModel recommendListContent = new RecommendContentModel();
        recommendListContent.setListType(true);
        listDatas.add(0, recommendListContent);
        return listDatas;
    }
}
