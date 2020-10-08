package xuanwu.ultimate.entity;

public interface IUltimateEntity {
public float getEntityHealth();
public float getEntityMaxHealth();
public boolean RefuseDead();
public boolean UpdateForced();
public boolean LockDeathTime();
}
