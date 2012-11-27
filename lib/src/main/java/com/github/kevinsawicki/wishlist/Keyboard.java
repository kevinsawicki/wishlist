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

import static android.content.Context.INPUT_METHOD_SERVICE;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Keyboard utilities
 */
public class Keyboard {

  /**
   * Hide soft input method manager
   *
   * @param view
   * @return view
   */
  public static View hideSoftInput(final View view) {
    InputMethodManager manager = (InputMethodManager) view.getContext()
        .getSystemService(INPUT_METHOD_SERVICE);
    if (manager != null)
      manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    return view;
  }
}
