/*
 * Copyright 2012 Kevin Sawicki <kevinsawicki@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.kevinsawicki.wishlist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Helper for working with a SQLite database
 */
public abstract class DatabaseHelper extends SQLiteOpenHelper {

  /**
   * Transaction interface
   */
  public static interface Transaction {

    /**
     * Perform transaction
     *
     * @param database
     */
    void perform(SQLiteDatabase database);
  }

  /**
   * Create database helper
   *
   * @param context
   * @param name
   * @param factory
   * @param version
   */
  public DatabaseHelper(Context context, String name, CursorFactory factory,
      int version) {
    super(context, name, factory, version);
  }

  /**
   * Get readable database
   *
   * @return readable database or null if it failed to create/open
   */
  protected SQLiteDatabase getReadable() {
    try {
      return getReadableDatabase();
    } catch (SQLiteException e1) {
      // Make second attempt
      try {
        return getReadableDatabase();
      } catch (SQLiteException e2) {
        return null;
      }
    }
  }

  /**
   * Get writable database
   *
   * @return writable database or null if it failed to create/open
   */
  protected SQLiteDatabase getWritable() {
    try {
      return getWritableDatabase();
    } catch (SQLiteException e1) {
      // Make second attempt
      try {
        return getWritableDatabase();
      } catch (SQLiteException e2) {
        return null;
      }
    }
  }

  /**
   * Run given {@link Transaction}
   *
   * @param transaction
   * @return true if transaction completed, false otherwise
   */
  protected boolean run(final Transaction transaction) {
    final SQLiteDatabase database = getWritable();
    if (database == null)
      return false;

    database.beginTransaction();
    try {
      transaction.perform(database);
      database.setTransactionSuccessful();
    } finally {
      database.endTransaction();
    }
    return true;
  }
}
