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

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import android.view.View;

/**
 * Utilities for working with the {@link View} class
 */
public class ViewUtils {

  /**
   * Set visibility of given view to be gone or visible
   * <p>
   * This method has no effect if the view visibility is currently invisible
   *
   * @param view
   * @param gone
   * @return view
   */
  public static <V extends View> V setGone(final V view, final boolean gone) {
    if (view != null)
      switch (view.getVisibility()) {
      case VISIBLE:
        if (gone)
          view.setVisibility(GONE);
        break;
      case GONE:
        if (!gone)
          view.setVisibility(VISIBLE);
        break;
      }
    return view;
  }

  /**
   * Set visibility of given view to be invisible or visible
   * <p>
   * This method has no effect if the view visibility is currently gone
   *
   * @param view
   * @param invisible
   * @return view
   */
  public static <V extends View> V setInvisible(final V view,
      final boolean invisible) {
    if (view != null)
      switch (view.getVisibility()) {
      case VISIBLE:
        if (invisible)
          view.setVisibility(INVISIBLE);
        break;
      case INVISIBLE:
        if (!invisible)
          view.setVisibility(VISIBLE);
        break;
      }
    return view;
  }

  private ViewUtils() {

  }
}
