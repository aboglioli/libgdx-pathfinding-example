package com.kiriost.game.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by kiriost on 02/04/16.
 */
public class AnimatedActor extends Actor {
    // Graphics and animation
    private TextureRegion[] textureRegions;
    private TextureRegion currentAnimation;
    protected Animation animation;
    private float duration = 0;

    // Animation
    private boolean walking = true;

    public AnimatedActor(String texture, int tileWidth, int tileHeight) {
        TextureRegion[][] temp = new TextureRegion(new Texture(texture)).split(tileWidth, tileHeight);

        textureRegions = new TextureRegion[temp.length * temp[0].length];
        int index = 0;
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                textureRegions[index++] = temp[i][j];
            }
        }

        animation = new Animation(0.1f, textureRegions);

        setX(0);
        setY(0);
        setWidth(40f);
        setHeight(40f);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(walking) {
            duration += delta;
            if (duration > 50f)
                duration = 0;

            currentAnimation = animation.getKeyFrame(duration, true);
        }
        else {
            currentAnimation = textureRegions[0];
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        setColor(color.r, color.g, color.b, color.a * parentAlpha);

        batch.setColor(getColor());
        batch.draw(currentAnimation, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(),
                getScaleY(), getRotation());
    }

    public TextureRegion getCurrentAnimation() {
        return currentAnimation;
    }
}