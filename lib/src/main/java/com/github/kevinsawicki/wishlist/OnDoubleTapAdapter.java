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

import android.view.MotionEvent;
import android.view.GestureDetector.OnDoubleTapListener;

/**
 * Adapter for a {@link OnDoubleTapListener}
 */
public class OnDoubleTapAdapter implements OnDoubleTapListener {

  @Override
  public boolean onDoubleTap(MotionEvent e) {
    return false;
  }

  @Override
  public boolean onDoubleTapEvent(MotionEvent e) {
    return false;
  }

  @Override
  public boolean onSingleTapConfirmed(MotionEvent e) {
    return false;
  }
}
