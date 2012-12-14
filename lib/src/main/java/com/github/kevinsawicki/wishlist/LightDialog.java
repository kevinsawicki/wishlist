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

import static android.os.Build.VERSION.SDK_INT;
import android.app.AlertDialog;
import android.content.Context;

/**
 * Alert dialog in Holo Light theme
 */
public class LightDialog extends AlertDialog {

  /**
   * Create light dialog
   *
   * @param context
   * @return light alert dialog
   */
  public static LightDialog create(Context context) {
    LightDialog dialog;
    if (SDK_INT >= 14)
      dialog = new LightDialog(context, 3);
    else {
      dialog = new LightDialog(context);
      dialog.setInverseBackgroundForced(true);
    }
    return dialog;
  }

  /**
   * Create light dialog with title and message
   *
   * @param context
   * @param title
   * @param message
   * @return alert dialog
   */
  public static LightDialog create(Context context, String title, String message) {
    LightDialog dialog = create(context);
    dialog.setTitle(title);
    dialog.setMessage(message);
    return dialog;
  }

  /**
   * Create light dialog with title and message
   *
   * @param context
   * @param title
   * @param message
   * @return alert dialog
   */
  public static LightDialog create(Context context, int title, int message) {
    LightDialog dialog = create(context);
    dialog.setTitle(title);
    dialog.setMessage(context.getString(message));
    return dialog;
  }

  /**
   * Create light dialog with title and message
   *
   * @param context
   * @param title
   * @param message
   * @return alert dialog
   */
  public static LightDialog create(Context context, int title, String message) {
    LightDialog dialog = create(context);
    dialog.setTitle(title);
    dialog.setMessage(message);
    return dialog;
  }

  /**
   * Create light dialog with title and message
   *
   * @param context
   * @param title
   * @param message
   * @return alert dialog
   */
  public static LightDialog create(Context context, String title, int message) {
    return create(context, title, context.getString(message));
  }

  /**
   * @param context
   */
  protected LightDialog(Context context) {
    super(context);
  }

  /**
   * @param context
   * @param theme
   */
  protected LightDialog(Context context, int theme) {
    super(context, theme);
  }

  /**
   * Set positive button
   *
   * @param text
   * @param listener
   * @return this dialog
   */
  public LightDialog setPositiveButton(int text, OnClickListener listener) {
    return setPositiveButton(getContext().getString(text), listener);
  }

  /**
   * Set positive button
   *
   * @param text
   * @param listener
   * @return this dialog
   */
  public LightDialog setPositiveButton(CharSequence text,
      OnClickListener listener) {
    setButton(BUTTON_POSITIVE, text, listener);
    return this;
  }

  /**
   * Set positive button
   *
   * @param listener
   * @return this dialog
   */
  public LightDialog setPositiveButton(OnClickListener listener) {
    return setPositiveButton(android.R.string.ok, listener);
  }

  /**
   * Set negative button
   *
   * @param text
   * @param listener
   * @return this dialog
   */
  public LightDialog setNegativeButton(int text, OnClickListener listener) {
    return setNegativeButton(getContext().getString(text), listener);
  }

  /**
   * Set negative button
   *
   * @param text
   * @param listener
   * @return this dialog
   */
  public LightDialog setNegativeButton(CharSequence text,
      OnClickListener listener) {
    setButton(BUTTON_NEGATIVE, text, listener);
    return this;
  }

  /**
   * Set negative button
   *
   * @param listener
   * @return this dialog
   */
  public LightDialog setNegativeButton(OnClickListener listener) {
    return setNegativeButton(android.R.string.cancel, listener);
  }
}
