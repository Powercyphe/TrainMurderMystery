package dev.doctor4t.trainmurdermystery.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;

public class GunshotParticle extends SpriteBillboardParticle {
    protected GunshotParticle(ClientWorld world, double x, double y, double z,
                                    double vx, double vy, double vz) {
        super(world, x, y, z, vx, vy, vz);

        this.maxAge = 3;
        this.scale = 0.2f;
        this.setVelocity(vx, vy, vz);

        this.red = 1f;
        this.green = 1f;
        this.blue = 1f;
        this.alpha = 1f;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    protected int getBrightness(float tint) {
        return 0xF000F0;
    }

    @Override
    public void tick() {
        super.tick();
        this.alpha = 1.0f - ((float) this.age / this.maxAge);
    }

    public static class Factory<DefaultParticleType extends ParticleEffect> implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider sprites;

        public Factory(SpriteProvider sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(DefaultParticleType type, ClientWorld world,
                                       double x, double y, double z,
                                       double vx, double vy, double vz) {
            GunshotParticle particle = new GunshotParticle(world, x, y, z, vx, vy, vz);
            particle.setSprite(this.sprites);
            return particle;
        }
    }
}
