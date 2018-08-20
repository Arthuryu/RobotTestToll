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

interface INeckManager
{
  int open();
  int close();

  int control_lr(double abs_angle, double angle_per_sec);
  int control_async_lr(double angle_per_sec);
  int control_rock_lr(double abs_angle_min, double abs_angle_max, int times, double angle_per_sec);
  int halt_lr();
  int reset_lr(double angle_per_sec);
  int get_curr_angle_lr();

  int control_ud(double abs_angle, double angle_per_sec);
  int control_async_ud(double angle_per_sec);
  int control_rock_ud(double abs_angle_min, double abs_angle_max, int times, double angle_per_sec);
  int halt_ud();
  int reset_ud(double angle_per_sec);
  int get_curr_angle_ud();
}
