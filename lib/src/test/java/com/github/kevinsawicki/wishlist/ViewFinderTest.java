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
import static org.junit.Assert.assertTrue;
import android.app.Activity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;

import com.xtremelabs.robolectric.RobolectricTestRunner;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit tests of {@link ViewFinder}
 */
@RunWith(RobolectricTestRunner.class)
public class ViewFinderTest {

  /**
   * Verify on check helpers
   */
  @Test
  public void onCheckRunnable() {
    CheckBox checkbox = new CheckBox(new Activity());
    checkbox.setId(android.R.id.button1);
    ViewFinder finder = new ViewFinder(checkbox);
    final AtomicBoolean run = new AtomicBoolean(false);
    finder.onCheck(android.R.id.button1, new Runnable() {

      public void run() {
        run.set(true);
      }
    });
    checkbox.performClick();
    assertTrue(run.get());
  }

  /**
   * Verify on check helpers
   */
  @Test
  public void onCheckRunnableWithMultipleIds() {
    Activity activity = new Activity();
    FrameLayout root = new FrameLayout(activity);

    CheckBox checkbox1 = new CheckBox(activity);
    checkbox1.setId(android.R.id.button1);
    root.addView(checkbox1);

    CheckBox checkbox2 = new CheckBox(activity);
    checkbox2.setId(android.R.id.button2);
    root.addView(checkbox2);

    ViewFinder finder = new ViewFinder(root);
    final AtomicInteger run = new AtomicInteger(0);
    finder.onCheck(new Runnable() {

      public void run() {
        run.incrementAndGet();
      }
    }, android.R.id.button1, android.R.id.button2);
    checkbox1.performClick();
    assertEquals(1, run.get());
    checkbox2.performClick();
    assertEquals(2, run.get());
  }

  /**
   * Verify on check helpers
   */
  @Test
  public void onCheckListener() {
    CheckBox checkbox = new CheckBox(new Activity());
    checkbox.setId(android.R.id.button1);
    ViewFinder finder = new ViewFinder(checkbox);
    final AtomicBoolean run = new AtomicBoolean(false);
    finder.onCheck(android.R.id.button1, new OnCheckedChangeListener() {

      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        run.set(true);
      }
    });
    checkbox.performClick();
    assertTrue(run.get());
  }
}
