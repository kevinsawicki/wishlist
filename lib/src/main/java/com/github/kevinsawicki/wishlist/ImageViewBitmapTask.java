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

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Task to decode a bitmap and set it on an {@link ImageView}
 */
public class ImageViewBitmapTask extends DecodeBitmapTask {

  /**
   * View being updated
   */
  protected final ImageView view;

  /**
   * Create task to set bitmap at path on given image view
   *
   * @param maxWidth
   * @param maxHeight
   * @param path
   * @param view
   */
  public ImageViewBitmapTask(final int maxWidth, final int maxHeight,
      final String path, final ImageView view) {
    super(maxWidth, maxHeight, path);

    this.view = view;
  }

  @Override
  protected void onPreExecute() {
    super.onPreExecute();

    view.setImageDrawable(null);
    view.setTag(this);
  }

  @Override
  protected void onPostExecute(final Bitmap result) {
    super.onPostExecute(result);

    if (!this.equals(view.getTag()))
      return;

    view.setTag(null);
    if (result != null)
      view.setImageBitmap(result);
    else
      view.setImageDrawable(null);
  }
}
