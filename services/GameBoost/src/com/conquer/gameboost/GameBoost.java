/*
 * Copyright (C) 2021 The LineageOS Project
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
package com.conquer.gameboost;

import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.os.SystemProperties;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import android.util.Log;

public class GameBoost extends TileService {
    protected static final String GAMEBOOST_ENABLED = "service.game.boost";
    private boolean mBoostDetection = false;

    @Override
    public void onStartListening() {
        super.onStartListening();
        refresh();
    }

    @Override
    public void onClick() {
        super.onClick();
        if (mGamingModeDetection) {
        SystemProperties.set(BASIC_BASIC_GAMINGMODE_ENABLED, "off");
        mGamingModeDetection = false;
        Log.d("BasicGamingMode", "Stopped BasicGamingMode");
        } else {
        SystemProperties.set(BASIC_BASIC_GAMINGMODE_ENABLED, "on");
        mGamingModeDetection = true;
        Log.d("BasicGamingMode", "Starting BasicGamingMode");
        Toast.makeText(BasicGamingMode.this, getString(R.string.basicgamingmode_toast), Toast.LENGTH_LONG).show();
        }
        refresh();
    }

    private void refresh() {
        if (mGamingModeDetection) {
            getQsTile().setState(Tile.STATE_ACTIVE);
        } else {
            getQsTile().setState(Tile.STATE_INACTIVE);
        }
        getQsTile().updateTile();
    }

}
