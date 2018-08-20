/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package android.hardware;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;

public class NeckManager {
    private static final String TAG = "NeckManager";

    private final Context mContext;
    private final INeckManager mService;

    public NeckManager(Context context, INeckManager service) {
        mContext = context;
        mService = service;
    }

    /**
     * Open neck device
     *
     * @return 0 if success else -1
     */
    public int open() {
      try {
        return mService.open();
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in open", e);
        return -1;
      }
    }

    /**
     * Close neck device
     *
     * @return 0 if success else -1
     */
    public int close() {
      try {
        return mService.close();
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in close", e);
        return -1;
      }
    }

    /**
     * Control robot neck in left-right direction and given speed.
     * @param abs_angle
     *        -120/left ~ 120/right
     * @param angle_per_sec
     *        angle per second
     *
     * @return 0 if success else -1
     */
    public int control_lr(double abs_angle, double angle_per_sec) {
      try {
        return mService.control_lr(abs_angle, angle_per_sec);
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in control_lr", e);
        return -1;
      }
    }

    /**
     * Asynchrously control robot neck in left-right direction and given speed.
     * @param angle_per_sec
     *        angle per second
     *
     * @return 0 if success else -1
     */
    public int control_async_lr(double angle_per_sec) {
      try {
        return mService.control_async_lr(angle_per_sec);
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in control_async_lr", e);
        return -1;
      }
    }

    /**
     * Repeatly control robot neck from one to anther point in left-right direction and given speed.
     * @param abs_angle_x
     *        one end (-120/left ~ 120/right)
     * @param abs_angle_y
     *        another end (-120/left ~ 120/right)
     * @param times
     *        repeated times
     * @param angle_per_sec
     *        angle per second
     *
     * @return 0 if success else -1
     */
    public int control_rock_lr(double abs_angle_x, double abs_angle_y, int times, double angle_per_sec) {
      try {
        return mService.control_rock_lr(abs_angle_x, abs_angle_y, times, angle_per_sec);
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in control_rock_lr", e);
        return -1;
      }
    }

    /**
     * Stop left-right movement of neck immediately.
     *
     * @return 0 if success else -1
     */
    public int halt_lr() {
      try {
        return mService.halt_lr();
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in halt_lr", e);
        return -1;
      }
    }

    /**
     * Move neck to zero point in left-right direction.
     * @param angle_per_sec
     *        angle per second
     *
     * @return 0 if success else -1
     */
    public int reset_lr(double angle_per_sec) {
      try {
        return mService.reset_lr(angle_per_sec);
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in reset_lr", e);
        return -1;
      }
    }

    /**
     * Get present neck position in left-right direction.
     *
     * @return current position
     */
    public int get_curr_angle_lr() {
      try {
        return mService.get_curr_angle_lr();
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in get_curr_angle_lr", e);
        return -1;
      }
    }

    /**
     * Control robot neck in up-down direction and given speed.
     * @param abs_angle
     *        -120/left ~ 120/right
     * @param angle_per_sec
     *        angle per second
     *
     * @return 0 if success else -1
     */
    public int control_ud(double abs_angle, double angle_per_sec) {
      try {
        return mService.control_ud(abs_angle, angle_per_sec);
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in control_ud", e);
        return -1;
      }
    }

    /**
     * Asynchrously control robot neck in up-down direction and given speed.
     * @param angle_per_sec
     *        angle per second
     *
     * @return 0 if success else -1
     */
    public int control_async_ud(double angle_per_sec) {
      try {
        return mService.control_async_ud(angle_per_sec);
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in control_async_ud", e);
        return -1;
      }
    }

    /**
     * Repeatly control robot neck from one to anther point in up-down direction and given speed.
     * @param abs_angle_x
     *        one end (-120/left ~ 120/right)
     * @param abs_angle_y
     *        another end (-120/left ~ 120/right)
     * @param times
     *        repeated times
     * @param angle_per_sec
     *        angle per second
     *
     * @return 0 if success else -1
     */
    public int control_rock_ud(double abs_angle_x, double abs_angle_y, int times, double angle_per_sec) {
      try {
        return mService.control_rock_ud(abs_angle_x, abs_angle_y, times, angle_per_sec);
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in control_rock_ud", e);
        return -1;
      }
    }

    /**
     * Stop up-down movement of neck immediately.
     *
     * @return 0 if success else -1
     */
    public int halt_ud() {
      try {
        return mService.halt_ud();
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in halt_ud", e);
        return -1;
      }
    }

    /**
     * Move neck to zero point in up-down direction.
     * @param angle_per_sec
     *        angle per second
     *
     * @return 0 if success else -1
     */
    public int reset_ud(double angle_per_sec) {
      try {
        return mService.reset_ud(angle_per_sec);
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in reset_ud", e);
        return -1;
      }
    }

    /**
     * Get present neck position in up-down direction.
     *
     * @return current position
     */
    public int get_curr_angle_ud() {
      try {
        return mService.get_curr_angle_ud();
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in get_curr_angle_ud", e);
        return -1;
      }
    }
}
