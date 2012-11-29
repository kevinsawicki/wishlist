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

import static android.graphics.Bitmap.Config.ARGB_8888;
import static android.util.Log.DEBUG;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;

/**
 * Task to decode a bitmap from a file path
 */
public class DecodeBitmapTask extends AsyncTask<Void, Void, Bitmap> {

  private static final String TAG = "DecodeBitmapTask";

  /**
   * Get byte count
   *
   * @param bitmap
   * @return byte count
   */
  public static final int getByteCount(final Bitmap bitmap) {
    return bitmap.getRowBytes() * bitmap.getHeight();
  }

  private static final byte[] BUFFER = new byte[16 * 1024];

  /**
   * Maximum width of decoded bitmap
   */
  protected final int maxWidth;

  /**
   * Maximum height of decoded bitmap
   */
  protected final int maxHeight;

  /**
   * Path to decoded bitmap from
   */
  protected final String path;

  /**
   * Create task to decode the bitmap at the specified path to the specified
   * maximum width and height
   *
   * @param maxWidth
   * @param maxHeight
   * @param path
   */
  public DecodeBitmapTask(final int maxWidth, final int maxHeight,
      final String path) {
    this.maxWidth = maxWidth;
    this.maxHeight = maxHeight;
    this.path = path;
  }

  /**
   * Decode {@link Bitmap} at given path
   *
   * @return bitmap
   */
  protected Bitmap decode() {
    final Options options = new Options();
    options.inJustDecodeBounds = true;

    BitmapFactory.decodeFile(path, options);
    if (options.outWidth <= 0 || options.outHeight <= 0) {
      Log.d(TAG, "Decoding bounds of " + path + " failed");
      new File(path).delete();
      return null;
    }

    if (isCancelled())
      return null;

    int scale = 1;
    if (options.outWidth > maxWidth || options.outHeight > maxHeight)
      scale = Math.max(
          Math.round((float) options.outHeight / (float) maxHeight),
          Math.round((float) options.outWidth / (float) maxWidth));

    options.inJustDecodeBounds = false;
    options.inSampleSize = scale;
    options.inPreferredConfig = ARGB_8888;
    options.inPurgeable = true;

    Bitmap decoded;
    synchronized (BUFFER) {
      options.inTempStorage = BUFFER;
      decoded = BitmapFactory.decodeFile(path, options);
    }

    if (Log.isLoggable(TAG, DEBUG)) {
      if (decoded == null) {
        Log.d(TAG, "Decoding " + path + " failed");
        new File(path).delete();
      } else
        Log.d(TAG,
            "Decoded to " + decoded.getWidth() + "x" + decoded.getHeight()
                + " from max size: " + maxWidth + "x" + maxHeight
                + " using scale:" + scale + " and byte count:"
                + getByteCount(decoded));
    }

    return decoded;
  }

  /**
   * Does the given path exist?
   *
   * @param path
   * @return true if exists and non-empty, false otherwise
   */
  protected boolean pathExists(final String path) {
    File file = new File(path);
    return file.exists() && file.length() > 0;
  }

  /**
   * Load bitmap
   *
   * @return bitmap
   */
  protected Bitmap load() {
    if (pathExists(path))
      return decode();
    else
      return null;
  }

  @Override
  protected Bitmap doInBackground(final Void... params) {
    if (!isCancelled())
      return load();
    else
      return null;
  }

  /**
   * Get path being decoded
   *
   * @return path
   */
  public String getPath() {
    return path;
  }
}
