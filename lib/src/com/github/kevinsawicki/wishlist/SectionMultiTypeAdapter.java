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
 * Type adapter with section indexing according to English alphabet
 */
public abstract class SectionMultiTypeAdapter extends MultiTypeAdapter
    implements SectionIndexer {

  private final SectionFinder sections = new SectionFinder();

  /**
   * @param activity
   */
  public SectionMultiTypeAdapter(final Activity activity) {
    super(activity);
  }

  /**
   * @param context
   */
  public SectionMultiTypeAdapter(final Context context) {
    super(context);
  }

  /**
   * @param inflater
   */
  public SectionMultiTypeAdapter(final LayoutInflater inflater) {
    super(inflater);
  }

  @Override
  public MultiTypeAdapter clear() {
    sections.clear();

    return super.clear();
  }

  /**
   * Add items mapped to given letter
   *
   * @param sectionType
   * @param section
   * @param itemType
   * @param items
   * @return this adapter
   */
  public SectionMultiTypeAdapter addItems(final int sectionType,
      final Object section, final int itemType, final Object[] items) {
    sections.add(section, section).add(section, items);

    super.addItem(sectionType, section);
    super.addItems(itemType, items);

    return this;
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
