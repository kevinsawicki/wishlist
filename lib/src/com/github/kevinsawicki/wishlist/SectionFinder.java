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

import android.text.TextUtils;
import android.util.SparseIntArray;
import android.widget.SectionIndexer;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Section finder helper
 */
public class SectionFinder implements SectionIndexer {

  private final Set<Object> sections = new LinkedHashSet<Object>();

  private final SparseIntArray sectionPositions = new SparseIntArray();

  private final SparseIntArray itemSections = new SparseIntArray();

  private int index = 0;

  /**
   * Clear all sections
   *
   * @return this finder
   */
  public SectionFinder clear() {
    sections.clear();
    sectionPositions.clear();
    itemSections.clear();
    index = 0;
    return this;
  }

  /**
   * Get section for item
   * <p>
   * The default behavior is to use the first character from the item's
   * {@link #toString()} method
   *
   * @param item
   * @return section
   */
  protected Object getSection(final Object item) {
    final String string = item.toString();
    if (!TextUtils.isEmpty(string))
      return Character.toUpperCase(string.charAt(0));
    else
      return '?';
  }

  private void addSection(Object section) {
    int count = sections.size();
    if (sections.add(section))
      sectionPositions.put(count, index);
  }

  private void addItem(Object item) {
    itemSections.put(index, sections.size());
    index++;
  }

  /**
   * Index items by section returned from {@link #getSection(Object)}
   *
   * @param items
   * @return this finder
   */
  public SectionFinder index(Object... items) {
    for (Object item : items) {
      addSection(getSection(item));
      addItem(item);
    }
    return this;
  }

  /**
   * Add items to given section
   *
   * @param section
   * @param items
   * @return this finder
   */
  public SectionFinder add(final Object section, final Object... items) {
    addSection(section);
    for (Object item : items)
      addItem(item);
    return this;
  }

  public int getPositionForSection(final int section) {
    return sectionPositions.get(section);
  }

  public int getSectionForPosition(final int position) {
    return itemSections.get(position);
  }

  public Object[] getSections() {
    return sections.toArray();
  }
}
