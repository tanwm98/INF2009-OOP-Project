package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class SatelliteTextureFactory { // Factory class for creating satellite textures.

	public Texture makeSatellite(int newSatelliteTex) {
		if (newSatelliteTex == 1) {
			return new Texture(Gdx.files.internal("Objects/Satellites/satellite_2.png"));
		}
		else if (newSatelliteTex == 2) {
			return new Texture(Gdx.files.internal("Objects/Satellites/satellite_3.png"));
		} else {
			return null;
		}
	}
}

