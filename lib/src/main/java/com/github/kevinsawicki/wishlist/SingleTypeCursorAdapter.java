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

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Cursor adapter for a single view type
 */
public abstract class SingleTypeCursorAdapter extends CursorAdapter {

  private final LayoutInflater inflater;

  private final int layout;

  private final int[] children;

  /**
   * Create adapter
   *
   * @param activity
   * @param cursor
   * @param flags
   * @param layoutResourceId
   */
  public SingleTypeCursorAdapter(final Activity activity, final Cursor cursor,
      final int flags, final int layoutResourceId) {
    this(activity, cursor, flags, activity.getLayoutInflater(),
        layoutResourceId);
  }

  /**
   * Create adapter
   *
   * @param context
   * @param cursor
   * @param flags
   * @param layoutResourceId
   */
  public SingleTypeCursorAdapter(final Context context, final Cursor cursor,
      final int flags, final int layoutResourceId) {
    this(context, cursor, flags, LayoutInflater.from(context), layoutResourceId);
  }

  /**
   * Create adapter
   *
   * @param context
   * @param cursor
   * @param flags
   *
   * @param inflater
   * @param layoutResourceId
   */
  public SingleTypeCursorAdapter(final Context context, final Cursor cursor,
      final int flags, final LayoutInflater inflater, final int layoutResourceId) {
    super(context, cursor, flags);

    this.inflater = inflater;
    this.layout = layoutResourceId;

    int[] childIds = getChildViewIds();
    if (childIds == null)
      childIds = new int[0];
    children = childIds;
  }

  /**
   * Get child view ids to store
   * <p>
   * The index of each id in the returned array should be used when using the
   * helpers to update a specific child view
   *
   * @return ids
   */
  protected abstract int[] getChildViewIds();

  /**
   * Initialize view
   *
   * @param view
   * @return view
   */
  protected View initialize(final View view) {
    return updater.initialize(view, children);
  }

  @Override
  public void bindView(View view, Context context, Cursor cursor) {
    updater.setCurrentView(view);
  }

  @Override
  public View newView(Context context, Cursor cursor, ViewGroup parent) {
    return initialize(inflater.inflate(layout, null));
  }
}
