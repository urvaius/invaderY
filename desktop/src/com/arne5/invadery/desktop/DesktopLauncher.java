package com.arne5.invadery.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.arne5.invadery.InvaderY;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class DesktopLauncher {
	public static void main (String[] arg) {


		TexturePacker.Settings settings = new TexturePacker.Settings();
		//TexturePacker.process(settings, "C:/Development/github/invadery/spaceimages","C:/Development/github/invadery/android/assets","pack");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title="Invader Y";
		config.width=InvaderY.WIDTH;
		config.height=InvaderY.HEIGHT;


		new LwjglApplication(new InvaderY(), config);



	}
}
