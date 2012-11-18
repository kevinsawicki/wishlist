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
import android.text.format.DateUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * Updater for child views indexed from a root view
 */
public class ViewUpdater {

  /**
   * Number formatter for integers
   */
  public static final NumberFormat FORMAT_INT = NumberFormat
      .getIntegerInstance();

  /**
   * Root view currently being updated
   */
  public View view;

  /**
   * Child views currently being updated
   */
  public View[] childViews;

  /**
   * Initialize view by binding indexed child views to tags on the root view
   * <p>
   * Sub-classes may override this method but must call super
   *
   * @param view
   * @param children
   * @return view
   */
  public View initialize(final View view, final int[] children) {
    final View[] views = new View[children.length];
    for (int i = 0; i < children.length; i++)
      views[i] = view.findViewById(children[i]);
    view.setTag(views);
    this.view = view;
    childViews = views;
    return view;
  }

  /**
   * Set current view that is currently being updated
   *
   * @param view
   */
  public void setCurrentView(final View view) {
    this.view = view;
    childViews = getChildren(view);
  }

  /**
   * Get indexed children
   *
   * @param parentView
   * @return children
   */
  public View[] getChildren(final View parentView) {
    return (View[]) parentView.getTag();
  }

  /**
   * Get text view at given index
   *
   * @param childViewIndex
   * @return text view
   */
  public TextView textView(final int childViewIndex) {
    return (TextView) childViews[childViewIndex];
  }

  /**
   * Get text view at given index
   *
   * @param parentView
   * @param childViewIndex
   * @return text view
   */
  public TextView textView(final View parentView, final int childViewIndex) {
    return (TextView) getChildren(parentView)[childViewIndex];
  }

  /**
   * Get image view at given index
   *
   * @param childViewIndex
   * @return image view
   */
  public ImageView imageView(final int childViewIndex) {
    return (ImageView) childViews[childViewIndex];
  }

  /**
   * Get image view at given index
   *
   * @param parentView
   * @param childViewIndex
   * @return image view
   */
  public ImageView imageView(final View parentView, final int childViewIndex) {
    return (ImageView) getChildren(parentView)[childViewIndex];
  }

  /**
   * Get view at given index
   *
   * @param childViewIndex
   * @return view
   */
  @SuppressWarnings("unchecked")
  public <V extends View> V view(final int childViewIndex) {
    return (V) childViews[childViewIndex];
  }

  /**
   * Get view at given index
   *
   * @param parentView
   * @param childViewIndex
   * @return view
   */
  @SuppressWarnings("unchecked")
  public <V extends View> V view(final View parentView,
      final int childViewIndex) {
    return (V) getChildren(parentView)[childViewIndex];
  }

  /**
   * Set text on text view at given index
   *
   * @param childViewIndex
   * @param text
   * @return text view
   */
  public TextView setText(final int childViewIndex, final CharSequence text) {
    final TextView textView = textView(childViewIndex);
    textView.setText(text);
    return textView;
  }

  /**
   * Set text on text view at given index
   *
   * @param parentView
   * @param childViewIndex
   * @param text
   * @return text view
   */
  public TextView setText(final View parentView, final int childViewIndex,
      final CharSequence text) {
    final TextView textView = textView(parentView, childViewIndex);
    textView.setText(text);
    return textView;
  }

  /**
   * Set text on text view at index to string resource
   *
   * @param childViewIndex
   * @param resourceId
   * @return text view
   */
  public TextView setText(final int childViewIndex, final int resourceId) {
    final TextView textView = textView(childViewIndex);
    textView.setText(resourceId);
    return textView;
  }

  /**
   * Set text on text view at index to string resource
   *
   * @param parentView
   * @param childViewIndex
   * @param resourceId
   * @return text view
   */
  public TextView setText(final View parentView, final int childViewIndex,
      final int resourceId) {
    final TextView textView = textView(parentView, childViewIndex);
    textView.setText(resourceId);
    return textView;
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
  public TextView setNumber(final int childViewIndex, final long number) {
    final TextView textView = textView(childViewIndex);
    textView.setText(FORMAT_INT.format(number));
    return textView;
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
  public TextView setNumber(final View parentView, final int childViewIndex,
      final long number) {
    final TextView textView = textView(parentView, childViewIndex);
    textView.setText(FORMAT_INT.format(number));
    return textView;
  }

  /**
   * Get child view
   *
   * @param childViewIndex
   * @param childViewClass
   * @return child view
   */
  @SuppressWarnings("unchecked")
  public <T> T getView(final int childViewIndex,
      final Class<T> childViewClass) {
    return (T) childViews[childViewIndex];
  }

  /**
   * Get child view
   *
   * @param parentView
   * @param childViewIndex
   * @param childViewClass
   * @return child view
   */
  @SuppressWarnings("unchecked")
  public <T> T getView(final View parentView, final int childViewIndex,
      final Class<T> childViewClass) {
    return (T) getChildren(parentView)[childViewIndex];
  }

  /**
   * Set child view as gone or visible
   *
   * @param childViewIndex
   * @param gone
   * @return child view
   */
  public View setGone(final int childViewIndex, boolean gone) {
    return ViewUtils.setGone(view(childViewIndex), gone);
  }

  /**
   * Set child view as gone or visible
   *
   * @param parentView
   * @param childViewIndex
   * @param gone
   * @return child view
   */
  public View setGone(final View parentView, final int childViewIndex,
      boolean gone) {
    return ViewUtils.setGone(view(parentView, childViewIndex), gone);
  }

  /**
   * Set the checked state of the {@link CompoundButton} with at index
   *
   * @param childViewIndex
   * @param checked
   * @return check box
   */
  public CompoundButton setChecked(final int childViewIndex,
      final boolean checked) {
    final CompoundButton button = view(childViewIndex);
    button.setChecked(checked);
    return button;
  }

  /**
   * Set the checked state of the {@link CompoundButton} with at index
   *
   * @param parentView
   * @param childViewIndex
   * @param checked
   * @return check box
   */
  public CompoundButton setChecked(final View parentView,
      final int childViewIndex, final boolean checked) {
    final CompoundButton button = view(parentView, childViewIndex);
    button.setChecked(checked);
    return button;
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
    TextView view = textView(childViewIndex);
    view.setText(text);
    ViewUtils.setGone(view, TextUtils.isEmpty(text));
    return view;
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
    TextView view = textView(parentView, childViewIndex);
    view.setText(text);
    ViewUtils.setGone(view, TextUtils.isEmpty(text));
    return view;
  }

  private CharSequence formatRelativeTimeSpan(final long time) {
    return DateUtils.getRelativeTimeSpanString(time);
  }

  /**
   * Set relative time span on text view
   *
   * @param childViewIndex
   * @param time
   * @return text view
   */
  public TextView setRelativeTimeSpan(final int childViewIndex, final long time) {
    return setText(childViewIndex, formatRelativeTimeSpan(time));
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
    return setText(parentView, childViewIndex, formatRelativeTimeSpan(time));
  }
}
