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

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Memo'ized typefaces loaded from the assets directory
 */
public class TypefaceUtils {

  private static final Map<String, Typeface> TYPEFACES = new HashMap<String, Typeface>(
      4);

  /**
   * Get typeface with name
   *
   * @param name
   * @param view
   * @return typeface, either cached or loaded from the assets
   */
  public static Typeface getTypeface(final String name, final View view) {
    return getTypeface(name, view.getContext());
  }

  /**
   * Get typeface with name
   *
   * @param name
   * @param context
   * @return typeface, either cached or loaded from the assets
   */
  public static Typeface getTypeface(final String name, final Context context) {
    Typeface typeface = TYPEFACES.get(name);
    if (typeface == null) {
      typeface = Typeface.createFromAsset(context.getAssets(), name);
      TYPEFACES.put(name, typeface);
    }
    return typeface;
  }

  /**
   * Set typeface with name on given text view
   *
   * @param name
   * @param view
   * @return view
   */
  public static <V extends TextView> V setTypeface(final String name,
      final V view) {
    if (view != null)
      view.setTypeface(getTypeface(name, view));
    return view;
  }

  /**
   * Set typeface with name on given text views
   *
   * @param name
   * @param views
   */
  public static void setTypeface(final String name, final TextView... views) {
    if (views == null || views.length == 0)
      return;

    Typeface typeface = getTypeface(name, views[0]);
    for (TextView view : views)
      view.setTypeface(typeface);
  }
}
