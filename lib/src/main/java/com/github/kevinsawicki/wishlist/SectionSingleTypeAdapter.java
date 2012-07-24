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
import android.view.LayoutInflater;
import android.widget.SectionIndexer;

/**
 * @param <V>
 */
public abstract class SectionSingleTypeAdapter<V> extends SingleTypeAdapter<V>
    implements SectionIndexer {

  private final SectionFinder sections = new SectionFinder();

  /**
   * @param activity
   * @param layoutResourceId
   */
  public SectionSingleTypeAdapter(Activity activity, int layoutResourceId) {
    super(activity, layoutResourceId);
  }

  /**
   * @param context
   * @param layoutResourceId
   */
  public SectionSingleTypeAdapter(Context context, int layoutResourceId) {
    super(context, layoutResourceId);
  }

  /**
   * @param inflater
   * @param layoutResourceId
   */
  public SectionSingleTypeAdapter(LayoutInflater inflater, int layoutResourceId) {
    super(inflater, layoutResourceId);
  }

  @Override
  public void setItems(Object[] items) {
    super.setItems(items);

    sections.clear().index(items);
  }

  public int getPositionForSection(int section) {
    return sections.getPositionForSection(section);
  }

  public int getSectionForPosition(int position) {
    return sections.getSectionForPosition(position);
  }

  public Object[] getSections() {
    return sections.getSections();
  }
}
