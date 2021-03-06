package com.arne5.invadery.Entity;

import com.arne5.invadery.Assets;
import com.arne5.invadery.InvaderY;
import com.arne5.invadery.camera.OrthoCamera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import java.rmi.server.ServerNotActiveException;

/**
 * Created by urvaius on 11/12/14.
 */
public class Player extends Entity
	{
		private final EntityManager entityManager;
		private final OrthoCamera camera;
		private long lastFire;
		private int score;
		private int level;

		public int getLevel(){return level;}
		public void setLevel(int level){this.level = level;};


		public int getScore()
			{
				return score;
			}


		public void setScore(int score)
			{
				this.score = score;
			}



		private int lives;
		public int getLives(){return lives;}

		public void setLives(int lives){this.lives = lives;}



		public Player(Vector2 pos, Vector2 direction,Float width,Float height, EntityManager entityManager,OrthoCamera camera)
			{
				super(Assets.PLAYER, pos, direction,width,height);
				this.entityManager= entityManager;
				this.camera = camera;
				this.lives = 3;
				this.score = 0;
				this.level =1;



			}

		@Override
		public void update()
			{

				pos.add(direction);
				//add scores


				// shoot button?
				// movement

				int dir = 0;
				if(Gdx.input.isTouched())
					{
						Vector2 touch= camera.unprojectCoordinates(Gdx.input.getX(),Gdx.input.getY());
						if(touch.x > InvaderY.WIDTH/2 )
							{
								dir=2;

							}
						else if(touch.x < 0 + Assets.shootButton.getWidth() && touch.y < 0 + Assets.shootButton.getHeight())
							{
								dir =0;
							}
						else
							{
								dir=1;
							}
					}


				if(Gdx.input.isKeyPressed(Input.Keys.A)|| dir==1)
					{
						setDirection(-300,0);


					}
				else if(Gdx.input.isKeyPressed(Input.Keys.D)|| dir==2)
					{
						setDirection(300,0);
					}
				else
					{
						setDirection(0,0);
					}

				//add to not go past screen

				if (pos.x >= InvaderY.WIDTH - Assets.PLAYER.getWidth() )
					{
						pos.x = InvaderY.WIDTH  - Assets.PLAYER.getWidth();
					}

				if (pos.x <= 0 )
					{
						pos.x = 0 ;
					}



				if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
					{
						if(System.currentTimeMillis()-lastFire>=250)
							{
								entityManager.AddEntity(new Missile(pos.cpy().add(Assets.PLAYER.getWidth() / 2, Assets.PLAYER.getHeight())));
								Assets.laser.play();
								lastFire=System.currentTimeMillis();
							}


					}




					// TODO: combine these two for space and touched.
				// add if shootbutton pressed as well
				if(Gdx.input.isTouched())
					{
						Vector2 touchButton= camera.unprojectCoordinates(Gdx.input.getX(),Gdx.input.getY());
						Vector2 touchFire = new Vector2(Assets.shootButton.getX(),Assets.shootButton.getY());
						if(touchButton.x < touchFire.x +Assets.shootButton.getWidth() && touchButton.y < touchFire.y + Assets.shootButton.getHeight())
							{

								if(System.currentTimeMillis()-lastFire>=250)
									{
										entityManager.AddEntity(new Missile(pos.cpy().add(Assets.PLAYER.getWidth() / 2, Assets.PLAYER.getHeight())));
										Assets.laser.play();
										lastFire=System.currentTimeMillis();
									}

							}

					}



			}


	}

