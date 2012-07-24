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

import static android.widget.Toast.LENGTH_LONG;
import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.text.MessageFormat;

/**
 * Helper to show {@link Toast} notifications
 */
public class Toaster {

	private static void show(final Activity activity, final int resId,
			final int duration) {
		Context context = activity.getApplicationContext();
		Toast.makeText(context, resId, duration).show();
	}

	private static void show(final Activity activity, final String message,
			final int duration) {
		Context context = activity.getApplicationContext();
		Toast.makeText(context, message, duration).show();
	}

	/**
	 * Show message in {@link Toast} with {@link Toast#LENGTH_LONG} duration
	 *
	 * @param activity
	 * @param resId
	 */
	public static void showLong(Activity activity, int resId) {
		show(activity, resId, LENGTH_LONG);
	}

	/**
	 * Show message in {@link Toast} with {@link Toast#LENGTH_SHORT} duration
	 *
	 * @param activity
	 * @param resId
	 */
	public static void showShort(Activity activity, int resId) {
		show(activity, resId, LENGTH_LONG);
	}

	/**
	 * Show message in {@link Toast} with {@link Toast#LENGTH_LONG} duration
	 *
	 * @param activity
	 * @param message
	 */
	public static void showLong(Activity activity, String message) {
		show(activity, message, LENGTH_LONG);
	}

	/**
	 * Show message in {@link Toast} with {@link Toast#LENGTH_SHORT} duration
	 *
	 * @param activity
	 * @param message
	 */
	public static void showShort(Activity activity, String message) {
		show(activity, message, LENGTH_LONG);
	}

	/**
	 * Show message in {@link Toast} with {@link Toast#LENGTH_LONG} duration
	 *
	 * @param activity
	 * @param message
	 * @param args
	 */
	public static void showLong(Activity activity, String message,
			Object... args) {
		String formatted = MessageFormat.format(message, args);
		show(activity, formatted, LENGTH_LONG);
	}

	/**
	 * Show message in {@link Toast} with {@link Toast#LENGTH_SHORT} duration
	 *
	 * @param activity
	 * @param message
	 * @param args
	 */
	public static void showShort(Activity activity, String message,
			Object... args) {
		String formatted = MessageFormat.format(message, args);
		show(activity, formatted, LENGTH_LONG);
	}

	/**
	 * Show message in {@link Toast} with {@link Toast#LENGTH_LONG} duration
	 *
	 * @param activity
	 * @param resId
	 * @param args
	 */
	public static void showLong(Activity activity, int resId, Object... args) {
		String message = activity.getString(resId);
		showLong(activity, message, args);
	}

	/**
	 * Show message in {@link Toast} with {@link Toast#LENGTH_SHORT} duration
	 *
	 * @param activity
	 * @param resId
	 * @param args
	 */
	public static void showShort(Activity activity, int resId, Object... args) {
		String message = activity.getString(resId);
		showShort(activity, message, args);
	}
}
