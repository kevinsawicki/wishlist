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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import android.app.Activity;

import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.shadows.ShadowToast;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests of {@link Toaster} helper
 */
@RunWith(RobolectricTestRunner.class)
public class ToasterTest {

  /**
   * Setup tests
   */
  @Before
  public void setup() {
    ShadowToast.reset();
    assertNull(ShadowToast.getLatestToast());
  }

  /**
   * Call Toaster with no activity
   */
  @Test
  public void noActivity() {
    Toaster.showLong(null, "toast");
    assertNull(ShadowToast.getLatestToast());
    Toaster.showShort(null, "toast");
    assertNull(ShadowToast.getLatestToast());
  }

  /**
   * Call Toaster with valid activity and message
   */
  @Test
  public void toastText() {
    Toaster.showLong(new Activity(), "toast1");
    assertEquals("toast1", ShadowToast.getTextOfLatestToast());
    Toaster.showShort(new Activity(), "toast2");
    assertEquals("toast2", ShadowToast.getTextOfLatestToast());
  }
}
