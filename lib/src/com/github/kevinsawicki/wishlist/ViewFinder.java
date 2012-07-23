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
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Helper for finding and tweaking a view's children
 */
public class ViewFinder {

  private final View view;

  /**
   * Create finder wrapping given view
   *
   * @param view
   */
  public ViewFinder(final View view) {
    this.view = view;
  }

  /**
   * Get image view with id
   *
   * @param id
   * @return image view
   */
  public ImageView imageView(final int id) {
    return (ImageView) view.findViewById(id);
  }

  /**
   * Set text of child view with given id
   *
   * @param id
   * @param content
   * @return text view
   */
  public TextView setText(final int id, final String content) {
    final TextView text = (TextView) view.findViewById(id);
    text.setText(content);
    return text;
  }

  /**
   * Set text of child view with given id
   *
   * @param id
   * @param content
   * @return text view
   */
  public TextView setText(final int id, final int content) {
    return setText(id, view.getResources().getString(content));
  }

  /**
   * Register on click listener to child view with given id
   *
   * @param id
   * @param listener
   * @return view registered with listener
   */
  public View onClick(final int id, final OnClickListener listener) {
    View clickable = view.findViewById(id);
    clickable.setOnClickListener(listener);
    return clickable;
  }

  /**
   * Register runnable to be invoked when child view with given id is clicked
   *
   * @param id
   * @param runnable
   * @return view registered with runnable
   */
  public View onClick(final int id, final Runnable runnable) {
    return onClick(id, new OnClickListener() {

      public void onClick(View v) {
        runnable.run();
      }
    });
  }

  /**
   * Set drawable on child image view
   *
   * @param id
   * @param drawable
   * @return image view
   */
  public ImageView setDrawable(final int id, final int drawable) {
    ImageView image = (ImageView) view.findViewById(id);
    image.setImageDrawable(image.getResources().getDrawable(drawable));
    return image;
  }
}
