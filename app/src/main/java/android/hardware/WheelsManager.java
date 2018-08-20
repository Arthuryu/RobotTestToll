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

public class WheelsManager {
    private static final String TAG = "WheelsManager";

    private final Context mContext;
    private final IWheelsManager mService;

    public WheelsManager(Context context, IWheelsManager service) {
        mContext = context;
        mService = service;
    }

    /**
     * Open wheels device
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
     * Close wheels device
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
     * Move the robot to given distance in given angle and speed
     * @param angle
     *        0: right; 90: forward 180: left; 270: backward
     * @param speed
     *        meters per second
     * @param distance
     *        moving distance
     *
     * @return 0 if success else -1
     */
    public int move(int angle, double speed, double distance) {
      try {
        return mService.move(angle, speed, distance);
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in move", e);
        return -1;
      }
    }

    /**
     * Asynchronously move the robot to given distance in given angle and speed
     * @param angle
     *        0: right; 90: forward 180: left; 270: backward
     * @param speed
     *        meters per second
     *
     * @return 0 if success else -1
     */
    public int move_async(int angle, double speed) {
      try {
        return mService.move_async(angle, speed);
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in move_async", e);
        return -1;
      }
    }

    /**
     * Move-forward the robot to given distance in given speed
     *
     * @param speed
     *        meters per second
     * @param distance
     *        moving distance
     *
     * @return 0 if success else -1
     */
    public int move_forward(double speed, double distance) {
      try {
        return mService.move_forward(speed, distance);
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in move_forward", e);
        return -1;
      }
    }

    /**
     * Move-backward the robot to given distance in given speed
     *
     * @param speed
     *        meters per second
     * @param distance
     *        moving distance
     *
     * @return 0 if success else -1
     */
    public int move_backward(double speed, double distance) {
      try {
        return mService.move_backward(speed, distance);
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in move_backward", e);
        return -1;
      }
    }

    /**
     * Move-left the robot to given distance in given speed
     *
     * @param speed
     *        meters per second
     * @param distance
     *        moving distance
     *
     * @return 0 if success else -1
     */
    public int move_left(double speed, double distance) {
      try {
        return mService.move_left(speed, distance);
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in move_left", e);
        return -1;
      }
    }

    /**
     * Move-left the robot to given distance in given speed
     *
     * @param speed
     *        meters per second
     * @param distance
     *        moving distance
     *
     * @return 0 if success else -1
     */
    public int move_right(double speed, double distance) {
      try {
        return mService.move_right(speed, distance);
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in move_right", e);
        return -1;
      }
    }

    /**
     * Turn the robot to given angle in given speed
     *
     * @param speed
     *        meters per second
     * @param angle
     *        turning angle
     *
     * @return 0 if success else -1
     */
    public int turn(double speed, int angle) {
      try {
        return mService.turn(speed, angle);
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in turn", e);
        return -1;
      }
    }

    /**
     * Asynchronously turn the robot to given angle in given speed
     *
     * @param speed
     *        meters per second
     *
     * @return 0 if success else -1
     */
    public int turn_async(double speed) {
      try {
        return mService.turn_async(speed);
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in turn_async", e);
        return -1;
      }
    }

    /**
     * Turn-left the robot to given angle in given speed
     *
     * @param speed
     *        meters per second
     * @param angle
     *        turning angle
     *
     * @return 0 if success else -1
     */
    public int turn_left(double speed, int angle) {
      try {
        return mService.turn_left(speed, angle);
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in turn_left", e);
        return -1;
      }
    }

    /**
     * Turn-right the robot to given angle in given speed
     *
     * @param speed
     *        meters per second
     * @param angle
     *        turning angle
     *
     * @return 0 if success else -1
     */
    public int turn_right(double speed, int angle) {
      try {
        return mService.turn_right(speed, angle);
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in turn_right", e);
        return -1;
      }
    }

    /**
     * Stop all the robot wheels
     *
     * @return 0 if success else -1
     */
    public int halt() {
      try {
        return mService.halt();
      } catch (RemoteException e) {
        Log.e(TAG, "RemoteException in halt", e);
        return -1;
      }
    }
}
