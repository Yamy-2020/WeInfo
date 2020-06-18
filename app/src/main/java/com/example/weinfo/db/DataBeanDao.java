package com.example.weinfo.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.weinfo.bean.DataBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DATA_BEAN".
*/
public class DataBeanDao extends AbstractDao<DataBean, Integer> {

    public static final String TABLENAME = "DATA_BEAN";

    /**
     * Properties of entity DataBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property CourseId = new Property(0, int.class, "courseId", false, "COURSE_ID");
        public final static Property Id = new Property(1, int.class, "id", true, "ID");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Order = new Property(3, int.class, "order", false, "ORDER");
        public final static Property ParentChapterId = new Property(4, int.class, "parentChapterId", false, "PARENT_CHAPTER_ID");
        public final static Property UserControlSetTop = new Property(5, boolean.class, "userControlSetTop", false, "USER_CONTROL_SET_TOP");
        public final static Property Visible = new Property(6, int.class, "visible", false, "VISIBLE");
        public final static Property IsInterested = new Property(7, boolean.class, "isInterested", false, "IS_INTERESTED");
    }


    public DataBeanDao(DaoConfig config) {
        super(config);
    }
    
    public DataBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DATA_BEAN\" (" + //
                "\"COURSE_ID\" INTEGER NOT NULL ," + // 0: courseId
                "\"ID\" INTEGER PRIMARY KEY NOT NULL ," + // 1: id
                "\"NAME\" TEXT," + // 2: name
                "\"ORDER\" INTEGER NOT NULL ," + // 3: order
                "\"PARENT_CHAPTER_ID\" INTEGER NOT NULL ," + // 4: parentChapterId
                "\"USER_CONTROL_SET_TOP\" INTEGER NOT NULL ," + // 5: userControlSetTop
                "\"VISIBLE\" INTEGER NOT NULL ," + // 6: visible
                "\"IS_INTERESTED\" INTEGER NOT NULL );"); // 7: isInterested
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DATA_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DataBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getCourseId());
        stmt.bindLong(2, entity.getId());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
        stmt.bindLong(4, entity.getOrder());
        stmt.bindLong(5, entity.getParentChapterId());
        stmt.bindLong(6, entity.getUserControlSetTop() ? 1L: 0L);
        stmt.bindLong(7, entity.getVisible());
        stmt.bindLong(8, entity.getIsInterested() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DataBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getCourseId());
        stmt.bindLong(2, entity.getId());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
        stmt.bindLong(4, entity.getOrder());
        stmt.bindLong(5, entity.getParentChapterId());
        stmt.bindLong(6, entity.getUserControlSetTop() ? 1L: 0L);
        stmt.bindLong(7, entity.getVisible());
        stmt.bindLong(8, entity.getIsInterested() ? 1L: 0L);
    }

    @Override
    public Integer readKey(Cursor cursor, int offset) {
        return cursor.getInt(offset + 1);
    }    

    @Override
    public DataBean readEntity(Cursor cursor, int offset) {
        DataBean entity = new DataBean( //
            cursor.getInt(offset + 0), // courseId
            cursor.getInt(offset + 1), // id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.getInt(offset + 3), // order
            cursor.getInt(offset + 4), // parentChapterId
            cursor.getShort(offset + 5) != 0, // userControlSetTop
            cursor.getInt(offset + 6), // visible
            cursor.getShort(offset + 7) != 0 // isInterested
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DataBean entity, int offset) {
        entity.setCourseId(cursor.getInt(offset + 0));
        entity.setId(cursor.getInt(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setOrder(cursor.getInt(offset + 3));
        entity.setParentChapterId(cursor.getInt(offset + 4));
        entity.setUserControlSetTop(cursor.getShort(offset + 5) != 0);
        entity.setVisible(cursor.getInt(offset + 6));
        entity.setIsInterested(cursor.getShort(offset + 7) != 0);
     }
    
    @Override
    protected final Integer updateKeyAfterInsert(DataBean entity, long rowId) {
        return entity.getId();
    }
    
    @Override
    public Integer getKey(DataBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DataBean entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
