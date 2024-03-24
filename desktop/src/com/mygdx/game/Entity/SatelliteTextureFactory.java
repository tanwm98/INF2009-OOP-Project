package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class SatelliteTextureFactory {
	
	public Texture makeSatellite(int newSatelliteTex) {
		if (newSatelliteTex == 0) {
			return new Texture(Gdx.files.internal("Objects/Satellites/satelite.png"));
		}
		else {
			if (newSatelliteTex == 1) {
				return new Texture(Gdx.files.internal("Objects/Satellites/satellite_1.png"));
			}
			if (newSatelliteTex == 2) {
				return new Texture(Gdx.files.internal("Objects/Satellites/satellite_2.png"));
			}
			if (newSatelliteTex == 3) {
				return new Texture(Gdx.files.internal("Objects/Satellites/satellite_3.png"));
			}
			else {
				return null;
			}
		}
	}
}
