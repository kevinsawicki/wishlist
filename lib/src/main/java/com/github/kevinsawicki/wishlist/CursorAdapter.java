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
import android.database.Cursor;

/**
 * Cursor adapter with helpers
 */
public abstract class CursorAdapter extends
    android.support.v4.widget.CursorAdapter {

  /**
   * Updater for current view
   */
  protected final ViewUpdater updater;

  /**
   * @param context
   * @param c
   * @param autoRequery
   */
  public CursorAdapter(Context context, Cursor c, boolean autoRequery) {
    super(context, c, autoRequery);

    updater = new ViewUpdater();
  }

  /**
   * @param context
   * @param c
   * @param flags
   */
  public CursorAdapter(Context context, Cursor c, int flags) {
    super(context, c, flags);

    updater = new ViewUpdater();
  }
}
