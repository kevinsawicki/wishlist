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

import static android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE;
import static android.view.KeyEvent.ACTION_DOWN;
import static android.view.KeyEvent.KEYCODE_ENTER;
import static android.view.inputmethod.EditorInfo.IME_ACTION_DONE;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/**
 * Utilities for working with an {@link EditText}
 */
public class EditTextUtils {

  /**
   * A {@link Runnable} that returns a boolean
   */
  public static interface BooleanRunnable {

    /**
     * Runnable that returns true when run, false when not run
     *
     * @return true if run, false otherwise
     */
    boolean run();
  }

  /**
   * Bind given runnable to be invoked when the
   *
   * @param editText
   * @param runnable
   * @return edit text
   */
  public static EditText onDone(final EditText editText,
      final BooleanRunnable runnable) {
    if ((editText.getInputType() & TYPE_TEXT_FLAG_MULTI_LINE) == 0)
      editText.setOnKeyListener(new OnKeyListener() {

        public boolean onKey(View v, int keyCode, KeyEvent event) {
          if (keyCode != KEYCODE_ENTER)
            return false;
          if (event == null)
            return false;
          if (event.getAction() != ACTION_DOWN)
            return false;

          return runnable.run();
        }
      });

    editText.setOnEditorActionListener(new OnEditorActionListener() {

      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == IME_ACTION_DONE)
          return runnable.run();
        else
          return false;
      }
    });

    return editText;
  }
}
