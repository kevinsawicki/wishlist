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
import static org.junit.Assert.assertEquals;
import android.app.Activity;
import android.view.View;

import com.xtremelabs.robolectric.RobolectricTestRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit tests of {@link ViewUtils}
 */
@RunWith(RobolectricTestRunner.class)
public class ViewUtilsTest {

  /**
   * Set a visible view gone
   */
  @Test
  public void setVisibleViewGone() {
    View view = new View(new Activity());
    view.setVisibility(VISIBLE);
    assertEquals(VISIBLE, view.getVisibility());
    ViewUtils.setGone(view, true);
    assertEquals(GONE, view.getVisibility());
  }

  /**
   * Set an invisible view gone
   */
  @Test
  public void setInvisibleViewGone() {
    View view = new View(new Activity());
    view.setVisibility(INVISIBLE);
    assertEquals(INVISIBLE, view.getVisibility());
    ViewUtils.setGone(view, true);
    assertEquals(GONE, view.getVisibility());
  }

  /**
   * Set a visible view invisible
   */
  @Test
  public void setVisibleViewInvisible() {
    View view = new View(new Activity());
    view.setVisibility(VISIBLE);
    assertEquals(VISIBLE, view.getVisibility());
    ViewUtils.setInvisible(view, true);
    assertEquals(INVISIBLE, view.getVisibility());
  }

  /**
   * Set a visible view invisible
   */
  @Test
  public void setGoneViewInvisible() {
    View view = new View(new Activity());
    view.setVisibility(GONE);
    assertEquals(GONE, view.getVisibility());
    ViewUtils.setInvisible(view, true);
    assertEquals(INVISIBLE, view.getVisibility());
  }
}
