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

import static android.content.Context.LOCATION_SERVICE;
import static android.location.Criteria.ACCURACY_FINE;
import static android.location.LocationManager.GPS_PROVIDER;
import static android.location.LocationManager.NETWORK_PROVIDER;
import static android.location.LocationManager.PASSIVE_PROVIDER;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

/**
 *
 */
public class LocationUtils {

  /**
   * Get the location with the later date
   *
   * @param location1
   * @param location2
   * @return location
   */
  private static Location getLatest(Location location1, Location location2) {
    if (location1 == null)
      return location2;

    if (location2 == null)
      return location1;

    if (location2.getTime() > location1.getTime())
      return location2;
    else
      return location1;
  }

  /**
   * Get the latest location trying multiple providers
   *
   * @param context
   * @return latest location set or null if none
   */
  public static Location getLatestLocation(final Context context) {
    LocationManager manager = (LocationManager) context
        .getSystemService(LOCATION_SERVICE);
    Criteria criteria = new Criteria();
    criteria.setAccuracy(ACCURACY_FINE);
    String provider = manager.getBestProvider(criteria, true);
    Location bestLocation;
    if (provider != null)
      bestLocation = manager.getLastKnownLocation(provider);
    else
      bestLocation = null;
    Location gpsLocation = manager.getLastKnownLocation(GPS_PROVIDER);
    Location networkLocation = manager.getLastKnownLocation(NETWORK_PROVIDER);
    Location passiveLocation = manager.getLastKnownLocation(PASSIVE_PROVIDER);
    Location latestLocation = getLatest(bestLocation, gpsLocation);
    latestLocation = getLatest(latestLocation, networkLocation);
    latestLocation = getLatest(latestLocation, passiveLocation);
    return latestLocation;
  }
}
