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

import java.text.NumberFormat;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Base adapter
 */
public abstract class TypeAdapter extends BaseAdapter {

  /**
   * Number formatter for integers
   */
  protected static final NumberFormat FORMAT_INT = NumberFormat
      .getIntegerInstance();

  /**
   * View being updated
   */
  protected View view;

  /**
   * Get text view with given id
   *
   * @param parentView
   * @param childViewId
   * @return text view
   */
  protected TextView textView(final View parentView, final int childViewId) {
    return (TextView) parentView.getTag(childViewId);
  }

  /**
   * Get image view with given id
   *
   * @param parentView
   * @param childViewId
   * @return image view
   */
  protected ImageView imageView(final View parentView, final int childViewId) {
    return (ImageView) parentView.getTag(childViewId);
  }

  /**
   * Get view with given id
   *
   * @param parentView
   * @param childViewId
   * @return view
   */
  protected View view(final View parentView, final int childViewId) {
    return (View) parentView.getTag(childViewId);
  }

  /**
   * Set text on text view with given id
   *
   * @param parentView
   * @param childViewId
   * @param text
   * @return text view
   */
  protected TextView setText(final View parentView, final int childViewId,
      final CharSequence text) {
    final TextView textView = textView(parentView, childViewId);
    textView.setText(text);
    return textView;
  }

  /**
   * Set text on text view with given id to string at given resource id
   *
   * @param parentView
   * @param childViewId
   * @param resourceId
   * @return text view
   */
  protected TextView setText(final View parentView, final int childViewId,
      final int resourceId) {
    final TextView textView = textView(parentView, childViewId);
    textView.setText(resourceId);
    return textView;
  }

  /**
   * Set text on text view to be formatted version of given integer number
   * <p>
   * This method uses the formatter returned from
   * {@link NumberFormat#getIntegerInstance()}
   *
   * @param parentView
   * @param childViewId
   * @param number
   * @return text view
   */
  protected TextView setNumber(final View parentView, final int childViewId,
      final long number) {
    final TextView textView = textView(parentView, childViewId);
    textView.setText(FORMAT_INT.format(number));
    return textView;
  }

  /**
   * Get child view
   *
   * @param parentView
   * @param childViewId
   * @param childViewClass
   * @return child view
   */
  @SuppressWarnings("unchecked")
  protected <T> T getView(final View parentView, final int childViewId,
      final Class<T> childViewClass) {
    return (T) parentView.getTag(childViewId);
  }

  /**
   * Set child view as gone or visible
   *
   * @param parentView
   * @param childViewId
   * @param gone
   * @return child view
   */
  protected View setGone(final View parentView, final int childViewId,
      boolean gone) {
    return ViewUtils.setGone(view(parentView, childViewId), gone);
  }

  /**
   * Create array with given base ids and additional ids
   *
   * @param base
   * @param ids
   * @return extended array
   */
  protected int[] join(final int[] base, final int... ids) {
    if (ids == null || ids.length == 0)
      return base;

    final int[] extended = new int[base.length + ids.length];
    System.arraycopy(base, 0, extended, 0, base.length);
    System.arraycopy(ids, 0, extended, base.length, ids.length);
    return extended;
  }

  /**
   * Initialize view by binding indexed child views to tags on the root view
   * <p>
   * Sub-classes may override this method but must call super
   *
   * @param view
   * @param children
   * @return view
   */
  protected View initialize(final View view, final int[] children) {
    for (int id : children) {
      View child = view.findViewById(id);
      if (child != null)
        view.setTag(id, child);
    }
    return view;
  }

  /**
   * Get text view with given id
   *
   * @param childViewId
   * @return text view
   */
  protected TextView textView(final int childViewId) {
    return textView(view, childViewId);
  }

  /**
   * Get image view with given id
   *
   * @param childViewId
   * @return image view
   */
  protected ImageView imageView(final int childViewId) {
    return imageView(view, childViewId);
  }

  /**
   * Get view with given id
   *
   * @param childViewId
   * @return view
   */
  protected View view(final int childViewId) {
    return view(view, childViewId);
  }

  /**
   * Set text on text view with given id
   *
   * @param childViewId
   * @param text
   * @return text view
   */
  protected TextView setText(final int childViewId, final CharSequence text) {
    return setText(view, childViewId, text);
  }

  /**
   * Set text on text view with given id to given string resource
   *
   * @param childViewId
   * @param resourceId
   * @return text view
   */
  protected TextView setText(final int childViewId, final int resourceId) {
    return setText(view, childViewId, resourceId);
  }

  /**
   * Set text on text view to be formatted version of given integer number
   * <p>
   * This method uses the formatter returned from
   * {@link NumberFormat#getIntegerInstance()}
   *
   * @param childViewId
   * @param number
   * @return text view
   */
  protected TextView setNumber(final int childViewId, final long number) {
    return setNumber(view, childViewId, number);
  }

  /**
   * Get child view
   *
   * @param childViewId
   * @param childViewClass
   * @return child view
   */
  protected <T> T getView(final int childViewId, final Class<T> childViewClass) {
    return getView(view, childViewId, childViewClass);
  }

  /**
   * Set child view as gone or visible
   *
   * @param childViewId
   * @param gone
   * @return child view
   */
  protected View setGone(final int childViewId, boolean gone) {
    return setGone(view, childViewId, gone);
  }
}
