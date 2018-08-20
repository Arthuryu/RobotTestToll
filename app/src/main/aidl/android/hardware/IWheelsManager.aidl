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

interface IWheelsManager
{
  int open();
  int close();

  int move(int angle, double speed, double forward_distance);
  int move_async(int angle, double speed);
  int move_forward(double speed, double distance);
  int move_backward(double speed, double distance);
  int move_left(double speed, double distance);
  int move_right(double speed, double distance);

  int turn(double speed, int angle);
  int turn_async(double speed);
  int turn_left(double speed, int angle);
  int turn_right(double speed, int angle);

  int halt();
}
