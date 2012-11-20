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

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * Base adapter
 */
public abstract class TypeAdapter extends BaseAdapter {

  /**
   * Updater for current view
   */
  protected final ViewUpdater updater = new ViewUpdater();

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
    return updater.initialize(view, children);
  }

  /**
   * Set current view that is currently being updated
   *
   * @param view
   */
  protected void setCurrentView(final View view) {
    updater.setCurrentView(view);
  }

  /**
   * Get indexed children
   *
   * @param parentView
   * @return children
   */
  protected View[] getChildren(final View parentView) {
    return updater.getChildren(parentView);
  }

  /**
   * Get text view at given index
   *
   * @param childViewIndex
   * @return text view
   */
  protected TextView textView(final int childViewIndex) {
    return updater.textView(childViewIndex);
  }

  /**
   * Get text view at given index
   *
   * @param parentView
   * @param childViewIndex
   * @return text view
   */
  protected TextView textView(final View parentView, final int childViewIndex) {
    return updater.textView(parentView, childViewIndex);
  }

  /**
   * Get image view at given index
   *
   * @param childViewIndex
   * @return image view
   */
  protected ImageView imageView(final int childViewIndex) {
    return updater.imageView(childViewIndex);
  }

  /**
   * Get image view at given index
   *
   * @param parentView
   * @param childViewIndex
   * @return image view
   */
  protected ImageView imageView(final View parentView, final int childViewIndex) {
    return updater.imageView(parentView, childViewIndex);
  }

  /**
   * Get view at given index
   *
   * @param childViewIndex
   * @return view
   */
  protected <V extends View> V view(final int childViewIndex) {
    return updater.view(childViewIndex);
  }

  /**
   * Get view at given index
   *
   * @param parentView
   * @param childViewIndex
   * @return view
   */
  protected <V extends View> V view(final View parentView,
      final int childViewIndex) {
    return updater.view(parentView, childViewIndex);
  }

  /**
   * Set text on text view at given index
   *
   * @param childViewIndex
   * @param text
   * @return text view
   */
  protected TextView setText(final int childViewIndex, final CharSequence text) {
    return updater.setText(childViewIndex, text);
  }

  /**
   * Set text on text view at given index
   *
   * @param parentView
   * @param childViewIndex
   * @param text
   * @return text view
   */
  protected TextView setText(final View parentView, final int childViewIndex,
      final CharSequence text) {
    return updater.setText(parentView, childViewIndex, text);
  }

  /**
   * Set text on text view at index to string resource
   *
   * @param childViewIndex
   * @param resourceId
   * @return text view
   */
  protected TextView setText(final int childViewIndex, final int resourceId) {
    return updater.setText(childViewIndex, resourceId);
  }

  /**
   * Set text on text view at index to string resource
   *
   * @param parentView
   * @param childViewIndex
   * @param resourceId
   * @return text view
   */
  protected TextView setText(final View parentView, final int childViewIndex,
      final int resourceId) {
    return updater.setText(parentView, childViewIndex, resourceId);
  }

  /**
   * Set text on text view to be formatted version of given integer number
   * <p>
   * This method uses the formatter returned from
   * {@link NumberFormat#getIntegerInstance()}
   *
   * @param childViewIndex
   * @param number
   * @return text view
   */
  protected TextView setNumber(final int childViewIndex, final long number) {
    return updater.setNumber(childViewIndex, number);
  }

  /**
   * Set text on text view to be formatted version of given integer number
   * <p>
   * This method uses the formatter returned from
   * {@link NumberFormat#getIntegerInstance()}
   *
   * @param parentView
   * @param childViewIndex
   * @param number
   * @return text view
   */
  protected TextView setNumber(final View parentView, final int childViewIndex,
      final long number) {
    return updater.setNumber(parentView, childViewIndex, number);
  }

  /**
   * Get child view
   *
   * @param childViewIndex
   * @param childViewClass
   * @return child view
   */
  protected <T> T getView(final int childViewIndex,
      final Class<T> childViewClass) {
    return updater.getView(childViewIndex, childViewClass);
  }

  /**
   * Get child view
   *
   * @param parentView
   * @param childViewIndex
   * @param childViewClass
   * @return child view
   */
  protected <T> T getView(final View parentView, final int childViewIndex,
      final Class<T> childViewClass) {
    return updater.getView(parentView, childViewIndex, childViewClass);
  }

  /**
   * Set child view as gone or visible
   *
   * @param childViewIndex
   * @param gone
   * @return child view
   */
  protected View setGone(final int childViewIndex, boolean gone) {
    return updater.setGone(childViewIndex, gone);
  }

  /**
   * Set child view as gone or visible
   *
   * @param parentView
   * @param childViewIndex
   * @param gone
   * @return child view
   */
  protected View setGone(final View parentView, final int childViewIndex,
      boolean gone) {
    return updater.setGone(parentView, childViewIndex, gone);
  }

  /**
   * Set the checked state of the {@link CompoundButton} with at index
   *
   * @param childViewIndex
   * @param checked
   * @return check box
   */
  protected CompoundButton setChecked(final int childViewIndex,
      final boolean checked) {
    return updater.setChecked(childViewIndex, checked);
  }

  /**
   * Set the checked state of the {@link CompoundButton} with at index
   *
   * @param parentView
   * @param childViewIndex
   * @param checked
   * @return check box
   */
  protected CompoundButton setChecked(final View parentView,
      final int childViewIndex, final boolean checked) {
    return updater.setChecked(parentView, childViewIndex, checked);
  }

  /**
   * Set the text on the text view if it is non-empty and make the view gone if
   * it is empty
   *
   * @param childViewIndex
   * @param text
   * @return text view
   */
  public TextView setVisibleText(final int childViewIndex,
      final CharSequence text) {
    return updater.setVisibleText(childViewIndex, text);
  }

  /**
   * Set the text on the text view if it is non-empty and make the view gone if
   * it is empty
   *
   * @param parentView
   *
   * @param childViewIndex
   * @param text
   * @return text view
   */
  public TextView setVisibleText(final View parentView,
      final int childViewIndex, final CharSequence text) {
    return updater.setVisibleText(parentView, childViewIndex, text);
  }

  /**
   * Set relative time span on text view
   *
   * @param childViewIndex
   * @param time
   * @return text view
   */
  public TextView setRelativeTimeSpan(final int childViewIndex, final long time) {
    return updater.setRelativeTimeSpan(childViewIndex, time);
  }

  /**
   * Set relative time span on text view
   *
   * @param parentView
   * @param childViewIndex
   * @param time
   * @return text view
   */
  public TextView setRelativeTimeSpan(final View parentView,
      final int childViewIndex, final long time) {
    return updater.setRelativeTimeSpan(parentView, childViewIndex, time);
  }
}
