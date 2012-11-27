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
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Adapter for lists where only multiple view types are needed
 */
public abstract class MultiTypeAdapter extends TypeAdapter {

  private static class Item {

    public final int type;

    public final Object item;

    private Item(final int type, final Object item) {
      this.type = type;
      this.item = item;
    }

    @Override
    public int hashCode() {
      return item.hashCode();
    }
  }

  private final LayoutInflater inflater;

  private final int[] layout;

  private final int[][] children;

  private final List<Item> items = new ArrayList<Item>();

  /**
   * Create adapter
   * 
   * @param activity
   */
  public MultiTypeAdapter(final Activity activity) {
    this(activity.getLayoutInflater());
  }

  /**
   * Create adapter
   * 
   * @param context
   */
  public MultiTypeAdapter(final Context context) {
    this(LayoutInflater.from(context));
  }

  /**
   * Create adapter
   * 
   * @param inflater
   */
  public MultiTypeAdapter(final LayoutInflater inflater) {
    this.inflater = inflater;

    int[] empty = new int[0];
    int count = getViewTypeCount();
    children = new int[count][];
    layout = new int[count];
    for (int i = 0; i < count; i++) {
      int[] ids = getChildViewIds(i);
      if (ids == null)
        ids = empty;
      children[i] = ids;
      layout[i] = getChildLayoutId(i);
    }
  }

  /**
   * Clear all items
   * 
   * @return this adapter
   */
  public MultiTypeAdapter clear() {
    items.clear();

    notifyDataSetChanged();
    return this;
  }

  /**
   * Add item to adapter registered as the given type
   * 
   * @param type
   * @param item
   * @return this adapter
   */
  public MultiTypeAdapter addItem(final int type, final Object item) {
    items.add(new Item(type, item));

    notifyDataSetChanged();
    return this;
  }

  /**
   * Add items to adapter registered as the given type
   * 
   * @param type
   * @param items
   * @return this adapter
   */
  public MultiTypeAdapter addItems(final int type, final Object[] items) {
    if (items == null || items.length == 0)
      return this;

    for (Object item : items)
      this.items.add(new Item(type, item));

    notifyDataSetChanged();
    return this;
  }

  /**
   * Add items to adapter registered as the given type
   * 
   * @param type
   * @param items
   * @return this adapter
   */
  public MultiTypeAdapter addItems(final int type, final Collection<?> items) {
    if (items == null || items.isEmpty())
      return this;

    for (Object item : items)
      this.items.add(new Item(type, item));

    notifyDataSetChanged();
    return this;
  }

  /**
   * Remove item at position
   * 
   * @param position
   * @return this adapter
   */
  public MultiTypeAdapter removeItem(final int position) {
    if (position > 0 && position < items.size()
        && items.remove(position) != null)
      notifyDataSetChanged();
    return this;
  }

  /**
   * Get layout id for type
   * 
   * @param type
   * @return layout id
   */
  protected abstract int getChildLayoutId(int type);

  /**
   * Get child view ids for type
   * <p>
   * The index of each id in the returned array should be used when using the
   * helpers to update a specific child view
   * 
   * @param type
   * @return array of view ids
   */
  protected abstract int[] getChildViewIds(int type);

  @Override
  public int getCount() {
    return items.size();
  }

  @Override
  public Object getItem(final int position) {
    return items.get(position).item;
  }

  @Override
  public long getItemId(final int position) {
    return items.get(position).hashCode();
  }

  @Override
  public int getItemViewType(final int position) {
    return items.get(position).type;
  }

  /**
   * Update view for item
   * 
   * @param position
   * @param view
   * @param item
   * @param type
   */
  protected void update(final int position, final View view, final Object item,
      final int type) {
    setCurrentView(view);
    update(position, item, type);
  }

  /**
   * Initialize view for given type
   * <p>
   * Sub-classes may override this method but should call super so that tags are
   * properly set on the given root view.
   * 
   * @param type
   * @param view
   * @return view
   */
  protected View initialize(final int type, final View view) {
    return super.initialize(view, children[type]);
  }

  /**
   * Update view for item
   * 
   * @param position
   * @param item
   * @param type
   */
  protected abstract void update(int position, Object item, int type);

  public View getView(final int position, View convertView,
      final ViewGroup parent) {
    int type = getItemViewType(position);
    if (convertView == null)
      convertView = initialize(type, inflater.inflate(layout[type], null));
    update(position, convertView, getItem(position), type);
    return convertView;
  }
}
